package com.find_city.bd;

import java.util.ArrayList;

public class CityDatabase {

    private static final String JSON_PATH = "./src/main/DATA/city.json";

    private final ArrayList<String> cityNames;

    public CityDatabase() {

        JsonCityNamesReader reader = new JsonCityNamesReader();

        this.cityNames = reader.read(JSON_PATH);

    }
    public ArrayList<String> AllList () {
        return new ArrayList<>(cityNames);
    }
    public void remove (String CityName) {

        int count = 0;
        int countForDelete = 0;
        for (String name : cityNames) {
            if (CityName.equalsIgnoreCase(name.toLowerCase())) {
                countForDelete = count;
            }
            count++;
        }
        cityNames.remove(countForDelete);
    }

    public boolean contain (String cityName) {
        boolean result = false;

        for (String name : cityNames) {
            if (name.equalsIgnoreCase(cityName.toLowerCase())) {
                result = true;
                break;
            }
        }
        return result;
    }
    @Override
    public String toString() {
        return cityNames.toString();
    }
}