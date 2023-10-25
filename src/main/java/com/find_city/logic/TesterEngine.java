package com.find_city.logic;

import com.find_city.bd.CityDatabase;

import java.net.MalformedURLException;

public class TesterEngine {

    public static void main(String[] args) throws MalformedURLException {
        Engine engine = new Engine();
        System.out.println(engine.verification("Херсон"));
        System.out.println(engine.verification("Алупка"));
        System.out.println(engine.verification("Алупка"));
        System.out.println(engine.verification("Дніпро"));
        System.out.println(engine.verification("здаюсь"));
    }
}