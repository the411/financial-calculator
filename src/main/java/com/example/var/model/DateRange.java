package com.example.var.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;

@Getter
@Setter
@AllArgsConstructor
public class DateRange {

    private Calendar from;
    private Calendar to;
}
