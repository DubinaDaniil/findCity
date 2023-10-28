package com.find_city.final_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.stage.Stage;

import java.io.*;
import java.net.*;
import java.util.*;

public class FindCityGame extends Application {

    private static final String KIEV_IMAGE_IC = "images/ic-kiev.png";
    private static final String MAIN_VIEW_FXML = "main-view.fxml";
    private static final String MAIN_CSS = "styles/main.css";
    private static final String TITLE = "Мiста";

    final URL iconURL = getClass().getResource(KIEV_IMAGE_IC);
    final Image icon = new Image(Objects.requireNonNull(iconURL).toExternalForm());

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(MAIN_VIEW_FXML));
        Scene scene = new Scene(fxmlLoader.load(), 580, 260);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(MAIN_CSS)).toExternalForm());
        stage.setTitle(TITLE);
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}