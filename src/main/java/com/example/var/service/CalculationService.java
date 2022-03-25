package com.example.var.service;

import com.example.var.calculator.CalculatorFactory;
import com.example.var.calculator.CalculatorType;
import com.example.var.model.BaseResult;
import com.example.var.model.DataHistory;
import com.example.var.model.DataInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculationService extends AbstractCalculatorService {

    @Autowired
    private DataCollectorService dataCollectorService;
    @Autowired
    private CalculatorFactory calculatorFactory;

    @Override
    public BaseResult calculate(DataInput input) {

        BaseResult result;
        try {
            DataHistory history = dataCollectorService.prepareData(input);
            result = (BaseResult) calculatorFactory.getCalculator(CalculatorType.valueOf(input.getCalculationMethod())).calculate(history);
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return result;
    }


}
