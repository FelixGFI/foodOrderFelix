package com.example.demo.foodOrder.logic.classes;

import java.util.ArrayList;

public class Person {

    private final String firstName;
    private final String lastName;
    private Bestellung bestellung;
    private double gesamtWert;
    private static final ArrayList<Person> allPersons = new ArrayList<>();

    public void setBestellung(Bestellung bestellung) {

        if (this.bestellung == null) {
            this.bestellung = bestellung;
        } else {
            throw new BestellungsException("Doppelbestellung: Es wurde schon bestellt");
        }
    }

    public double getGesamtWert() {
        this.gesamtWert = 0;
        if (this.getBestellung() != null) {
            if (this.bestellung.getMon() != null) {
                this.gesamtWert += this.bestellung.getMon().getPreis();
            }
            if (this.bestellung.getDie() != null) {
                this.gesamtWert += this.bestellung.getDie().getPreis();
            }
            if (this.bestellung.getMit() != null) {
                this.gesamtWert += this.bestellung.getMit().getPreis();
            }
            if (this.bestellung.getDon() != null) {
                this.gesamtWert += this.bestellung.getDon().getPreis();
            }
            if (this.bestellung.getFre() != null) {
                this.gesamtWert += this.bestellung.getFre().getPreis();
            }
        }
        return this.gesamtWert;
    }

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gesamtWert = 0;
        allPersons.add(this);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Bestellung getBestellung() {
        return bestellung;
    }

    public static ArrayList<Person> getAllPersons() {
        return allPersons;
    }
    public static String getKostenListString() {
        StringBuilder KostenListString = new StringBuilder();
        KostenListString.append("Kostenauflistung aller Bestellungen:\n");
        for (Person p : getAllPersons()) {
            KostenListString.append(String.format("%-25s", p.getLastName())).append(p.getGesamtWert()).append("\n");
        }
        return KostenListString.toString();
    }

    public String getRechnung() {
        String rechnung = "Rechnung\n\n";
        rechnung += "Person: " + this.getLastName() + ", " + this.getFirstName() + "\n";
        if (this.getBestellung() != null) {
            Bestellung b = this.getBestellung();
            if (b.getMon() != null) {
                rechnung += String.format("%-25s", b.getMon().getBezeichnung()) + b.getMon().getPreis() + "\n";
            }
            if (b.getDie() != null) {
                rechnung += String.format("%-25s", b.getDie().getBezeichnung()) + b.getDie().getPreis() + "\n";
            }
            if (b.getMit() != null) {
                rechnung += String.format("%-25s", b.getMit().getBezeichnung()) + b.getMit().getPreis() + "\n";
            }
            if (b.getDon() != null) {
                rechnung += String.format("%-25s", b.getDon().getBezeichnung()) + b.getDon().getPreis() + "\n";
            }
            if (b.getFre() != null) {
                rechnung += String.format("%-25s", b.getFre().getBezeichnung()) + b.getFre().getPreis() + "\n";
            }
        } else {
            rechnung += "\n-----\n";
        }

        rechnung += "\nGesamtbetrag " + this.getGesamtWert() + "\n";
        return rechnung;
    }

    public String toString() {
        return this.getFirstName() + " " + this.getLastName();
    }
}
