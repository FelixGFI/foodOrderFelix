package com.example.demo.foodOrder.gui;

import javafx.scene.control.TextField;

public class ValidierungsDaten {

    private int fehlercode;
    private TextField focusedFehler;
    private boolean valide;

    public ValidierungsDaten(int fehlercode, TextField focusedFehler, boolean valide) {
        this.fehlercode = fehlercode;
        this.focusedFehler = focusedFehler;
        this.valide = valide;
    }

    public int getFehlercode() {
        return fehlercode;
    }

    public TextField getFocusedFehler() {
        return focusedFehler;
    }

    public boolean isValide() {
        return valide;
    }
}
