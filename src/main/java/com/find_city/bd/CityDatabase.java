package com.find_city.bd;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CityDatabase {

    private static final String JSON_PATH = "./src/main/resources/city.json";
    private final List<CityName> cityNameList = new ArrayList<>();

    public CityDatabase() {
        List<CityName> readCityNamesFromJson = JsonCityNamesReaderUtil.gsonRead(JSON_PATH);
        cityNameList.addAll(readCityNamesFromJson);
    }

    public List<String> allList() {
        return cityNameList
                .stream()
                .map(CityName::getName)
                .collect(Collectors.toList());
    }

    public void remove(final String cityName) {
        cityNameList.removeIf(name -> name.getName()
                .equalsIgnoreCase(cityName.toLowerCase().trim()));
    }

    public boolean contain(final String cityName) {
        return findUrlByCityName(cityName);
    }

    private boolean findUrlByCityName(final String cityName) {
        boolean isUrlInCityNameList = false;
        for (CityName name : cityNameList) {
            if (name.getName().equalsIgnoreCase(cityName.strip().toLowerCase())) {
                isUrlInCityNameList = true;
                break;
            }
        }
        return isUrlInCityNameList;
    }

    public URL getCityEmblemUrl(final String cityName) throws MalformedURLException {
        URL cityEmblemUrl;
        String url = "";
        if (contain(cityName)) {
            for (CityName name : cityNameList) {
                if (name.getName().equalsIgnoreCase(cityName.toLowerCase().trim())) {
                    url = "https:" + name.getUrl();
                    break;
                }
            }
        }
        cityEmblemUrl = new URL(url);
        return cityEmblemUrl;
    }

    @Override
    public String toString() {
        return cityNameList.toString();
    }
}