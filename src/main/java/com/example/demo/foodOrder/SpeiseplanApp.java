package com.example.demo.foodOrder;

import com.example.demo.SpeiseplanController;
import com.example.demo.foodOrder.logic.classes.Gericht;
import com.example.demo.foodOrder.logic.classes.Speiseplan;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class SpeiseplanApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        ArrayList<Gericht> monList = new ArrayList<>(Arrays.asList(
                new Gericht("Pommes (Montag)", 3, "C:\\JavaBilder\\Forelle.jpg"),
                new Gericht("Pommes mit Mayo", 3, "C:\\JavaBilder\\Forelle.jpg"),
                new Gericht("Pommes mit Ketchup", 3, "C:\\JavaBilder\\Flunder.jpg")));

        ArrayList<Gericht> dieList= new ArrayList<>(Arrays.asList(
                new Gericht("Pommes (Dienstag)", 3, "C:\\JavaBilder\\Forelle.jpg"),
                new Gericht("Pommes mit Mayo", 3, "pommes.png")));


        ArrayList<Gericht> mitList = new ArrayList<>(Arrays.asList(
                new Gericht("Pommes (Mit)", 3, "pommes.png"),
                new Gericht("Pommes mit Mayo", 3, "pommes.png"),
                new Gericht("Pommes mit Ketchup", 3, "pommes.png")));

        ArrayList<Gericht> donList = new ArrayList<>(Arrays.asList());


        ArrayList<Gericht> freList = new ArrayList<>(Arrays.asList(
                new Gericht("Pommes (Fre)", 3, "pommes.png"),
                new Gericht("Pommes mit Mayo", 3, "pommes.png")));

        Speiseplan sp = new Speiseplan(45, monList, dieList, mitList, null, freList);

        sp = SpeiseplanController.showDialog(sp);

        System.out.println(sp);


    }

    public static void main(String[] args) {
        launch();
    }

}
