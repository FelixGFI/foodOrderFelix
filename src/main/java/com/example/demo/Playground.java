package com.example.demo;

import com.example.demo.foodOrder.logic.Speiseplan;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.Month;


public class Playground {
    public static void main(String[] args) {
        PersonAlt p1 = new PersonAlt("Hans", "Dieterson", 147);
        Person p3 = new Person("Hans", "Dieterson", 147, "Appache Kampfhelikopter");

        if(false) {
            try {
                FileOutputStream fos = new FileOutputStream("src/generated/p1.dat");
                ObjectOutputStream out = new ObjectOutputStream(fos);
                out.writeObject(p1);
                out.close();
                fos.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        Person p2 = null;


        if(true) {
           try {
               FileInputStream fis = new FileInputStream("src/generated/p1.dat");
               ObjectInputStream in = new ObjectInputStream(fis);
               p2 = (Person) in.readObject();
               in.close();
               fis.close();
           } catch (Exception ex) {
               PersonAlt p4 = null;
               try{
                   FileInputStream fis = new FileInputStream("src/generated/p1.dat");
                   ObjectInputStream in = new ObjectInputStream(fis);
                   p4 = (PersonAlt) in.readObject();
               } catch (Exception e) {

               }
               if(p4 != null) {
                   p2 = new Person(p4.getVorname(), p4.getNachname(), p4.getAlter(), "einfügen");
               }
           }
       }
       if(p2 != null) {
           System.out.println(p2.toString());
       } else {
           System.out.println("null");
        }


       LocalDate ld = LocalDate.of(LocalDate.now().getYear()-1, Month.JANUARY, 1);

        for (int i = 0; i < 75; i++) {
            System.out.println(ld);
            ld = ld.plusDays(7);
        }

    }
}
class PersonAlt implements Serializable {

    private String vorname;
    private String nachname;
    private int alter;

    final static long serialVersionUID = 1;

    public PersonAlt(String vorname, String nachname, int alter) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.alter = alter;
    }

    @Override
    public String toString() {
        return "PersonAlt{" +
                "vorname='" + vorname + '\'' +
                ", nachname='" + nachname + '\'' +
                ", alter=" + alter +
                '}';
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public int getAlter() {
        return alter;
    }

    public void setAlter(int alter) {
        this.alter = alter;
    }
}

class Person implements Serializable {

    private String vorname;
    private String nachname;
    private int alter;
    private String geschlecht;

    final static long serialVersionUID = 2;

    public Person(String vorname, String nachname, int alter, String geschlecht) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.alter = alter;
        this.geschlecht = geschlecht;
    }

    @Override
    public String toString() {
        return "Person{" +
                "vorname='" + vorname + '\'' +
                ", nachname='" + nachname + '\'' +
                ", alter=" + alter +
                ", geschlecht='" + geschlecht + '\'' +
                '}';
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public int getAlter() {
        return alter;
    }

    public void setAlter(int alter) {
        this.alter = alter;
    }

    public String getGeschlecht() {
        return geschlecht;
    }

    public void setGeschlecht(String geschlecht) {
        this.geschlecht = geschlecht;
    }
}