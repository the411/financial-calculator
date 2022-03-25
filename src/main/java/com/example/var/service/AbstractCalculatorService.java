package com.example.var.service;

import com.example.var.model.BaseResult;
import com.example.var.model.DataInput;

public abstract class AbstractCalculatorService {
    public abstract BaseResult calculate(DataInput input);
}
