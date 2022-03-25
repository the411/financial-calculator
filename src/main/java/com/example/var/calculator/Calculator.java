package com.example.var.calculator;


public interface Calculator<T, U> {
    CalculatorType getType();

    T calculate(U data);
}