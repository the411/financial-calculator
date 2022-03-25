package com.example.var.calculator;

import com.example.var.configuration.YAMLConfig;
import com.example.var.model.PortfolioStockHistory;
import com.example.var.model.Return;
import com.example.var.model.RiskCalculationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yahoofinance.histquotes.HistoricalQuote;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class HistoricalMethodRiskCalculator implements Calculator<RiskCalculationResult, PortfolioStockHistory> {

    @Autowired
    private YAMLConfig config;

    private static final CalculatorType CALCULATOR_TYPE = CalculatorType.VAR_HISTORICAL;


    @Override
    public RiskCalculationResult calculate(PortfolioStockHistory history) {
        Map<String, List<HistoricalQuote>> historyDataMap = history.getStockHistory();
        RiskCalculationResult result = RiskCalculationResult.builder().build();

        try {
            if (historyDataMap.isEmpty()) {
                return result;
            }
            if (historyDataMap.size() == 1) {
                result = calculateSingleStockRiskValue(historyDataMap);
            } else {
                result = calculatePortfolioRiskValue(historyDataMap);
            }

        } catch (Exception e) {
            throw new RuntimeException();
        }

        return result;
    }

    private RiskCalculationResult calculateSingleStockRiskValue(Map<String, List<HistoricalQuote>> historyDataMap) {
        RiskCalculationResult result;

        try {
            List<HistoricalQuote> historicalQuotes = historyDataMap.entrySet().iterator().next().getValue();
            List<BigDecimal> dailyValueList = historicalQuotes.stream()
                    .map(HistoricalQuote::getClose)
                    .collect(Collectors.toList());
            result = calculateDailyReturnPercentagesAndRiskValue(dailyValueList);
        } catch (Exception e) {
            throw new RuntimeException();
        }

        return result;
    }

    private RiskCalculationResult calculatePortfolioRiskValue(Map<String, List<HistoricalQuote>> historyDataMap) {

        RiskCalculationResult result = RiskCalculationResult.builder().build();
        Map<Integer, BigDecimal> dailyPortfolioValueMap = new HashMap<>();
        try {
            int dayCount = historyDataMap.entrySet().iterator().next().getValue().size();
            for (int day = 0; day < dayCount; day++) {
                for (Map.Entry<String, List<HistoricalQuote>> entry : historyDataMap.entrySet()) {
                    int theDay = day;
                    if (!dailyPortfolioValueMap.containsKey(day)) {
                        dailyPortfolioValueMap.put(day, entry.getValue().get(day).getClose());
                    } else {
                        dailyPortfolioValueMap.compute(day, (key, value) -> value.add(entry.getValue().get(theDay).getClose()));
                    }
                }
            }

            List<BigDecimal> dailyPortfolioValues = new ArrayList<>(dailyPortfolioValueMap.values());
            return calculateDailyReturnPercentagesAndRiskValue(dailyPortfolioValues);


        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    private RiskCalculationResult calculateDailyReturnPercentagesAndRiskValue(List<BigDecimal> dailyValueList) {
        BigDecimal todaysClose;
        BigDecimal yesterdaysClose;
        List<Return> dailyReturnList = new ArrayList<>();

        try {
            for (int i = 1; i < dailyValueList.size(); i++) {
                todaysClose = dailyValueList.get(i);
                yesterdaysClose = dailyValueList.get(i - 1);

                BigDecimal dailyReturnPercentage = (todaysClose.divide(yesterdaysClose, 2, RoundingMode.FLOOR)).subtract(BigDecimal.ONE);

                Return dailyReturn = Return.builder().value(dailyReturnPercentage).build();
                dailyReturnList.add(dailyReturn);
            }
            Collections.sort(dailyReturnList);
            double percentile = 1 - config.getConfidence();
            int size = dailyReturnList.size()-1;

            int intIndex = (int) (size * percentile);

            return RiskCalculationResult.builder()
                    .value(dailyReturnList.get(intIndex).getValue())
                    .build();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public CalculatorType getType() {
        return CALCULATOR_TYPE;
    }
}
