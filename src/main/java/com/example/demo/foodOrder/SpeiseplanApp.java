package com.example.demo.foodOrder;

import com.example.demo.foodOrder.gui.SpeiseplanController;
import com.example.demo.foodOrder.logic.Gericht;
import com.example.demo.foodOrder.logic.Speiseplan;
import com.example.demo.foodOrder.output.PDFCreator;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;



public class SpeiseplanApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Speiseplan sp = new Speiseplan(-1, null, null, null, null, null);
        sp = SpeiseplanController.showDialog(sp);
    }

    public static void main(String[] args) {
        launch();
    }

}
