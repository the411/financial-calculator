package com.example.var;

import com.example.var.model.DataInput;
import com.example.var.model.PortfolioStockHistory;
import yahoofinance.histquotes.HistoricalQuote;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestDataBuilder {

    public static DataInput buildDataInput(){
        return DataInput.builder()
                .tickerList(new String[]{"TSLA", "GOOG"})
                .calculationMethod("VAR_HISTORICAL")
                .build();
    }

    public static PortfolioStockHistory buildSingleStockHistoryData(){
        Map<String, List<HistoricalQuote>> stockHistoryMap = new HashMap<>();
        List<HistoricalQuote> daysList = new ArrayList<>();
        for (int i = 1; i < 101 ; i ++){
            HistoricalQuote quote = new HistoricalQuote();
            quote.setClose(BigDecimal.valueOf(i));
            daysList.add(quote);
        }

        stockHistoryMap.put("AMZN", daysList);

        return PortfolioStockHistory.builder()
                .stockHistory(stockHistoryMap)
                .build();
    }
}
