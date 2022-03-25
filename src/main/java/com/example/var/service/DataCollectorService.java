package com.example.var.service;

import com.example.var.configuration.YAMLConfig;
import com.example.var.dataprovider.DataProviderFactory;
import com.example.var.dataprovider.DataProviderType;
import com.example.var.model.DataInput;
import com.example.var.model.PortfolioStockHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataCollectorService extends AbstractDataCollectorService {

    @Autowired
    private DataProviderFactory dataProviderFactory;
    @Autowired
    private YAMLConfig config;

    @Override
    public PortfolioStockHistory prepareData(DataInput input) {

        PortfolioStockHistory stockHistoryResult;

        try {
            stockHistoryResult = (PortfolioStockHistory) dataProviderFactory
                    .getDataProvider(DataProviderType.valueOf(config.getProvider()))
                    .collectPortfolioData(input);
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return stockHistoryResult;
    }
}
