package com.example.var.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class DataInput {

    public String[] tickerList;
    private String calculationMethod;

}
