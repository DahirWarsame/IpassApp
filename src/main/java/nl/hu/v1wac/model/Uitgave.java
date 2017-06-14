package nl.hu.v1wac.model;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by dahir on 6/12/2017.
 */
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

    public void setUitgaveID(int uitgaveID) {
        this.uitgaveID = uitgaveID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public double getBedrag() {
        return bedrag;
    }

    public void setBedrag(double bedrag) {
        this.bedrag = bedrag;
    }

    public String getSoortUitgave() {
        return soortUitgave;
    }

    public void setSoortUitgave(String soortUitgave) {
        this.soortUitgave = soortUitgave;
    }

    public int getKenmerknummer() {
        return kenmerknummer;
    }

    public void setKenmerknummer(int kenmerknummer) {
        this.kenmerknummer = kenmerknummer;
    }

    public int getAantalMaanden() {
        return (aantalMaanden);
    }

    public void setAantalMaanden(int aantalMaanden) {
        this.aantalMaanden = aantalMaanden;
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

    public void setAfbeelding(String afbeelding) {
        this.afbeelding = afbeelding;
    }

    public String getUitgaveDatum() {
        Date date = uitgaveDatum;
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(date);
    }

    public void setUitgaveDatum(Date uitgaveDatum) {
        this.uitgaveDatum = uitgaveDatum;
    }

    public String getBeschrijving() {
        return beschrijving;
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
