package com.example.demo.foodOrder.output;

import com.example.demo.foodOrder.logic.classes.Gericht;
import com.example.demo.foodOrder.logic.classes.PersonTest;
import com.example.demo.foodOrder.logic.classes.Speiseplan;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class PDFCreatorTest {
    @Test
void testcreateSpeiseplanPDF() throws MalformedURLException, FileNotFoundException {
    Speiseplan sp1 = PersonTest.erzeugeSpeiseplan();
    Gericht g = new Gericht("Flache Flunder", 7.99, "C:\\JavaBilder\\Flunder.jpg");
    ArrayList<Gericht> gList = new ArrayList<>(Arrays.asList(g , sp1.getDie().get(0), sp1.getDie().get(1)));
    Speiseplan sp2 = new Speiseplan(16, sp1.getMon(), gList, sp1.getMit(), sp1.getDon(), sp1.getFre());
    Speiseplan sp3 = new Speiseplan(17, sp1.getMon(), gList, sp1.getMit(), null, sp1.getFre());
    Speiseplan sp4 = new Speiseplan(18, null, null, null, null, null);
    Speiseplan sp5 = new Speiseplan(19, null, gList, sp1.getMit(), sp1.getDon(), sp1.getFre());
    PDFCreator.createSpeiseplanPDF(sp1, "speiseplan");
    PDFCreator.createSpeiseplanPDF(sp2, "speiseplan2");
    PDFCreator.createSpeiseplanPDF(sp3, "speiseplan3");
    PDFCreator.createSpeiseplanPDF(sp4, "speiseplan4");
    PDFCreator.createSpeiseplanPDF(sp5, "speiseplan5");
    System.out.println(3 + sp2.getMon().get(1).getGerichtBildPath());

}

    @Test
    void getAnzahlGerichte() {
        Speiseplan sp1 = PersonTest.erzeugeSpeiseplan();
        Gericht g = new Gericht("Flache Flunder", 7.99);

        ArrayList<Gericht> gList = new ArrayList<>(List.of(g));
        ArrayList<Gericht> gList1 = new ArrayList<>(Arrays.asList(g , sp1.getMon().get(0), sp1.getDie().get(1)));
        ArrayList<Gericht> gList2 = new ArrayList<>(Arrays.asList(g , sp1.getDie().get(0), sp1.getDie().get(1)));

        Speiseplan sp2 = new Speiseplan(16, gList, sp1.getDie(), sp1.getMit(), sp1.getDon(), sp1.getFre());
        Speiseplan sp3 = new Speiseplan(17, sp1.getMon(), gList, sp1.getMit(), sp1.getDon(), sp1.getFre());
        Speiseplan sp4 = new Speiseplan(18, gList1, sp1.getDie(), sp1.getMit(), sp1.getDon(), sp1.getFre());
        Speiseplan sp5 = new Speiseplan(16, sp1.getMon(), gList2, sp1.getMit(), sp1.getDon(), sp1.getFre());

        Assertions.assertAll( () -> Assertions.assertEquals(2,PDFCreator.getAnzahlGerichte(sp1)),
                () -> Assertions.assertEquals(2,PDFCreator.getAnzahlGerichte(sp2)),
                () -> Assertions.assertEquals(2,PDFCreator.getAnzahlGerichte(sp3)),
                () -> Assertions.assertEquals(3,PDFCreator.getAnzahlGerichte(sp4)),
                () -> Assertions.assertEquals(3,PDFCreator.getAnzahlGerichte(sp5)));
    }
}


