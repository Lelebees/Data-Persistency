import domain.Reiziger;
import domain.ReizigerDAOPsql;
import interfaces.ReizigerDAO;

import java.sql.*;
import java.util.List;

public class    Main {
    static Connection conn;
    public static void main (String[] args)
    {
        getConnection();

        try{
           testReizigerDAO(new ReizigerDAOPsql(conn));
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void getConnection() {
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost/ovchip", "postgres", "CPlugLF6x41u2m1aw8Wd");
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void closeConnection() {
        try {
            conn.close();
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * P2. Reiziger DAO: persistentie van een klasse
     *
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
        for (Reiziger r:rList) {
            System.out.println(r);
        }
    }
}
