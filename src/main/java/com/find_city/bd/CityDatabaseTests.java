package com.find_city.bd;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class CityDatabaseTests {

    public static void main(String[] args) {

        CityDatabase database = new CityDatabase();

        database.remove("Авдіївка");
        database.remove(" алмазна ");

        List<String> cityNames = database.allList();

        System.out.println(cityNames);
        System.out.println(database.contain(" алупка "));

        try {
            URL alupka = database.getCityEmblemUrl("алупка");
            System.out.println(alupka);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UrlNotFoundInCityDatabaseException e) {
            e.printStackTrace();
        }
    }
}