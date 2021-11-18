package com.example.demo.foodOrder.logic.classes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PersonTest {
    /**
     * A01. Das Programm soll es ermöglichen, Personen mit Vornamen und Nachname anzulegen.
     */
    @Test
    void testCreatePerson() {

        Person p = new Person("Peter", "Meier");

    }
    /**
     * A02. Das Programm soll es ermöglichen, für eine Woche (Montag bis Freitag)
     * einen Speiseplan mit für jeden Tag mehreren möglichen Optionen einzugeben.
     * <p>
     * A04. Der Speisplan soll auch funktionieren, wenn einzelne Tage ausfallen, z.B. wegen eines Feiertags.
     */

    @Test
    void testCreateSpeiseplan() {
        erzeugeSpeiseplan();
        Speiseplan s;
        ArrayList<Gericht> mon = new ArrayList<>(
                Arrays.asList(new Gericht("Veganes Schnitzel", 5.0), new Gericht("Forelle", 10.0)));

        ArrayList<Gericht> die = new ArrayList<>(
                Arrays.asList(new Gericht("Wels", 15.0), new Gericht("Tortellini", 7.0)));

        ArrayList<Gericht> don = new ArrayList<>(
                Arrays.asList(new Gericht("Steak", 12.0), new Gericht("Champignons", 7.0)));

        ArrayList<Gericht> fre = new ArrayList<>(
                Arrays.asList(new Gericht("Spaghetti", 7.0), new Gericht("Ravioli", 7.0)));
        s = new Speiseplan(16, mon, die, null, don, fre);
        Person p = new Person("Gustav", "Gustavson");
        Assertions.assertThrows(NullPointerException.class, () -> new Bestellung(p, s.getMon().get(0), s.getDie().get(0), s.getMit().get(0), s.getDon().get(0), s.getFre().get(0)));
    }

    public static Speiseplan erzeugeSpeiseplan() {

        Speiseplan s;
        ArrayList<Gericht> mon = new ArrayList<>(
                Arrays.asList(new Gericht("Veganes Schnitzel", 5.0, "C:\\JavaBilder\\veganesSchnitzel.jpg"), new Gericht("Forelle", 10.0, "C:\\JavaBilder\\Forelle.jpg")));

        ArrayList<Gericht> die = new ArrayList<>(
                Arrays.asList(new Gericht("Wels", 15.0, "C:\\JavaBilder\\Wels.jpg"), new Gericht("Tortellini", 7.0, "C:\\JavaBilder\\Tortellini.jpg")));

        ArrayList<Gericht> mit = new ArrayList<>(
                Arrays.asList(new Gericht("Gemüsepfanne", 6.0, "C:\\JavaBilder\\Gemüsepfanne.jpg"), new Gericht("Bratkartoffeln", 7.0)));

        ArrayList<Gericht> don = new ArrayList<>(
                Arrays.asList(new Gericht("Steak", 12.0), new Gericht("Champignons", 7.0, "C:\\JavaBilder\\Champingons.jpg")));

        ArrayList<Gericht> fre = new ArrayList<>(
                Arrays.asList(new Gericht("Spaghetti", 7.0, "C:\\JavaBilder\\Spagetti.jpg"), new Gericht("Ravioli", 7.0, "C:\\JavaBilder\\Ravioli.jpg")));

        return new Speiseplan(15, mon, die, mit, don, fre);
    }
    /**
     * A03. Das Programm soll es ermöglichen, für jede Speise einen Preis festzulegen.
     */
    @Test
    void testPreisfestlegen() {

        new Gericht("Rumpsteak", 20.0);
    }
    /**
     * A05. Das Programm soll es ermöglichen, dass jede Person für einen
     * Tag eine Speise aus dem Speiseplan auswählen kann.
     */

    @Test
    void testBestelleSpeise() {

        Speiseplan s = erzeugeSpeiseplan();
        ArrayList<Gericht> mon = s.getMon();
        ArrayList<Gericht> die = s.getDie();
        ArrayList<Gericht> mit = s.getMit();
        ArrayList<Gericht> don = s.getDon();
        ArrayList<Gericht> fre = s.getFre();

        Person p = new Person("Franz", "Müller");
        Bestellung b = new Bestellung(p, mon.get(0), die.get(1), mit.get(0), don.get(1), null);

        Person q = new Person("Franziska", "Müller");
        Bestellung b2 = new Bestellung(q, mon.get(0), die.get(0), mit.get(0), don.get(0), fre.get(0));

        Assertions.assertAll(
                () -> Assertions.assertEquals(2, mon.get(0).getBestellteMenge()),
                () -> Assertions.assertEquals(0, mon.get(1).getBestellteMenge()),
                () -> Assertions.assertEquals(1, die.get(0).getBestellteMenge()),
                () -> Assertions.assertEquals(1, die.get(1).getBestellteMenge()),
                () -> Assertions.assertEquals(2, mit.get(0).getBestellteMenge()),
                () -> Assertions.assertEquals(0, mit.get(1).getBestellteMenge()),
                () -> Assertions.assertEquals(1, don.get(0).getBestellteMenge()),
                () -> Assertions.assertEquals(1, don.get(1).getBestellteMenge()),
                () -> Assertions.assertEquals(1, fre.get(0).getBestellteMenge()),
                () -> Assertions.assertEquals(0, fre.get(1).getBestellteMenge())
        );

    }
    /**
     * A06. Das Programm soll es ermöglichen, in Form einer Liste für alle Speisen die
     * Anzahl der jeweiligen Bestellungen aufzulisten (für die Küche, um die Zutaten zu kaufen).
     */
    @Test
    void testListeAnzahlBestellungen() {

        Speiseplan s = erzeugeSpeiseplan();
        Gericht montagsGericht = s.getMon().get(0);
        int anzahl = montagsGericht.getBestellteMenge();
        Assertions.assertEquals(0, anzahl);
        Person p = new Person("Franz", "Müller");
        Bestellung b1 = new Bestellung(p, montagsGericht, null, null, null, null);
        Assertions.assertEquals(1, montagsGericht.getBestellteMenge());

        Person q = new Person("Lieschen", "Müller");
        Bestellung b2 = new Bestellung(q, montagsGericht, s.getDie().get(1), null, null, null);

        Person l = new Person("Anja", "Lorenz");
        Bestellung b3 = new Bestellung(l, null, null, null, null, null);

        Assertions.assertThrows(BestellungsException.class, () -> new Bestellung(l, s.getMon().get(0), s.getDie().get(0),
                s.getMit().get(0), s.getDon().get(0), s.getFre().get(0)));


        Assertions.assertEquals(2, s.getMon().get(0).getBestellteMenge());

        Assertions.assertThrows(BestellungsException.class,
                () -> new Bestellung(l, s.getMon().get(1), null, null, null, null));
    }
    /**
     * A07. Das Programm soll es ermöglichen, eine Liste der pro Person aufgelaufenen Kosten
     * für die gesamte Woche auszugeben. Diese Liste wird von der Buchhaltung benötigt,
     * um die Kosten in Rechnung zu stellen.
     * <p>
     * A08. Das Programm soll es ermöglichen, für jede Person eine Rechnung
     * mit den in der Woche bestellten Speisen, deren Preis und dem Gesamtpreis
     * zu erstellen. Diese Liste ist für die Kunden gedacht, um ihre Bestellungen
     * zu kontrollieren.
     */
    @Test
    void testKostenauflistung() {

        Speiseplan s = this.erzeugeSpeiseplan();
        Person vonJorvik = new Person("Jarl Harald", "von Jorvik");

        System.out.println(Person.getKostenListString());  // A07

        Person eckstrasser = new Person("Adolf", "Eckstrasser");
        Person steiner = new Person("Johannes", "Steiner");

        Bestellung pb = new Bestellung(vonJorvik, s.getMon().get(1), s.getDie().get(1), s.getMit().get(1), s.getDon().get(1), s.getFre().get(1));
        Bestellung p2b = new Bestellung(eckstrasser, s.getMon().get(1), null, s.getMit().get(1), s.getDon().get(0), s.getFre().get(0));

        Assertions.assertEquals(38, vonJorvik.getGesamtWert());
        Assertions.assertEquals(36, eckstrasser.getGesamtWert());
        Assertions.assertEquals(0, steiner.getGesamtWert());

        System.out.println(Person.getKostenListString());

        System.out.println(vonJorvik.getRechnung());
        System.out.println(eckstrasser.getRechnung());
        System.out.println(steiner.getRechnung());
    }
    /**
     * B01. Das Programm soll der Küche Änderungen am Speiseplan ermöglichen,
     * z.B. wenn Zutaten nicht vorhanden sind (keine frische Forelle im Großmarkt).
     * Wenn bereits existierende Bestellungen betroffen sind,
     * soll das Programm eine Liste der betroffenen Personen ausgeben,
     * damit diese benachrichtigt werden können.
     */
    @Test
    void testAenderungenKueche() {

        Speiseplan s = this.erzeugeSpeiseplan();

        Person p = new Person("Jarl Harald", "von Jorvik");
        Person p2 = new Person("Adolf", "Eckstrasser");
        Person p3 = new Person("Johannes", "Steiner");

        Bestellung pb = new Bestellung(p, s.getMon().get(1), s.getDie().get(1), s.getMit().get(1), s.getDon().get(1), s.getFre().get(1));
        Bestellung p2b = new Bestellung(p2, s.getMon().get(1), null, s.getMit().get(1), s.getDon().get(0), s.getFre().get(0));



        Gericht alt = s.getMon().get(1);
        Gericht gNeu = new Gericht("Schupfnudeln mit Bratensoße", 3);
        ArrayList<Person> pArr = new ArrayList<>(Arrays.asList(p, p2));
        Assertions.assertEquals(alt, s.getMon().get(1));
        Assertions.assertEquals(pArr, s.gerichtAendern(s.getMon().get(1), gNeu));
        Assertions.assertEquals(gNeu, s.getMon().get(1));

        Gericht alt2 = s.getDon().get(0);
        Gericht neu2 = new Gericht("Königsberger Kloppse", 7.25);
        ArrayList<Person> pArr2 = new ArrayList<>(List.of(p2));
        Assertions.assertEquals(alt2, s.getDon().get(0));
        Assertions.assertEquals(pArr2, s.gerichtAendern(alt2, neu2));
        Assertions.assertEquals(neu2, s.getDon().get(0));

        //Sollte eine Exception werfen, weil gNeu bereits im Speiseplan vorhanden ist (Doppeltes Gericht)
        Assertions.assertThrows(SpeiseplanException.class,
                () -> s.gerichtAendern(s.getMon().get(0), gNeu));

        //Sollte eine Exception werfen, weil das Gericht 'Calamari' nicht vorhanden ist
        Assertions.assertThrows(SpeiseplanException.class,
                () -> s.gerichtAendern(new Gericht("Calamari", 1), gNeu));

        Assertions.assertNull(p.getBestellung().getMon());
        Gericht gNeu2 = new Gericht("Sauerkraut", 3.5);
        s.gerichtAendern(s.getFre().get(1), gNeu2);
        Assertions.assertEquals(gNeu2, s.getFre().get(1));

        Assertions.assertThrows(SpeiseplanException.class, () -> s.gerichtAendern(null, null));
        Assertions.assertThrows(SpeiseplanException.class, () -> s.gerichtAendern(new Gericht("Exception Test", 1), null));
        Assertions.assertThrows(SpeiseplanException.class, () -> s.gerichtAendern(null, new Gericht("Exception Test", 1)));
    }

    @Test
    void testMinorFunctionsOfSpeiseplan() {
        Speiseplan s = erzeugeSpeiseplan();
        /*
         * s.gerichtBereitsImSpeiseplan(Gericht)
         */
        Gericht apfelkompot = new Gericht("Apfelkompot", 3);
        Gericht veganesSchnitzel = s.getMon().get(0);
        Assertions.assertTrue(s.gerichtBereitsImSpeiseplan(s.getMit().get(0)));
        Assertions.assertFalse(s.gerichtBereitsImSpeiseplan(apfelkompot));
        Assertions.assertTrue(s.gerichtBereitsImSpeiseplan(veganesSchnitzel));
        /*
         * s.ersetzeGericht(Gericht alt, Gericht neu)
         */
        Assertions.assertEquals(veganesSchnitzel ,s.getMon().get(0));
        Assertions.assertTrue(s.ersetzeGericht(veganesSchnitzel, apfelkompot));
        Assertions.assertEquals(apfelkompot ,s.getMon().get(0));

        Assertions.assertTrue(s.ersetzeGericht(s.getDon().get(1) ,veganesSchnitzel));
        Assertions.assertEquals(veganesSchnitzel, s.getDon().get(1));

        Gericht honigpfannekuchen = new Gericht("Honigpfannekuchen", 5);
        Gericht schokopudding = new Gericht("schokopudding", 12);
        Assertions.assertFalse(s.ersetzeGericht(honigpfannekuchen, schokopudding));
        Assertions.assertEquals(veganesSchnitzel, s.getDon().get(1));
        /*
         * s.entferneUngueltigeBestellungen(Gericht);
         */
        Person stern = new Person("Ilse", "Stern");
        Person herrmann = new Person("Gerald", "Herrmann");
        Person bosko = new Person("Alfred", "Bosko");
        Bestellung bStern = new Bestellung(stern,
                s.getMon().get(0), s.getDie().get(1), null, s.getDon().get(1), null);
        Bestellung bHerrmann = new Bestellung(herrmann,
                null, s.getDie().get(1), null, s.getDon().get(1), s.getFre().get(1));
        Bestellung bBosko = new Bestellung(bosko,
                s.getMon().get(0), s.getDie().get(1), s.getMit().get(0), s.getDon().get(0), null);

        Gericht donGerichtA = s.getDon().get(0);
        Gericht donGerichtB = s.getDon().get(1);
        Gericht sternDonBestellung = stern.getBestellung().getDon();
        Gericht herrmannDonBestellung = herrmann.getBestellung().getDon();
        Gericht boskoDonBestellung = bosko.getBestellung().getDon();

        Assertions.assertEquals(sternDonBestellung, donGerichtB);
        Assertions.assertEquals(herrmannDonBestellung, donGerichtB);
        Assertions.assertEquals(boskoDonBestellung, donGerichtA);

        s.entferneUngueltigeBestellungen(donGerichtB);

        sternDonBestellung = stern.getBestellung().getDon();
        herrmannDonBestellung = herrmann.getBestellung().getDon();
        boskoDonBestellung = bosko.getBestellung().getDon();

        Assertions.assertNull(sternDonBestellung);
        Assertions.assertNull(herrmannDonBestellung);
        Assertions.assertEquals(boskoDonBestellung, donGerichtA);


        Gericht monGerichtA = s.getMon().get(0);
        Gericht monGerichtB = s.getMon().get(1);
        Gericht sternMonBestellung = stern.getBestellung().getMon();
        Gericht herrmannMonBestellung = herrmann.getBestellung().getMon();
        Gericht boskoMonBestellung = bosko.getBestellung().getMon();

        Assertions.assertEquals(sternMonBestellung, monGerichtA);
        Assertions.assertNull(herrmannMonBestellung);
        Assertions.assertEquals(boskoMonBestellung, monGerichtA);

        s.entferneUngueltigeBestellungen(monGerichtA);

        sternMonBestellung = stern.getBestellung().getMon();
        herrmannMonBestellung = herrmann.getBestellung().getMon();
        boskoMonBestellung = bosko.getBestellung().getMon();

        Assertions.assertNull(sternMonBestellung);
        Assertions.assertNull(herrmannMonBestellung);
        Assertions.assertNull(boskoMonBestellung);
    }
    /**
     * B02. Das Programm soll es Personen ermöglich, ihre Bestellung später zu ändern.
     */
    @Test
    void testBestellungAendern() {
        Speiseplan s = this.erzeugeSpeiseplan();
        Person p = new Person("Emanuel", "Eichstock");
        Person p2 = new Person("Sophia", "von Osthang-Lüdenscheid");
        Bestellung pb = new Bestellung(p, s.getMon().get(1), s.getDie().get(1), s.getMit().get(1), s.getDon().get(1), s.getFre().get(1));
        Bestellung p2b = new Bestellung(p2, s.getMon().get(1), null, s.getMit().get(1), s.getDon().get(0), s.getFre().get(0));
        p.getBestellung().setDie(s.getDie().get(0));
        Assertions.assertEquals(s.getDie().get(0), p.getBestellung().getDie());
    }
    /**
     * C01. Das Programm soll es ermöglichen, Gruppen von Personen zu definieren.
     * Die Gruppen sollen eine Bezeichnung haben. Diese Gruppen dienen dazu, z.B.
     * ein Ausbildungsjahr für eine bestimmte Ausbildung oder eine Gruppe
     * von Seminarleitern oder Dozenten zu verwalten für die gemeinsam
     * ein Speiseplan gedruckt wird.
     * <p>
     * C02. Das Programm soll es ermöglichen, eine Liste der Gruppen auszugeben.
     * <p>
     * C03. Das Programm soll es ermöglichen, alle Bestellungen
     * einer Gruppe auszugeben (für die Verteilung der Speisen).
     * <p>
     * C04. Das Programm soll es ermöglichen, nachträglich Personen zu Gruppen hinzuzufügen.
     * <p>
     * C05. Das Programm soll es ermöglichen, Personen aus Gruppen zu entfernen.
     */
    @Test
    void testErstelleGruppe() {
        //TODO split Test
        Speiseplan s = erzeugeSpeiseplan();
        Person p = new Person("Albert", "Drechsler");
        Person p2 = new Person("Gunther", "Sackheim");
        Person p3 = new Person("Elisabeth", "Südermüller");
        Bestellung pb = new Bestellung(p, s.getMon().get(1), s.getDie().get(1), s.getMit().get(1), s.getDon().get(1), s.getFre().get(1));
        Bestellung p2b = new Bestellung(p2, s.getMon().get(1), null, s.getMit().get(1), s.getDon().get(0), s.getFre().get(0));

        ArrayList<Person> pArr = new ArrayList<>(Arrays.asList(p, p2, p3));
        Gruppe g = new Gruppe("Gruppe1", pArr);

        Assertions.assertEquals(pArr, g.getMitgliederList());
        System.out.println(g.getMitgliederListeAsString());

        ArrayList<Bestellung> bArr = new ArrayList<>();
        bArr.add(p.getBestellung());
        bArr.add((p2.getBestellung()));
        Assertions.assertEquals(bArr, g.getBestellungenList());
        System.out.println(g.listBestellungen());

        Person p4 = new Person("Arne", "Kümmelbacher");

        Assertions.assertFalse(g.getMitgliederList().contains(p4));
        Assertions.assertEquals(3, g.getMitgliederList().size());
        g.addMitglied(p4);
        Assertions.assertTrue(g.getMitgliederList().contains(p4));

        Assertions.assertTrue(g.getMitgliederList().contains(p2));
        Assertions.assertEquals(4, g.getMitgliederList().size());
        g.removeMitglied(p2);
        Assertions.assertTrue(g.getMitgliederList().contains(p4));
        Assertions.assertEquals(3, g.getMitgliederList().size());

        Assertions.assertThrows(GruppeException.class, () -> g.addMitglied(p)); //Exception Person ist bereits in der Gruppe
        Assertions.assertThrows(GruppeException.class, () -> g.removeMitglied(p2)); //Exception Person ist gar nicht in der Gruppe

        g.addMitglied(p2);
        Assertions.assertTrue(g.getMitgliederList().contains(p2));
        Assertions.assertEquals(4, g.getMitgliederList().size());
        System.out.println(g.getMitgliederListeAsString());
        ArrayList<Person> pArr2 = new ArrayList<>(Arrays.asList(p, p3, p4, p2));
        Assertions.assertEquals(pArr2, g.getMitgliederList());

        Assertions.assertEquals(bArr, g.getBestellungenList());
        Bestellung p4b = new Bestellung(p4, s.getMon().get(1), s.getDie().get(0), null, null, s.getFre().get(0));
        bArr = new ArrayList<>(Arrays.asList(p.getBestellung(), p4.getBestellung(), p2.getBestellung()));
        Assertions.assertNotNull(p4.getBestellung());
        Assertions.assertEquals(bArr, g.getBestellungenList());
    }
    /**
     * Sicherstellen das für keine Person in einer Woche zwei Bestellungen aufggeben werden können
     */
    @Test
    void testZweimalBestellen() {
        Speiseplan s = erzeugeSpeiseplan();
        Person p = new Person("Eberhard", "Bergmeier");
        Bestellung pb = new Bestellung(p, s.getMon().get(0), s.getDie().get(0), s.getMit().get(0), s.getDon().get(0), s.getFre().get(0));
        Assertions.assertThrows(BestellungsException.class, () -> new Bestellung(p, s.getMon().get(1), s.getDie().get(1), s.getMit().get(1), s.getDon().get(1), s.getFre().get(1)));
        Assertions.assertThrows(BestellungsException.class, () -> new Bestellung(null, null, null, null, null, null));
        System.out.println(pb);
    }

}

