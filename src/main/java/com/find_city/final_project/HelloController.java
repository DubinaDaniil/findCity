package com.find_city.final_project;

import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;

import java.net.*;
import java.util.*;

public class HelloController implements Initializable {

    private static final String VICTORY = "ПЕРЕМОГА!!!";
    private static final String LOSE = "Ти програв!!!";

    @FXML
    private GridPane gameAreaGridPane;
    @FXML
    private Button nextCityButton;
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

    URL imageURL = getClass().getResource("images/ic-kiev.png");
    Image image = new Image(Objects.requireNonNull(imageURL).toExternalForm());

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
        customerCityEmblemImageView.setImage(image);
        pcCityEmblemImageView.setImage(image);
    }

    @FXML
    public void onPlayAgain() {

    }

    @FXML
    public void onExit() {
    }
}