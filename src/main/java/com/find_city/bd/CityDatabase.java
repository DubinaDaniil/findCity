package com.find_city.bd;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.*;

public class CityDatabase {

    private static final String JSON_PATH = "./src/main/DATA/city.json";
    private final List<CityName> cityNameList;

    public CityDatabase() {
        JsonCityNamesReader reader = new JsonCityNamesReader();
        this.cityNameList = reader.gsonRead(JSON_PATH);
    }

    public List<String> allList() {
        return cityNameList
                .stream()
                .map(CityName::getName)
                .collect(Collectors.toList());
    }

    public void remove(final String cityName) {
        cityNameList.removeIf(name -> name.getName().equalsIgnoreCase(cityName.toLowerCase().trim()));
    }

    public boolean contain(final String cityName) {
        boolean result = false;
        for (CityName name : cityNameList) {
            if (name.getName().equalsIgnoreCase(cityName.strip().toLowerCase())) {
                result = true;
                break;
            }
        }
        return result;
    }

    public URL getCityEmblemUrl(String cityName) throws MalformedURLException, UrlNotFoundInCityDatabaseException {
        URL cityEmblemUrl;
        if (contain(cityName)) {
            String url = "";
            for (CityName name : cityNameList) {
                if (name.getName().equalsIgnoreCase(cityName.toLowerCase().trim())) {
                    url = "https:" + name.getUrl();
                    break;
                }
            }
            cityEmblemUrl = new URL(url);
        } else {
            throw new UrlNotFoundInCityDatabaseException("URL емблемы города \"" + cityName + "\" не найдено");
        }
        return cityEmblemUrl;
    }

    @Override
    public String toString() {
        return cityNameList.toString();
    }
}