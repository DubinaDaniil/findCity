package com.find_city.final_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.stage.Stage;

import java.io.*;
import java.net.*;
import java.util.*;

public class HelloApplication extends Application {
    URL iconURL = getClass().getResource("images/ic-kiev.png");
    Image icon = new Image(Objects.requireNonNull(iconURL).toExternalForm());

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 580, 260);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("styles/main.css")).toExternalForm());
        stage.setTitle("Мiста");
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}