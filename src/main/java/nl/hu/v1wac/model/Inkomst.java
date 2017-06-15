package nl.hu.v1wac.model;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Inkomst {
    private int inkomstID;
    private int userID;
    private double bedrag;
    private String soortInkomen;
    private Date inkomstDatum;
    private String beschrijving;

    public Inkomst(int inkomstID, int userID, double bedrag, String soortInkomen, Date inkomstDatum, String beschrijving) {
        this.inkomstID = inkomstID;
        this.userID = userID;
        this.bedrag = bedrag;
        this.soortInkomen = soortInkomen;
        this.inkomstDatum = inkomstDatum;
        this.beschrijving = beschrijving;
    }

    public int getInkomstID() {
        return inkomstID;
    }
    public int getUserID() {
        return userID;
    }
    public double getBedrag() {
        return bedrag;
    }
    public String getSoortInkomen() {
        return soortInkomen;
    }
    public String getInkomstDatum() {
        Date date = inkomstDatum;
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(date);
    }
    public String getBeschrijving() {
        return beschrijving;
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
