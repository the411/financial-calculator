package com.example.var.service;

import com.example.var.model.DataHistory;
import com.example.var.model.DataInput;

public abstract class AbstractDataCollectorService {
    public abstract DataHistory prepareData(DataInput input);

}
