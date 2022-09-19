import domain.Adres;
import domain.AdresDAOPsql;
import domain.Reiziger;
import domain.ReizigerDAOPsql;
import interfaces.AdresDAO;
import interfaces.ReizigerDAO;

import java.sql.*;
import java.util.List;

public class Main {
    static Connection conn;

    public static void main(String[] args) {

        try {
            ReizigerDAOPsql rdao = new ReizigerDAOPsql(getConnection());
            AdresDAOPsql adao = new AdresDAOPsql(getConnection());
            rdao.setAdao(adao);
            testReizigerDAO(rdao);
            testAdresDAO(adao, rdao);
            closeConnection();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

    public static Connection getConnection() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection("jdbc:postgresql://localhost/ovchip", "postgres", "CPlugLF6x41u2m1aw8Wd");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
        return conn;
    }

    public static void closeConnection() throws SQLException {
        if (conn != null) {
            conn.close();
            conn = null;
        }
    }

    /**
     * P2. Reiziger DAO: persistentie van een klasse
     * <p>
     * Deze methode test de CRUD-functionaliteit van de Reiziger DAO
     *
     * @throws SQLException
     */
    private static void testReizigerDAO(ReizigerDAO rdao) throws SQLException {
        System.out.println("\n---------- Test ReizigerDAO -------------");

        // Haal alle reizigers op uit de database (Read)
        List<Reiziger> reizigers = rdao.findAll();
        System.out.println("[Test READ] ReizigerDAO.findAll() geeft de volgende reizigers:");
        for (Reiziger r : reizigers) {
            System.out.println(r);
        }
        System.out.println();

        // Maak een nieuwe reiziger aan en persisteer deze in de database (Create)
        String gbdatum = "1981-03-14";
        Reiziger sietske = new Reiziger(77, "S", "", "Boers", java.sql.Date.valueOf(gbdatum));
        System.out.print("[Test CREATE] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.save() ");
        rdao.save(sietske);
        reizigers = rdao.findAll();
        System.out.println(reizigers.size() + " reizigers\n");

        // Geef S. Boers een tussenvoegsel (Update)
        Reiziger sBoers = new Reiziger(77, "S", "van", "Boers", java.sql.Date.valueOf(gbdatum));
        System.out.print("[Test UPDATE] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.update() ");
        rdao.update(sBoers);
        reizigers = rdao.findAll();
        System.out.println(reizigers.size() + " reizigers\n");

        //Verwijder S. van Boers uit onze database (Delete)
        System.out.print("[Test DELETE] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.delete() ");
        rdao.delete(sBoers);
        reizigers = rdao.findAll();
        System.out.println(reizigers.size() + " reizigers\n");

        //Vind user met ID
        System.out.println("Test ID");
        System.out.println(rdao.findByID(1));

        //Vind users met een geboortedatum
        System.out.println("------- Geboortedatum Test ------");
        List<Reiziger> rList = rdao.findByGbdatum("2002-12-03");
        for (Reiziger r : rList) {
            System.out.println(r);
        }
    }

    public static void testAdresDAO(AdresDAO adao, ReizigerDAO rdao) {
        String gbdatum = "1981-03-14";
        Reiziger testReiziger = new Reiziger(70, "F", "M", "L", java.sql.Date.valueOf(gbdatum));
        rdao.save(testReiziger);

        System.out.println("\n---------- Test AdresDAO -------------");

        // Haal alle adressen uit de database
        List<Adres> adressen = adao.findAll();
        System.out.println("[Test READ] AdresDAO.findAll() geeft de volgende adressen:");
        for (Adres a : adressen) {
            System.out.println(a);
        }
        System.out.println();

        // Maak een nieuw adres aan en persisteer deze in de database (Create)
        Adres adres = new Adres(70, "3704AK", "2018A", "Nijenheim", "Zeist", 70);
        System.out.print("[Test CREATE] Eerst " + adressen.size() + " adressen, na AdresDAO.save() ");
        adao.save(adres);
        adressen = adao.findAll();
        System.out.println(adressen.size() + " adressen\n");

        // Geef een adres een andere postcode (Update)
        Adres adres1 = new Adres(70, "1234AB", "2018A", "Nijenheim", "Zeist", 70);
        System.out.print("[Test UPDATE] Eerst " + adressen.size() + " adressen, na AdresDAO.update() ");
        adao.update(adres1);
        adressen = adao.findAll();
        System.out.println(adressen.size() + " adressen\n");

        //Verwijder adres uit onze database (Delete)
        System.out.print("[Test DELETE] Eerst " + adressen.size() + " adressen, na AdresDAO.delete() ");
        adao.delete(adres1);
        adressen = adao.findAll();
        System.out.println(adressen.size() + " adressen\n");

        rdao.delete(testReiziger);
    }
}
