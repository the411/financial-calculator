package com.example.var.dataprovider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class DataProviderFactory {

    private final Map<DataProviderType, DataProvider> map;

    @Autowired
    private DataProviderFactory(List<DataProvider> providers) {
        map = providers.stream().collect(Collectors.toUnmodifiableMap(DataProvider::getType, Function.identity()));
    }

    public DataProvider getDataProvider(DataProviderType type) {
        return Optional.ofNullable(map.get(type)).orElseThrow(IllegalArgumentException::new);
    }

}
