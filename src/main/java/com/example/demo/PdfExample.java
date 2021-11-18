package com.example.demo;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

public class PdfExample {

    public static void main(String args[]) throws Exception {
        // Creating a PdfWriter
        String dest = "C:\\Users\\van_loechtern_felix\\eclipse-workspace\\demo\\src\\main\\resources\\com\\example\\demo\\generated.pdf";
        PdfWriter writer = new PdfWriter(dest);
        // Creating a PdfDocument
        PdfDocument pdf = new PdfDocument(writer);
        // Creating a Document
        Document document = new Document(pdf);
        String para1 = "This is a generated document";
        String para2 = "This is a second paragraph";
        // Creating Paragraphs
        Paragraph paragraph1 = new Paragraph(para1);
        Paragraph paragraph2 = new Paragraph(para2);
        // Adding paragraphs to document
        document.add(paragraph1);
        document.add(paragraph2);
        // Closing the document
        document.close();
        System.out.println("Paragraph added");
    }
}


