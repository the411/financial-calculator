package com.example.var.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class CalculatorFactory {

    private final Map<CalculatorType, Calculator> map;

    @Autowired
    private CalculatorFactory(List<Calculator> calculators) {
        map = calculators.stream().collect(Collectors.toUnmodifiableMap(Calculator::getType, Function.identity()));
    }

    public Calculator getCalculator(CalculatorType type) {
        return Optional.ofNullable(map.get(type)).orElseThrow(IllegalArgumentException::new);
    }

}
