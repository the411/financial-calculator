package com.example.var.dataprovider;

public interface DataProvider<T, U> {
    DataProviderType getType();

    T collectPortfolioData(U input);

}
