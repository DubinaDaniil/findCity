package com.find_city.bd;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class CityDatabase {

    private static final String JSON_PATH = "./src/main/DATA/city.json";
    private final ArrayList<CityName> cityNameArrayList;

    public CityDatabase() {
        JsonCityNamesReader reader = new JsonCityNamesReader();
        this.cityNameArrayList = reader.gsonRead(JSON_PATH);
    }

    public ArrayList<String> allList() {
        return (ArrayList<String>) cityNameArrayList.stream().map(CityName::getName).collect(Collectors.toList());
    }

    public void remove(final String CityName) {
        int count = 0;
        int countForDeletingElement = 0;
        for (CityName name : cityNameArrayList) {
            if (CityName.equalsIgnoreCase(name.getName().strip().toLowerCase())) {
                countForDeletingElement = count;
            }
            count++;
        }
        cityNameArrayList.remove(countForDeletingElement);
    }

    public boolean contain(final String cityName) {
        boolean result = false;
        for (CityName name : cityNameArrayList) {
            if (name.getName().equalsIgnoreCase(cityName.strip().toLowerCase())) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return cityNameArrayList.toString();
    }
}