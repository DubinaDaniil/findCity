package com.find_city.bd;

import java.util.List;

public interface CityNamesReader <T> {
    List<T> read(String path);
}