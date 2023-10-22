package com.find_city.logic;

public class TesterEngine {
    public static void main(String[] args) {
        Engine engine = new Engine();
        System.out.println(engine.verification("Херсон"));
        System.out.println(engine.verification("Алупка"));
        System.out.println(engine.verification("Алупка"));
        System.out.println(engine.verification("Дніпро"));
        System.out.println(engine.verification("здаюсь"));
    }
}
