package domain;

import java.sql.Date;

public class Reiziger {
    int reiziger_id;
    String voorletters;
    String tussenvoegsel;
    String achternaam;
    Date geboortedatum;

    public Reiziger(int id, String voorletters, String achternaam)
    {
        this.reiziger_id = id;
        this.voorletters = voorletters;
        this.achternaam = achternaam;
    }

    public Reiziger(int id, String voorletters, String tussenvoegsel, String achternaam, Date geboortedatum)
    {
        this(id, voorletters, achternaam);
        this.tussenvoegsel = tussenvoegsel;
        this.geboortedatum = geboortedatum;
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

    public String toString()
    {
        return "ID: "+this.reiziger_id+" | "+ this.voorletters + " " +this.tussenvoegsel+ " " + this.achternaam + " | " + this.geboortedatum;
    }
}
