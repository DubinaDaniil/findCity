package com.find_city.bd;

import java.util.ArrayList;
import java.util.List;

public class CityDatabase {

    private static final String JSON_PATH = "./src/main/DATA/city.json";

    private final List<String> cityNames;

    public CityDatabase() {

        JsonCityNamesReader reader = new JsonCityNamesReader();

        this.cityNames = reader.read(JSON_PATH);

    }
    public List<String> AllList () {
        return new ArrayList<>(cityNames);
    }
    public void remove (String CityName) {

        int count = 0;
        for (String name : cityNames) {
            if (CityName.equalsIgnoreCase(name)) {
                cityNames.remove(count);
            }
            count++;
        }
    }

    @Override
    public String toString() {
        return cityNames.toString();
    }

    public static void main(String[] args) {

        CityDatabase database = new CityDatabase();

        System.out.println(database);
    }
}