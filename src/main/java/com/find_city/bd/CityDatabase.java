package com.find_city.bd;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class CityDatabase {
    private static final String JSON_PATH = "./src/main/DATA/city.json";
    private final ArrayList<CityName> cities;

    public CityDatabase() {
        JsonCityNamesReader reader = new JsonCityNamesReader();
        this.cities = reader.read(JSON_PATH);
    }

    public ArrayList<String> AllList () {
        return (ArrayList<String>) cities.stream().map(CityName::getName).collect(Collectors.toList());
    }

    public void remove (String CityName) {
        int count = 0;
        int countForDeletingElement = 0;
        for (CityName name : cities) {
            if (CityName.equalsIgnoreCase(name.getName().strip().toLowerCase())) {
                countForDeletingElement = count;
            }
            count++;
        }
        cities.remove(countForDeletingElement);
    }

    public boolean contain (String cityName) {
        boolean result = false;
        for (CityName name : cities) {
            if (name.getName().equalsIgnoreCase(cityName.strip().toLowerCase())) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return cities.toString();
    }
}