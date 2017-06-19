package nl.hu.v1wac.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Inkomst {
    private int inkomstID;
    private int userID;
    private float bedrag;
    private String soortInkomen;
    private java.sql.Date inkomstDatum;
    private String beschrijving;

    public Inkomst(int inkomstID, int userID, float bedrag, String soortInkomen, java.sql.Date inkomstDatum, String beschrijving) {
        this.inkomstID = inkomstID;
        this.userID = userID;
        this.bedrag = bedrag;
        this.soortInkomen = soortInkomen;
        this.inkomstDatum = inkomstDatum;
        this.beschrijving = beschrijving;
    }

    public Inkomst(int userID) {
        this.userID = userID;
    }

    public int getInkomstID() {
        return inkomstID;
    }

    public int getUserID() {
        return userID;
    }

    public float getBedrag() {
        return bedrag;
    }

    public String getSoortInkomen() {
        return soortInkomen;
    }

    public java.sql.Date getInkomstDatum() {
        return inkomstDatum;
    }

    public String getInkomstDatumString() {
        Date date = inkomstDatum;
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(date);
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setInkomstID(int inkomstID) {
        this.inkomstID = inkomstID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setBedrag(float bedrag) {
        this.bedrag = bedrag;
    }

    public void setSoortInkomen(String soortInkomen) {
        this.soortInkomen = soortInkomen;
    }

    public void setInkomstDatum(String inkomstDatum) {
        try {

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date parsed = format.parse(inkomstDatum);
            java.sql.Date sql = new java.sql.Date(parsed.getTime());
            this.inkomstDatum = sql;
        } catch (ParseException e) {
            e.printStackTrace();
        };

    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    @Override
    public String toString() {
        return "Inkomst{" +
                "inkomstID=" + inkomstID +
                ", userID=" + userID +
                ", bedrag=" + bedrag +
                ", soortInkomen=" + soortInkomen +
                ", inkomstDatum=" + inkomstDatum +
                ", beschrijving='" + beschrijving + '\'' +
                '}';
    }
}
