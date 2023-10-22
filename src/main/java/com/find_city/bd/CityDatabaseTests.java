package com.find_city.bd;

import java.util.List;

public class CityDatabaseTests {

    public static void main(String[] args) {

        CityDatabase database = new CityDatabase();

        List<String> strings = database.AllList();

        System.out.println(strings);

    }
}