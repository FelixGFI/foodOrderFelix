package com.example.demo;

import javafx.scene.control.TextField;

public class validierungsDaten {

    private int fehlercode;
    private TextField focusedFehler;
    private boolean valide;

    public validierungsDaten(int fehlercode, TextField focusedFehler, boolean valide) {
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
