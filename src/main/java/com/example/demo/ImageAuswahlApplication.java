package com.example.demo;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
/*"C:\\JavaBilder\\Flunder.jpg"*/
public class ImageAuswahlApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Button b = new Button("auswahl");

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Media Files", "*.png", "*.jpg")
        );
        File file = fileChooser.showOpenDialog(stage);

        //File file = new File("C:\\JavaBilder\\Flunder.jpg");
        FileInputStream input = new FileInputStream(file);
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(350);
        imageView.setFitWidth(500);


        b.getStyleClass().add("button2");

        Label l = new Label("das ist ein Label");
        Label l2 = new Label("noch ein Label");
        l2.getStyleClass().add("label2");
        System.out.println(l.getStyleClass());
        System.out.println(l2.getStyleClass());
        System.out.println(b.getStyleClass());

        VBox vBox = FXMLLoader.load(getClass().getResource("imageAuswahl.fxml"));
        vBox.getChildren().add(b);
        vBox.getChildren().add(imageView);
        vBox.getChildren().add(l);
        vBox.getChildren().add(l2);
        vBox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vBox, 600, 600);
        ObservableList<String> list = scene.getStylesheets();
        list.add(getClass().getResource("stylesheet.css").toExternalForm());
        stage.setTitle("Titel");
        stage.setScene(scene);
        stage.show();

        b.setOnAction(actionEvent -> {
            File file2 = fileChooser.showOpenDialog(stage);
            FileInputStream input2 = null;
            try {
                input2 = new FileInputStream(file2);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Image image2 = new Image(input2);
            imageView.setImage(image2);
        });


    }



    public static void main(String[] args) {
        launch();
    }

}

