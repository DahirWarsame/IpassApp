package nl.hu.v1wac.model;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Uitgave {
    private int uitgaveID;
    private int userID;
    private double bedrag;
    private String soortUitgave;
    private String kenmerknummer;
    private int aantalMaanden;
    private String link;
    private String afbeelding;
    private java.sql.Date uitgaveDatum;
    private String beschrijving;

    public Uitgave(int uitgaveID, int userID, double bedrag, String soortUitgave, String kenmerknummer, int aantalMaanden, String link, String afbeelding, java.sql.Date uitgaveDatum, String beschrijving) {
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

    public Uitgave(int userID) {
        this.userID = userID;
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

    public String  getKenmerknummer() {
        return (kenmerknummer != null && !kenmerknummer.isEmpty()) ? kenmerknummer: "null" ;
    }

    public int getAantalMaanden() {
        return (aantalMaanden);
    }

    public String getLink() {
        return link;
    }

    public String getAfbeelding() {
            return afbeelding;
    }

    public java.sql.Date getUitgaveDatum() {
        return uitgaveDatum;
    }
    public String getUitgaveDatumString() {
        java.sql.Date date = uitgaveDatum;
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(date);
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setUitgaveID(int uitgaveID) {
        this.uitgaveID = uitgaveID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setBedrag(double bedrag) {
        this.bedrag = bedrag;
    }

    public void setSoortUitgave(String soortUitgave) {
        this.soortUitgave = soortUitgave;
    }

    public void setKenmerknummer(String kenmerknummer) {
        this.kenmerknummer = kenmerknummer;
    }

    public void setAantalMaanden(int aantalMaanden) {
        this.aantalMaanden = aantalMaanden;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setAfbeelding(String afbeelding) {
        this.afbeelding = afbeelding;
    }

    public void setUitgaveDatum(String uitgaveDatum) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            java.util.Date parsed = format.parse(uitgaveDatum);
            java.sql.Date sql = new java.sql.Date(parsed.getTime());
            this.uitgaveDatum = sql;
        } catch (ParseException e) {
            e.printStackTrace();
        };
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
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
