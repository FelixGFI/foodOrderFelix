package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    int zahlInt = 0;
    int ursprungsWaert = 0;
    @FXML
    private Label zahl = new Label();
    @FXML
    private TextField zahlEingabe = new TextField();
    @FXML
    private TextField bezeichnungEingabe;
    @FXML
    private Button fertig = new Button();
    @FXML
    private Label bezeichnung;
    @FXML
    private Button plus = new Button();
    @FXML
    private Button reset = new Button();
    @FXML
    private Button minus = new Button();
    @FXML
    private HBox eingabeBox = new HBox();

    private Font font1 = Font.font("Arial", 100);
    private Font font3 = Font.font("Arial", 50);
    private Font font2 = Font.font("Arial", 30);
    private Font font4 = Font.font("Arial",1);

    @FXML
    public void initialize() throws IOException {

        zahlEingabe.setText("0");
        bezeichnungEingabe.setText("Bezeichnung");

        zahl.setVisible(false);
        plus.setVisible(false);
        reset.setVisible(false);
        minus.setVisible(false);
        bezeichnung.setVisible(false);

        zahlEingabe.setMaxWidth(120);
        fertig.setMaxWidth(120);
        zahlEingabe.setFont(font2);
        fertig.setFont(font2);
        bezeichnungEingabe.setFont(font2);

        plus.setFont(font3);
        reset.setFont(font3);
        minus.setFont(font3);
        bezeichnung.setFont(font3);

        plus.setFont(font4);
        reset.setFont(font4);
        minus.setFont(font4);
        bezeichnung.setFont(font4);
    }

    @FXML
    protected void onPlusButtonClick() {
        zahlInt++;
        zahl.setText(zahlInt + "");
    }
    @FXML
    protected void onResetButtonClick() {
        zahlInt = ursprungsWaert;
        zahl.setText(zahlInt + "");
    }
    @FXML
    protected void onMinusButtonClick() {
        zahlInt--;
        zahl.setText(zahlInt + "");
    }
    @FXML
    protected void onFertigButtonClick() {
        zahl.setText(zahlEingabe.getText());
        reset.setText(zahlEingabe.getText());
        bezeichnung.setText(bezeichnungEingabe.getText());

        this.zahlInt = Integer.valueOf(zahlEingabe.getText());
        this.ursprungsWaert = Integer.valueOf(zahlEingabe.getText());

        zahl.setVisible(true);
        plus.setVisible(true);
        reset.setVisible(true);
        minus.setVisible(true);
        bezeichnung.setVisible(true);

        zahlEingabe.setVisible(false);
        fertig.setVisible(false);
        bezeichnungEingabe.setVisible(false);
        eingabeBox.setSpacing(0);
        eingabeBox.setVisible(false);

        zahlEingabe.setFont(font4);
        fertig.setFont(font4);

        zahl.setFont(font1);
        plus.setFont(font3);
        reset.setFont(font3);
        minus.setFont(font3);
        bezeichnung.setFont(font3);
    }
}