package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Scanner sc = new Scanner(System.in);
        int anzahlFenster = sc.nextInt();
        for (int i = 0; i < anzahlFenster; i++) {
            Stage stage2 = new Stage();
            FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
            showNewStage(stage2, fxmlLoader2, "Zähler " + (i + 1));
        }
    }

    private void showNewStage(Stage stage, FXMLLoader fxmlLoader, String s) throws IOException {
        VBox vBox = fxmlLoader.load();
        Scene scene = new Scene(vBox, 600, 400);
        stage.setTitle(s);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}