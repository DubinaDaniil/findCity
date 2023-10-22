package com.find_city.bd;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class JsonCityNamesReader implements CityNamesReader {
    private static final Gson GSON = new Gson();
    Type REVIEW_TYPE = TypeToken.getParameterized(List.class, CityName.class).getType();
    @Override
    public List<String> read (String path) {

        List<CityName> citiesNames;
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

        System.out.println(stringBuilder);

        citiesNames = GSON.fromJson(stringBuilder.toString(), REVIEW_TYPE);

        return citiesNames.stream()
                .map(CityName::getName)
                .toList();
    }
}