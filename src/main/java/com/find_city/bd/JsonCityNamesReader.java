package com.find_city.bd;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class JsonCityNamesReader implements CityNamesReader <CityName> {
    private static final Gson GSON = new Gson();

    @Override
    public ArrayList<CityName> read(String path) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append('\n');
            }
        } catch (IOException e) {
            System.out.println("File not found");
        }
        CityName[] citiesNames = GSON.fromJson(stringBuilder.toString(), CityName[].class);
        return new ArrayList<>(Arrays.asList(citiesNames));
    }
}