package com.find_city.final_project;

import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;

import java.net.*;
import java.util.*;

public class HelloController implements Initializable {

    @FXML
    private GridPane gameArea;
    @FXML
    private Button nextCity;
    @FXML
    private TextField inputText;
    @FXML
    private Label pcCity;
    @FXML
    private VBox inviteVbox;
    @FXML
    private Label prevCity;
    @FXML
    private ImageView customerCityEmblem;
    @FXML
    private ImageView pcCityEmblem;

    URL imageURL = getClass().getResource("images/ic-kiev.png");
    Image image = new Image(Objects.requireNonNull(imageURL).toExternalForm());

    @FXML
    public void onStartGameButtonClick() {
        gameArea.setVisible(true);
        inviteVbox.setVisible(false);
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        gameArea.managedProperty().bind(gameArea.visibleProperty());
        inviteVbox.managedProperty().bind(inviteVbox.visibleProperty());
        customerCityEmblem.setImage(image);
        pcCityEmblem.setImage(image);
    }
}