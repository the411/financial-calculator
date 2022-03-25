package com.example.var.controller;

import com.example.var.model.BaseResult;
import com.example.var.model.DataInput;
import com.example.var.service.CalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class RiskCalculatorController {

    @Autowired
    CalculationService service;

    @PostMapping("/calculate")
    public ResponseEntity<BaseResult> calculate(@RequestBody DataInput input) {
        BaseResult result = service.calculate(input);
        return ResponseEntity.accepted().body((result));
    }


}
