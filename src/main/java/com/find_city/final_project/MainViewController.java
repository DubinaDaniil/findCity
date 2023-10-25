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
    private static final String END_GAME = "здаюсь";
    private static final String WRONG_LAST_LATTER = "Слово не на останню букву";
    private static final String CITY_IS_NOT_FOUND = "Міста немає в базі";

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

    @FXML
    public void onStartGameButtonClick() {
        gameAreaGridPane.setVisible(true);
        inviteVbox.setVisible(false);
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        gameAreaGridPane.managedProperty().bind(gameAreaGridPane.visibleProperty());
        inviteVbox.managedProperty().bind(inviteVbox.visibleProperty());
        scoreVbox.managedProperty().bind(scoreVbox.visibleProperty());
        finalViewVbox.managedProperty().bind(finalViewVbox.visibleProperty());
    }

    @FXML
    public void onPlayAgain() {

    }

    @FXML
    public void onExit() {

    }

    @FXML
    public void onNextCityButton() {
        errorLabel.setText("");
        prevCityLabel.setText(getCustomerCity());
        verification(getCustomerCity());
        inputTextTextField.clear();
    }

    private int playerScore = 0;
    private int computerScore = 0;
    private String lastWord = "";

    CityDatabase cityDatabase = new CityDatabase();

    public String verification(final String value) {
        boolean inCollection;
        boolean endChar;
        if (END_GAME.equalsIgnoreCase(value.trim())) {
            endWindow(playerScore, computerScore);
        } else {
            endChar = this.isEndChar(value);
            inCollection = cityDatabase.contain(value);

            if (inCollection && !endChar) {
                errorWindow(WRONG_LAST_LATTER);
            }
            if (!inCollection) {
                errorWindow(CITY_IS_NOT_FOUND);
            }

            if (endChar && inCollection) {
                playerScore++;
                cityDatabase.remove(value);
                String result = searchWord(value.toCharArray()[value.length() - 1]);
                if (result.length() > 0) {
                    pcCityLabel.setText("");
                    computerScore++;
                    lastWord = result;
                    pcCityLabel.setText(result);
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
}