package nl.hu.v1wac.model;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Uitgave {
    private int uitgaveID;
    private int userID;
    private double bedrag;
    private String soortUitgave;
    private int kenmerknummer;
    private int aantalMaanden;
    private String link;
    private String afbeelding;
    private Date uitgaveDatum;
    private String beschrijving;

    public Uitgave(int uitgaveID, int userID, double bedrag, String soortUitgave, int kenmerknummer, int aantalMaanden, String link, String afbeelding, Date uitgaveDatum, String beschrijving) {
        this.uitgaveID = uitgaveID;
        this.userID = userID;
        this.bedrag = bedrag;
        this.soortUitgave = soortUitgave;
        this.kenmerknummer = kenmerknummer;
        this.aantalMaanden = aantalMaanden;
        this.link = link;
        this.afbeelding = afbeelding;
        this.uitgaveDatum = uitgaveDatum;
        this.beschrijving = beschrijving;
    }

    public int getUitgaveID() {
        return uitgaveID;
    }

    public int getUserID() {
        return userID;
    }

    public double getBedrag() {
        return bedrag;
    }

    public String getSoortUitgave() {
        return soortUitgave;
    }

    public int getKenmerknummer() {
        return kenmerknummer;
    }

    public int getAantalMaanden() {
        return (aantalMaanden);
    }

    public String getLink() {
        if(link.equals("null")) {
            return link = "nvt";
        } else {
            return link;
        }
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAfbeelding() {
        if(afbeelding.equals("null")) {
            return afbeelding = "nvt";
        } else {
            return afbeelding;
        }
    }

    public String getUitgaveDatum() {
        Date date = uitgaveDatum;
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(date);
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    @Override
    public String toString() {
        return "Uitgave{" +
                "uitgaveID=" + uitgaveID +
                ", userID=" + userID +
                ", bedrag=" + bedrag +
                ", soortUitgave=" + soortUitgave +
                ", kenmerknummer=" + kenmerknummer +
                ", aantalMaanden=" + aantalMaanden +
                ", link='" + link + '\'' +
                ", afbeelding='" + afbeelding + '\'' +
                ", uitgaveDatum=" + uitgaveDatum +
                ", beschrijving='" + beschrijving + '\'' +
                '}';
    }
}
