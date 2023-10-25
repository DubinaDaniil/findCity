package com.find_city.final_project;

import com.find_city.bd.*;
import javafx.animation.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.util.*;

import java.net.*;
import java.util.*;

public class MainViewController implements Initializable {

    private static final String VICTORY = "ПЕРЕМОГА!!!";
    private static final String LOSE = "Ти програв!!!";
    private static final String TIE = "Нічия!!!";
    private static final String END_GAME = "здаюсь";
    private static final String WRONG_LAST_LATTER = "Слово не на останню букву";
    private static final String CITY_IS_NOT_FOUND = "Міста немає в базі";
    private static final String EMPTY_FIELD = "Ви ввели порожній рядок";

    @FXML
    private GridPane gameAreaGridPane;
    @FXML
    private TextField inputTextTextField;
    @FXML
    private Label pcCityLabel;
    @FXML
    private VBox inviteVbox;
    @FXML
    private Label prevCityLabel;
    @FXML
    private ImageView customerCityEmblemImageView;
    @FXML
    private ImageView pcCityEmblemImageView;
    @FXML
    private VBox scoreVbox;
    @FXML
    private VBox finalViewVbox;
    @FXML
    private Label finalMessageLabel;
    @FXML
    private Label customerCorrectAnswersLabel;
    @FXML
    private Label pcCorrectAnswersLabel;
    @FXML
    private Label errorLabel;

    CityDatabase cityDatabase = new CityDatabase();

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        gameAreaGridPane.managedProperty().bind(gameAreaGridPane.visibleProperty());
        inviteVbox.managedProperty().bind(inviteVbox.visibleProperty());
        scoreVbox.managedProperty().bind(scoreVbox.visibleProperty());
        finalViewVbox.managedProperty().bind(finalViewVbox.visibleProperty());
    }

    @FXML
    public void onStartGameButtonClick() {
        gameAreaGridPane.setVisible(true);
        inviteVbox.setVisible(false);
    }

    @FXML
    public void onPlayAgain() {
        dropToDefault();
    }

    @FXML
    public void onExit() {
        System.exit(0);
    }

    @FXML
    public void onNextCityButton() {
        errorLabel.setText("");
        if (inputTextTextField.getText().isEmpty()) {
            errorWindow(EMPTY_FIELD);
        }
        verification(getCustomerCity());
        inputTextTextField.clear();
    }

    private int playerScore = 0;
    private int computerScore = 0;
    private String lastWord = "";


    public String verification(final String value) {
        boolean inCollection;
        boolean endChar;
        if (END_GAME.equalsIgnoreCase(value.trim())) {
            endWindow(playerScore, computerScore);
        } else {
            endChar = this.isEndChar(value);
            inCollection = cityDatabase.contain(value);

            showErrorMessage(inCollection, endChar);

            if (endChar && inCollection) {
                playerScore++;
                cityDatabase.remove(value);
                String result = searchWord(value.toCharArray()[value.length() - 1]);
                if (result.length() > 0) {
                    pcCityLabel.setText("");
                    computerScore++;
                    lastWord = result;
                    pcCityLabel.setText(result);
                    prevCityLabel.setText(getCustomerCity());
                    return result;
                } else {
                    endWindow(playerScore, computerScore);
                }
            }
        }
        return "";
    }

    private void showErrorMessage(final boolean inCollection, final boolean endChar) {
        if (inCollection && !endChar) {
            errorWindow(WRONG_LAST_LATTER);
        } else if (!inCollection) {
            errorWindow(CITY_IS_NOT_FOUND);
        }
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
        finalMessage(playerScore, computerScore);
        customerCorrectAnswersLabel.setText(String.valueOf(playerScore));
        pcCorrectAnswersLabel.setText(String.valueOf(computerScore));
        gameAreaGridPane.setVisible(false);
        scoreVbox.setVisible(true);
        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        KeyFrame showSwipeAgainFrame = new KeyFrame(Duration.seconds(5), event -> {
            scoreVbox.setVisible(false);
            finalViewVbox.setVisible(true);
        });
        timeline.getKeyFrames().add(showSwipeAgainFrame);
        timeline.play();
    }

    private void errorWindow(final String errorMessage) {
        errorLabel.setText("");
        errorLabel.setText(errorMessage);
    }

    private String getCustomerCity() {
        return inputTextTextField.getText();
    }

    private void dropToDefault() {
        finalViewVbox.setVisible(false);
        gameAreaGridPane.setVisible(true);
        prevCityLabel.setText("");
        pcCityLabel.setText("");
    }

    private void finalMessage(final int customerScore, final int computerScore) {
        if (customerScore == computerScore) {
            finalMessageLabel.setText(TIE);
        } else if (customerScore > computerScore) {
            finalMessageLabel.setText(VICTORY);
        } else finalMessageLabel.setText(LOSE);
    }
}
