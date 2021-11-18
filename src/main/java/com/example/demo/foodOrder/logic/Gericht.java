package com.example.demo.foodOrder.logic;

import java.util.ArrayList;

public class Gericht {

    private final String bezeichnung;
    private final double preis;
    private int bestellteMenge;
    private String gerichtBildPath;

    /** Merkt sich, welche Personen das Gericht bestellt haben */
    private final ArrayList<Person> personenListe = new ArrayList<>();

    public Gericht(String bezeichnung, double preis) {
        this.bezeichnung = bezeichnung;
        this.preis = preis;
        this.bestellteMenge = 0;
        int x = 0;
    }
    public Gericht(String bezeichnung, double preis, String gerichtBildPath) {
        this.bezeichnung = bezeichnung;
        this.preis = preis;
        this.bestellteMenge = 0;
        this.gerichtBildPath = gerichtBildPath;
    }

    public void bestelle(Person p) {
        bestellteMenge++;
        personenListe.add(p);
    }

    public int getBestellteMenge() {return bestellteMenge;}

    public String getBezeichnung() {return bezeichnung;}

    public double getPreis() {return preis;}

    public ArrayList<Person> getPersonenListe() {return personenListe;}

    public String toString() {return bezeichnung + ", " + preis;}

    public String getGerichtBildPath() {return gerichtBildPath;}

    public void setGerichtBildPath(String gerichtBildPath) {this.gerichtBildPath = gerichtBildPath;}
}
