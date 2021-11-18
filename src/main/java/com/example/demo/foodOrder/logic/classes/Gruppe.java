package com.example.demo.foodOrder.logic.classes;

import java.util.ArrayList;

public class Gruppe {
    private final String bezeichnung;
    private final ArrayList<Person> mitgliederList;

    public String getBezeichnung() {
        return bezeichnung;
    }

    public ArrayList<Person> getMitgliederList() {
        return mitgliederList;
    }

    public String getMitgliederListeAsString() {
    StringBuilder gruppenliste = new StringBuilder();
    gruppenliste.append("Mitgliederliste:\n");
        for (Person p : this.getMitgliederList()) {
            gruppenliste.append(p.getLastName()).append(", ").append(p.getFirstName()).append("\n");
        }
    return gruppenliste.toString();
    }

    public Gruppe(String bezeichnung, ArrayList<Person> mitgliederList) {
        this.bezeichnung = bezeichnung;
        this.mitgliederList = mitgliederList;
    }

    public ArrayList<Bestellung> getBestellungenList() {
        ArrayList<Bestellung> bArr = new ArrayList<>();
        for(Person p : getMitgliederList()) {
            if(p.getBestellung() != null) {
                bArr.add(p.getBestellung());
            }
        }
        return bArr;
    }
    public String listBestellungen() {
        StringBuilder string = new StringBuilder();
        string.append("Liste aller Bestellungen der Gruppe:\n");
        for(Bestellung b : getBestellungenList()) {
                string.append(b.toString()).append("\n");
        }
        return string.toString();
    }
    public void addMitglied(Person p) {
        if(!this.mitgliederList.contains((p))) {
            this.mitgliederList.add(p);
        } else {
         throw new GruppeException("Invalid Person: Person ist bereits Teil der Gruppe und kann nicht erneut hinzugefügt werden");
        }
    }
    public void removeMitglied(Person p) {
        if(!this.mitgliederList.contains(p)) {
            throw new GruppeException("Invalid Person: Person ist nicht Teil der Gruppe und kann deswegen nicht entfernt werden");
        } else {
            this.mitgliederList.remove(p);
        }
    }
}
