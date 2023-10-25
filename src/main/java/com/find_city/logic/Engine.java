package com.find_city.logic;

import com.find_city.bd.CityDatabase;
import com.find_city.final_project.*;

import java.util.List;

public class Engine {

    private static final String END_GAME = "здаюсь";

    private int playerScore = 0;
    private int computerScore = 0;
    private String lastWord = "";

    CityDatabase cityDatabase = new CityDatabase();

    public String verification(final String value) {
        boolean inCollection = false;
        boolean endChar = false;
        if (END_GAME.equalsIgnoreCase(value.trim())) {
            this.endWindow(this.playerScore, this.computerScore);
        } else {
            endChar = this.isEndChar(value);
            inCollection = cityDatabase.contain(value);

            if (inCollection && !endChar) {
                errorWindow("Слово не на останню букву");
            }
            if (!inCollection) {
                errorWindow("Міста немає в базі");
            }

            if (endChar && inCollection) {
                playerScore++;
                cityDatabase.remove(value);
                String result = searchWord(value.toCharArray()[value.length() - 1]);
                if (result.length() > 0) {
                    computerScore++;
                    lastWord = result;
                    return result;
                } else {
                    endWindow(playerScore, computerScore);
                }
            }
        }
        return "";
    }

    private String searchWord(final char firstChar) {
        List<String> list = cityDatabase.allList();
        String result = "";

        for (String city : list) {
            if (String.valueOf(city.toCharArray()[0]).equalsIgnoreCase(String.valueOf(firstChar))) {
                result = city;
                break;
            }
        }
        return result;
    }

    private boolean isEndChar(final String value) {
        if (this.lastWord.length() == 0) {
            return true;
        } else {
            char lastWordChar = this.lastWord.toCharArray()[this.lastWord.length() - 1];
            char valueChar = value.toCharArray()[0];
            return String.valueOf(lastWordChar).equalsIgnoreCase(String.valueOf(valueChar));
        }
    }

    private void endWindow(final int playerScore, final int computerScore) {
        System.out.println("Комп'ютер = " + computerScore + "\nГравець = " + playerScore);
    }

    private void errorWindow(final String errorMessage) {
        System.out.println("Помилка : " + errorMessage);
    }
}
