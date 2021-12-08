package com.example.demo;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Playground {
    public static void main(String[] args) throws IOException {
        String s = "    12.99 ";
        Double.valueOf(s);

        String path = "src/generated/output-text.txt";
        boolean appendFile = false;
        File file = new File(path);
        
        OutputStream output = new FileOutputStream(path, appendFile);
        
        String stringToWrite = "Guten Morgen Allerseits";
        byte[] dataWrite = stringToWrite.getBytes(StandardCharsets.UTF_16);

        output.write( dataWrite);

        InputStream input = new FileInputStream(file);
        
        byte[] dataRead = new byte[100];

            input.read(dataRead);


        String dataReadString = new String(dataRead, StandardCharsets.UTF_16);
        System.out.println(dataReadString.trim());
        output.close();
        input.close();
    }
}
