package com.find_city.final_project;

import com.find_city.bd.*;
import javafx.animation.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.util.*;

import java.io.*;
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
    private static final String PATH_IMAGES = "src/main/resources/com/find_city/final_project/images";
    private static final String STRING_EMPTY = "";
    private static final String KIEV_IMAGES_IC = "images/ic-kiev.png";

    private int playerScore = 0;
    private int computerScore = 0;
    private String lastWord = STRING_EMPTY;
    private boolean loseProperties = false;

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
        errorLabel.setText(STRING_EMPTY);
        if (inputTextTextField.getText().isEmpty()) {
            errorWindow(EMPTY_FIELD);
        } else {
            verification(getCustomerCity());
        }
        inputTextTextField.clear();
    }

    private void verification(final String value) {
        boolean inCollection;
        boolean endChar;
        String urlImage;
        if (END_GAME.equalsIgnoreCase(value.trim())) {
            loseProperties = true;
            endWindow(playerScore, computerScore);
        } else {
            endChar = this.isEndChar(value);
            inCollection = cityDatabase.contain(value);
            showErrorMessage(inCollection, endChar);
            if (endChar && inCollection) {
                playerScore++;
                try {
                    urlImage = String.valueOf(cityDatabase.getCityEmblemUrl(getCustomerCity()));
                } catch (MalformedURLException ex) {
                    urlImage = STRING_EMPTY;
                }
                cityDatabase.remove(value);
                String result = searchWord(value.toCharArray()[value.length() - 1]);
                if (result.length() > 0) {
                    computerAction(result, urlImage);
                } else {
                    endWindow(playerScore, computerScore);
                }
            }
        }
    }

    private void computerAction(final String ComputerWord, final String urlImagePlayer) {
        setImageEmblem(getCustomerCity(), urlImagePlayer, customerCityEmblemImageView);
        pcCityLabel.setText(STRING_EMPTY);
        computerScore++;
        lastWord = ComputerWord;
        String urlImageComputer;
        try {
            urlImageComputer = String.valueOf(cityDatabase.getCityEmblemUrl(ComputerWord));
        } catch (IOException ex) {
            urlImageComputer = STRING_EMPTY;
        }
        setImageEmblem(ComputerWord, urlImageComputer, pcCityEmblemImageView);
        pcCityLabel.setText(ComputerWord);
        cityDatabase.remove(ComputerWord);
        prevCityLabel.setText(getCustomerCity());
    }

    private void setImageEmblem(final String cityName, final String cityImageURL, final ImageView image) {
        try {
            File file = new File(getImgCity(cityName, cityImageURL));
            image.setImage(new Image(String.valueOf(file.toURI())));
        } catch (IOException ex) {
            URL iconURL = getClass().getResource(KIEV_IMAGES_IC);
            Image icon = new Image(Objects.requireNonNull(iconURL).toExternalForm());
            image.setImage(icon);
        }
    }

    private String getImgCity(String name, String url) throws IOException {
        File folderImage = new File(PATH_IMAGES);
        if (!folderImage.exists()) {
            folderImage.mkdir();
        }
        File folder = new File(PATH_IMAGES);
        File[] files = folder.listFiles();
        for (File img : files) {
            if (img.getName().equalsIgnoreCase(name + ".png")) {
                return PATH_IMAGES + "/" + img.getName();
            }
        }
        if (url.length() > 0) {
            downloadFile(url, PATH_IMAGES + "/" + name + ".png");
            return PATH_IMAGES + "/" + name + ".png";
        } else {
            return "";
        }
    }

    private static void downloadFile(String url, String fileName) throws IOException {
        URL newUrl = new URL(url);
        try (InputStream in = newUrl.openStream();
             BufferedInputStream bis = new BufferedInputStream(in);
             FileOutputStream fos = new FileOutputStream(fileName)) {

            byte[] data = new byte[1024];
            int count;
            while ((count = bis.read(data, 0, 1024)) != -1) {
                fos.write(data, 0, count);
            }
        }
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
        String result = STRING_EMPTY;

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
        errorLabel.setText(STRING_EMPTY);
        errorLabel.setText(errorMessage);
    }

    private String getCustomerCity() {
        return inputTextTextField.getText();
    }

    private void dropToDefault() {
        cityDatabase = new CityDatabase();
        lastWord = STRING_EMPTY;
        playerScore = 0;
        computerScore = 0;
        customerCityEmblemImageView.setImage(null);
        pcCityEmblemImageView.setImage(null);
        finalViewVbox.setVisible(false);
        gameAreaGridPane.setVisible(true);
        prevCityLabel.setText(STRING_EMPTY);
        pcCityLabel.setText(STRING_EMPTY);
    }

    private void finalMessage(final int customerScore, final int computerScore) {
        if (customerScore == computerScore) {
            if (loseProperties) {
                finalMessageLabel.setText(LOSE);
            } else {
                finalMessageLabel.setText(TIE);
            }
        } else if (customerScore > computerScore) {
            finalMessageLabel.setText(VICTORY);
        } else finalMessageLabel.setText(LOSE);
    }
}
