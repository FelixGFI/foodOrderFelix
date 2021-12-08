package com.example.demo.foodOrder.logic;

import java.io.Serializable;
import java.util.ArrayList;

public class Speiseplan implements Serializable {

    private final int kw;
    private final ArrayList<Gericht> mon;
    private final ArrayList<Gericht> die;
    private final ArrayList<Gericht> mit;
    private final ArrayList<Gericht> don;
    private final ArrayList<Gericht> fre;

    public Speiseplan(int kw, ArrayList<Gericht> mon, ArrayList<Gericht> die, ArrayList<Gericht> mit, ArrayList<Gericht> don, ArrayList<Gericht> fre) {
        this.mon = mon;
        this.die = die;
        this.mit = mit;
        this.don = don;
        this.fre = fre;
        this.kw = kw;
    }

    public ArrayList<Person> gerichtAendern(Gericht gerichtAlt, Gericht gerichtNeu) {

        if (gerichtAlt == null || gerichtNeu == null) {
            throw new SpeiseplanException("Invalid Gericht: Eines der Übergebenen Gerichte ist Null");
        }

        if (gerichtBereitsImSpeiseplan(gerichtNeu)) {
            throw new SpeiseplanException("invalid Gericht: Gericht ist bereits an einem Andren Tag vorhanden");
        }

        boolean gerichtImSpeiseplan = ersetzeGericht(gerichtAlt, gerichtNeu);
        if (!gerichtImSpeiseplan) {
            throw new SpeiseplanException("Invalid Gericht: zu änderndes Gericht ist nicht vorhanden");
        }

        entferneUngueltigeBestellungen(gerichtAlt);

        return gerichtAlt.getPersonenListe();

    }

    public boolean ersetzeGericht(Gericht gerichtAlt, Gericht gerichtNeu) {
        boolean gerichtImSpeiseplan = false;

        if (mon.contains(gerichtAlt)) {
            mon.set(mon.indexOf(gerichtAlt), gerichtNeu);
            gerichtImSpeiseplan = true;
       } else if (die.contains(gerichtAlt)) {
            die.set(die.indexOf(gerichtAlt), gerichtNeu);
            gerichtImSpeiseplan = true;
        } else if (mit.contains(gerichtAlt)) {
            mit.set(mit.indexOf(gerichtAlt), gerichtNeu);
            gerichtImSpeiseplan = true;
        } else if (don.contains(gerichtAlt)) {
            don.set(don.indexOf(gerichtAlt), gerichtNeu);
            gerichtImSpeiseplan = true;
        } else if (fre.contains(gerichtAlt)) {
            fre.set(fre.indexOf(gerichtAlt), gerichtNeu);
            gerichtImSpeiseplan = true;
        }
        return gerichtImSpeiseplan;
    }

    public void entferneUngueltigeBestellungen(Gericht gerichtAlt) {

        for (Person p : gerichtAlt.getPersonenListe()) {
            Bestellung b = p.getBestellung();
            if (b.getMon() == gerichtAlt) {
                b.setMon(null);
            } else if (b.getDie() == gerichtAlt) {
                b.setDie(null);
            } else if (b.getMit() == gerichtAlt) {
                b.setMit(null);
            } else if (b.getDon() == gerichtAlt) {
                b.setDon(null);
            } else if (b.getFre() == gerichtAlt) {
                b.setFre(null);
            }
        }
    }

    public boolean gerichtBereitsImSpeiseplan(Gericht gerichtNeu) {
        return mon.contains(gerichtNeu) ||
                die.contains(gerichtNeu) ||
                mit.contains(gerichtNeu) ||
                don.contains(gerichtNeu) ||
                fre.contains(gerichtNeu);
    }

    public ArrayList<Gericht> getMon() {return mon;}

    public ArrayList<Gericht> getDie() {return die;}

    public ArrayList<Gericht> getMit() {return mit;}

    public ArrayList<Gericht> getDon() {return don;}

    public ArrayList<Gericht> getFre() {return fre;}

    public int getKw() {return kw;}
}
