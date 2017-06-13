package nl.hu.v1wac.model;

import java.util.Calendar;

/**
 * Created by dahir on 6/12/2017.
 */
public class Inkomst {
    private int inkomstID;
    private int userID;
    private double bedrag;
    private boolean soortInkomen;
    private Calendar inkomstDatum;
    private String beschrijving;

    public Inkomst(int inkomstID, int userID, double bedrag, boolean soortInkomen, Calendar inkomstDatum, String beschrijving) {
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

    public void setInkomstID(int inkomstID) {
        this.inkomstID = inkomstID;
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

    public boolean isSoortInkomen() {
        return soortInkomen;
    }

    public void setSoortInkomen(boolean soortInkomen) {
        this.soortInkomen = soortInkomen;
    }

    public Calendar getInkomstDatum() {
        return inkomstDatum;
    }

    public void setInkomstDatum(Calendar inkomstDatum) {
        this.inkomstDatum = inkomstDatum;
    }

    public String getBeschrijving() {
        return beschrijving;
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
