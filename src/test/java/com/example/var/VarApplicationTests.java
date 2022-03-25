package com.example.var;

import com.example.var.calculator.HistoricalMethodRiskCalculator;
import com.example.var.controller.RiskCalculatorController;
import com.example.var.model.BaseResult;
import com.example.var.model.DataInput;
import com.example.var.model.PortfolioStockHistory;
import com.example.var.model.RiskCalculationResult;
import com.example.var.service.CalculationService;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;


@SpringBootTest
class VarApplicationTests {

    @Autowired
    RiskCalculatorController controller;
    @Autowired
    CalculationService calculationService;

    @Autowired
    HistoricalMethodRiskCalculator historicalMethodRiskCalculator;

    @Test
    void testCalculationService() {
        /*
        DataInput input = TestDataBuilder.buildDataInput();
        ResponseEntity<BaseResult> result = controller.calculate(input);
        Assertions.assertEquals(BigDecimal.valueOf(-0.04), result.getBody().getValue());

         */

        PortfolioStockHistory stockHistoryData = TestDataBuilder.buildSingleStockHistoryData();

        RiskCalculationResult result = historicalMethodRiskCalculator.calculate(stockHistoryData);
        Assertions.assertEquals(BigDecimal.valueOf(0.01),result.getValue());
    }


}
