package com.example.demo.foodOrder.logic.classes;

public class Bestellung {

    private final Person p;
    private Gericht mon;
    private Gericht die;
    private Gericht mit;
    private Gericht don;
    private Gericht fre;

    public Person getP() {
        return p;
    }

    public Gericht getMon() {
        return mon;
    }

    public Gericht getDie() {
        return die;
    }

    public Gericht getMit() {
        return mit;
    }

    public Gericht getDon() {
        return don;
    }

    public Gericht getFre() {
        return fre;
    }

    public void setMon(Gericht mon) {
        this.mon = mon;
    }

    public void setDon(Gericht don) {
        this.don = don;
    }

    public void setFre(Gericht fre) {
        this.fre = fre;
    }

    public void setDie(Gericht die) {
        this.die = die;
    }

    public void setMit(Gericht mit) {
        this.mit = mit;
    }

    public Bestellung(Person p, Gericht mon, Gericht die, Gericht mit, Gericht don, Gericht fre) {

        if(p == null) {
            throw new BestellungsException("Invalid Bestellung: Person ist Null");
        }
        this.p = p;
        p.setBestellung(this);

        this.mon = mon;
        this.die = die;
        this.mit = mit;
        this.don = don;
        this.fre = fre;
        gerichtBestellungPruefenUndHinzufuegen(mon, p);
        gerichtBestellungPruefenUndHinzufuegen(die, p);
        gerichtBestellungPruefenUndHinzufuegen(mit, p);
        gerichtBestellungPruefenUndHinzufuegen(don, p);
        gerichtBestellungPruefenUndHinzufuegen(fre, p);
    }
    public void gerichtBestellungPruefenUndHinzufuegen(Gericht gericht, Person person) {
        if (gericht != null) {
            gericht.bestelle(person);
        }
    }
    public String toString() {
        String string = this.getP().toString() + ": ";
        string += (mon != null) ? this.getMon().getBezeichnung() + " | " : "heute keine Bestellung | ";
        string += (die != null) ? this.getDie().getBezeichnung() + " | " : "heute keine Bestellung | ";
        string += (mit != null) ? this.getMit().getBezeichnung() + " | " : "heute keine Bestellung | ";
        string += (don != null) ? this.getDon().getBezeichnung() + " | " : "heute keine Bestellung | ";
        string += (fre != null) ? this.getFre().getBezeichnung() + " | " : "heute keine Bestellung | ";
        return string;
    }
}
