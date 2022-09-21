package domain;

import java.sql.Date;
import java.util.List;

public class Reiziger {
    int reiziger_id;
    String voorletters;
    String tussenvoegsel;
    String achternaam;
    Date geboortedatum;
    Adres adres;
    List<OVChipkaart> ovChipkaarten;

    public Reiziger(int id, String voorletters, String achternaam) {
        this.reiziger_id = id;
        this.voorletters = voorletters;
        this.achternaam = achternaam;
    }

    public Reiziger(int id, String voorletters, String tussenvoegsel, String achternaam, Date geboortedatum) {
        this(id, voorletters, achternaam);
        this.tussenvoegsel = tussenvoegsel;
        this.geboortedatum = geboortedatum;
    }

    public Reiziger(int id, String voorletters, String tussenvoegsel, String achternaam, Date geboortedatum, Adres adres) {
        this(id, voorletters, achternaam, tussenvoegsel, geboortedatum);
        this.adres = adres;
    }

    public int getReiziger_id() {
        return reiziger_id;
    }

    public String getVoorletters() {
        return voorletters;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public Date getGeboortedatum() {
        return geboortedatum;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public List<OVChipkaart> getOvChipkaarten() {
        return ovChipkaarten;
    }

    public void addOVChipkaart(OVChipkaart ovChipkaart) {
        this.ovChipkaarten.add(ovChipkaart);
    }

    public String toString() {
        return "Reiziger {" + reiziger_id + " " + voorletters + ". " + tussenvoegsel + " " + achternaam + ", geb. " + geboortedatum + ", " + adres + "}";
    }
}
