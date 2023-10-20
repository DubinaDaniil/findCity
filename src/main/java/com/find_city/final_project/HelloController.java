package com.find_city.final_project;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class HelloController {
    @FXML
    private GridPane gameArea;
    @FXML
    private Button nextCity;
    @FXML
    private TextField inputText;
    @FXML
    private Label pcCity;

    @FXML
    public void onHelloButtonClick() {
        gameArea.setVisible(true);
    }
}