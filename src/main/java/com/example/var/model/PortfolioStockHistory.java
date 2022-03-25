package com.example.var.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import yahoofinance.histquotes.HistoricalQuote;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
public class PortfolioStockHistory extends DataHistory {

    private Map<String, List<HistoricalQuote>> stockHistory;
}
