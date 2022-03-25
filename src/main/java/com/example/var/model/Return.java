package com.example.var.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class Return implements Comparable {

    private BigDecimal value;

    @Override
    public int compareTo(Object o) {
        Return r = (Return) o;
        return this.value.compareTo(r.getValue());
    }
}
