package com.example.var.dataprovider;

import com.example.var.model.DataInput;
import com.example.var.model.DateRange;
import com.example.var.model.PortfolioStockHistory;
import com.example.var.util.Utility;
import org.springframework.stereotype.Service;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class YahooFinanceDataProvider implements DataProvider<PortfolioStockHistory, DataInput> {

    private static final DataProviderType PROVIDER_TYPE = DataProviderType.YAHOO;


    @Override
    public DataProviderType getType() {
        return PROVIDER_TYPE;
    }

    @Override
    public PortfolioStockHistory collectPortfolioData(DataInput input) {
        Map<String, List<HistoricalQuote>> map = new HashMap<>();
        try {
            DateRange range = Utility.setDateRange();
            Map<String, Stock> stocks = YahooFinance.get(input.getTickerList(), range.getFrom(), range.getTo(), Interval.DAILY);

            if (!stocks.isEmpty()) {
                for (String key : input.getTickerList()) {
                    if (stocks.containsKey(key)) {
                        Stock stock = stocks.get(key);
                        if (!stock.getHistory().isEmpty()) {
                            List<HistoricalQuote> data = stock.getHistory();
                            map.put(key, data);
                        }
                    }
                }
            }

        } catch (Exception e) {
            throw new RuntimeException();
        }
        return PortfolioStockHistory.builder().stockHistory(map).build();
    }
}
