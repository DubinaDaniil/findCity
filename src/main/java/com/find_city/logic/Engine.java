package com.find_city.logic;

import com.find_city.bd.CityDatabase;

import java.util.ArrayList;

public class Engine {
    private static final String END_GAME = "здаюсь";
    private int player = 0;
    private int computer = 0;
    private String lastWord = "";
    CityDatabase db = new CityDatabase();

    public String verification(String value) {
        boolean inCollection = false;
        boolean endChar = false;
        if ("здаюсь".equalsIgnoreCase(value.trim())) {
            this.endWindow(this.player, this.computer);
        } else {
            endChar = this.isEndChar(value);
            inCollection = db.contain(value);

            if(inCollection && !endChar){
                errorWindow("Слово не на останню букву");
            }
            if(!inCollection){
                errorWindow("Міста немає в базі");
            }

            if(endChar && inCollection){
                player++;
                db.remove(value);
                String result = searchWord(value.toCharArray()[value.length()-1]);
                if(result.length() > 0){
                    computer++;
                    lastWord = result;
                    return result;
                }else{
                    endWindow(player,computer);
                }
            }
        }
        return "";
    }

    private String searchWord(char firstChar){
        ArrayList<String> list = db.AllList();
        String result = "";

        for (String city:list) {
            if(String.valueOf(city.toCharArray()[0]).equalsIgnoreCase(String.valueOf(firstChar))){
                result = city;
                break;
            }
        }

        return result;
    }
    private boolean isEndChar(String value) {
        if (this.lastWord.length() == 0) {
            return true;
        } else {
            char lastWordChar = this.lastWord.toCharArray()[this.lastWord.length() - 1];
            char valueChar = value.toCharArray()[0];
            return String.valueOf(lastWordChar).equalsIgnoreCase(String.valueOf(valueChar));
        }
    }

    private void endWindow(int player, int computer) {
        System.out.println("Комп'ютер = " + computer + "\nГравець = " + player);
    }
    private void errorWindow(String msg){
        System.out.println("Помилка : " + msg);
    }
}
