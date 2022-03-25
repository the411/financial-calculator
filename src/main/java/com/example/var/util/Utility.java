package com.example.var.util;

import com.example.var.configuration.YAMLConfig;
import com.example.var.model.DateRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class Utility {

    public static DateRange setDateRange() {
        Calendar from = Calendar.getInstance();
        Calendar to = Calendar.getInstance();
        from.add(Calendar.YEAR, -1);
        return new DateRange(from, to);
    }
}
