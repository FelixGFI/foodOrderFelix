package com.example.demo.foodOrder.output;

import com.example.demo.foodOrder.logic.Gericht;
import com.example.demo.foodOrder.logic.Speiseplan;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class PDFCreator {

    public static void createSpeiseplanPDF(Speiseplan speiseplan, String dokumentName) throws FileNotFoundException, MalformedURLException {
        String dest = "src/generated/" + dokumentName +".pdf";
        internalCreateSpeiseplanPDF(speiseplan, dest);
    }

    public static void createSpeiseplanPDF(Speiseplan speiseplan, File f) throws FileNotFoundException, MalformedURLException {
        internalCreateSpeiseplanPDF(speiseplan, f.getPath());
    }

    private static void internalCreateSpeiseplanPDF(Speiseplan speiseplan, String dest) throws FileNotFoundException, MalformedURLException {
        // Creating a PdfWriter
        PdfWriter writer = new PdfWriter(dest);

        // Creating a PdfDocument
        PdfDocument pdfDoc = new PdfDocument(writer);
        pdfDoc.setDefaultPageSize(PageSize.A4.rotate());

        // Adding a new page
        pdfDoc.addNewPage();

        // Creating a Document
        Document document = new Document(pdfDoc);

        // Creating a table object
        float [] pointColumnWidths = {120F, 120F, 120F, 120F, 120F, 120F};
        Table table = new Table(pointColumnWidths);

        // Adding first Row of Cells to Table

        createUeberschriftenzeileSpeiseplan(speiseplan, table);

        int maxAnzahlGerichte = getAnzahlGerichte(speiseplan);

        for (int gericht = 0 ; gericht < maxAnzahlGerichte; gericht++) {
            int indexPlus1 = gericht + 1;
            table.addCell("Gericht " + indexPlus1);
            for (int wochentag = 0; wochentag < 5; wochentag++) {
                Gericht tagesGericht = ermittleTagesGericht(gericht, wochentag, speiseplan);
                if(tagesGericht.getPreis() != 0) {
                    String imageFile = (tagesGericht.getGerichtBildPath() != null) ?
                            tagesGericht.getGerichtBildPath() :
                            "src/javaBilder/noImage.jpg";
                    Cell speiseBild = createSpeiseplanCellImage(imageFile);
                    table.addCell(speiseBild);
                } else {
                    table.addCell("");
                }
            }
            table.addCell("");

            for (int wochentag = 0; wochentag < 5; wochentag++) {
                Gericht tagesGericht = ermittleTagesGericht(gericht, wochentag, speiseplan);
                if(tagesGericht.getPreis() != 0) {
                    String bezeichnung = tagesGericht.getBezeichnung();
                    Double preis = tagesGericht.getPreis();
                    Cell beschreibung = createSpeiseplanCellText(bezeichnung, preis);
                    table.addCell(beschreibung);
                } else {
                    table.addCell("");
                }
            }
        }


        // Adding Table to the document
        document.add(table);

        // Closing the document
        document.close();
        System.out.println("PDF Created");
    }

    private static void createUeberschriftenzeileSpeiseplan(Speiseplan speiseplan, Table table) {
        table.addCell("KW " + speiseplan.getKw());
        table.addCell("Montag");
        table.addCell("Dienstag");
        table.addCell("Mittwoch");
        table.addCell("Donnerstag");
        table.addCell("Freitag");
    }

    private static Cell createSpeiseplanCellImage(String imageFile) throws MalformedURLException {
        try{
            java.awt.Image awtImage = ImageIO.read(new URL("file:" + imageFile));

            double scaledWidth = 120F;
            double scaledHeight = awtImage.getHeight(null) / (awtImage.getWidth(null) / scaledWidth);

            BufferedImage scaledAwtImage = new BufferedImage((int) scaledWidth, (int) scaledHeight, BufferedImage.TYPE_INT_RGB);

            Graphics2D g = scaledAwtImage.createGraphics();
            g.drawImage(awtImage, 0, 0, (int) scaledWidth, (int) scaledHeight, null);
            g.dispose();

            com.itextpdf.io.source.ByteArrayOutputStream bout = new ByteArrayOutputStream();
            ImageIO.write(scaledAwtImage, "jpeg", bout);
            byte[] imageBytes = bout.toByteArray();

            ImageData data = ImageDataFactory.create(imageBytes);

            Image img = new Image(data);
            Cell speise = new Cell();
            img.setHorizontalAlignment(HorizontalAlignment.CENTER);
            speise.setVerticalAlignment(VerticalAlignment.MIDDLE);
            speise.add(img.scaleToFit(120F, 120F));

            System.out.println(0);

            return speise;
        } catch (IOException e) {

            //Below is my own code that i acctually understand. above is the code mostly imported and adjusted from Herrn Oppitz

            ImageData data = ImageDataFactory.create(imageFile);
            Image img = new Image(data);
            Cell speise = new Cell();
            img.setHorizontalAlignment(HorizontalAlignment.CENTER);
            speise.setVerticalAlignment(VerticalAlignment.MIDDLE);
            speise.add(img.scaleToFit(120F, 120F));

            System.out.println(1);

            return speise;
        }
    }

    private static Cell createSpeiseplanCellText(String bezeichnung, Double preis) {
        Cell speise = new Cell();
        String preisString = String.format("%,.2f", preis);
        speise.add(new Paragraph(bezeichnung + "\n" + preisString + " EUR"));
        speise.setTextAlignment(TextAlignment.CENTER);
        speise.setVerticalAlignment(VerticalAlignment.MIDDLE);
        return speise;
    }

    private static Gericht ermittleTagesGericht(int option, int wochentag, Speiseplan speiseplan) {
        ArrayList<Gericht> monList = speiseplan.getMon();
        ArrayList<Gericht> dieList = speiseplan.getDie();
        ArrayList<Gericht> mitList = speiseplan.getMit();
        ArrayList<Gericht> donList = speiseplan.getDon();
        ArrayList<Gericht> freList = speiseplan.getFre();

        Gericht tagesGericht = new Gericht("", 0);

        if(wochentag == 0 && monList != null) {
            if(option < monList.size()) {
                tagesGericht = monList.get(option);
            }
        }
        if(wochentag == 1 && dieList != null) {
            if(option < dieList.size()) {
                tagesGericht = dieList.get(option);
            }
        }
        if(wochentag == 2 && mitList != null) {
            if(option < mitList.size()) {
                tagesGericht = mitList.get(option);
            }
        }
        if(wochentag == 3 && donList != null) {
            if(option < donList.size()) {
                tagesGericht = donList.get(option);
            }
        }
        if(wochentag == 4 && freList != null) {
            if( option < freList.size()) {
                tagesGericht = freList.get(option);
            }
        }
        return tagesGericht;
    }

    public static int getAnzahlGerichte(Speiseplan speiseplan) {
        int anzahlGerichte = 0;
        if(speiseplan.getMon() != null) {
            anzahlGerichte = speiseplan.getMon().size();
        }
        if(speiseplan.getDie() != null) {
            if(anzahlGerichte < speiseplan.getDie().size()) {
                anzahlGerichte = speiseplan.getDie().size();
            }
        }
        if(speiseplan.getMit() != null) {
            if(anzahlGerichte < speiseplan.getMit().size()) {
                anzahlGerichte = speiseplan.getMit().size();
            }
        }
        if(speiseplan.getDon() != null) {
            if(anzahlGerichte < speiseplan.getDon().size()) {
                anzahlGerichte = speiseplan.getDon().size();
            }
        }
        if(speiseplan.getFre() != null) {
            if(anzahlGerichte < speiseplan.getFre().size()) {
                anzahlGerichte = speiseplan.getFre().size();
            }
        }
        return anzahlGerichte;
    }

    private static Cell createSpeiseplanCell(String imageFile, String bezeichnung, Double preis) throws MalformedURLException {
        ImageData data = ImageDataFactory.create(imageFile);
        Image img = new Image(data);
        Cell speise = new Cell();
        speise.add(img.setAutoScale(true));
        speise.add(new Paragraph(bezeichnung + ", " + preis));
        speise.setTextAlignment(TextAlignment.CENTER);
        return speise;
    }

}
