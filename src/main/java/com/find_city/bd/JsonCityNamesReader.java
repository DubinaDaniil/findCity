package com.find_city.bd;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class JsonCityNamesReader implements CityNamesReader {

    private static final Gson GSON = new Gson();

    @Override
    public ArrayList<String> read (String path) {

        ArrayList<String> cities = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append('\n');
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        CityName [] citiesNames = GSON.fromJson(stringBuilder.toString(), CityName[].class);

        for (CityName cityName : citiesNames) {
            cities.add(cityName.getName());
        }

        return cities;
    }
}