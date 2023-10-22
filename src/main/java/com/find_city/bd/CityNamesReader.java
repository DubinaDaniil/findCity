package com.find_city.bd;

import java.util.List;

public interface CityNamesReader {
    List<String> read(String path);
}
