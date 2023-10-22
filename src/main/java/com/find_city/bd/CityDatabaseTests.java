package com.find_city.bd;

import java.util.List;

public class CityDatabaseTests {

    public static void main(String[] args) {

        CityDatabase database = new CityDatabase();

        database.remove("Авдіївка");
        database.remove("Алмазна");

        List<String> cityNames = database.AllList();

        System.out.println(cityNames);

    }
}