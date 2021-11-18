package com.example.demo;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class FXMLGerichtSpeicher {

    private Button bt;
    private TextField tfName;
    private TextField tfPreis;
    private ImageView im;
    private String tag;
    private String imPath;

    public FXMLGerichtSpeicher(Button bt, TextField tfName, TextField tfPreis, ImageView im, String imPath, String tag) {
        this.bt = bt;
        this.tfName = tfName;
        this.tfPreis = tfPreis;
        this.im = im;
        this.tag = tag;
        this.imPath = imPath;
    }

    public Button getBt() {return bt;}

    public void setBt(Button bt) {this.bt = bt;}

    public TextField getTfName() {return tfName;}

    public void setTfName(TextField tfName) {this.tfName = tfName;}

    public TextField getTfPreis() {return tfPreis;}

    public void setTfPreis(TextField tfPreis) {this.tfPreis = tfPreis;}

    public ImageView getIm() {return im;}

    public void setIm(ImageView im) {this.im = im;}

    public String getTag() {return tag;}

    public void setTag(String tag) { this.tag = tag;}

    public String getImPath() {return imPath;}

    public void setImPath(String imPath) {this.imPath = imPath;}
}
