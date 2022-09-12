package domain;

import interfaces.ReizigerDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReizigerDAOPsql implements ReizigerDAO {
    Connection conn;

    public ReizigerDAOPsql(Connection conn)
    {
        this.conn = conn;
    }

    @Override
    public boolean save(Reiziger reiziger) {
        try {
            PreparedStatement st = conn.prepareStatement("INSERT INTO reiziger (reiziger_id,voorletters, tussenvoegsel, achternaam, geboortedatum) VALUES (?,?,?,?,?);");
            st.setInt(1, reiziger.getReiziger_id());
            st.setString(2, reiziger.getVoorletters());
            st.setString(3, reiziger.getTussenvoegsel());
            st.setString(4, reiziger.getAchternaam());
            st.setDate(5, reiziger.getGeboortedatum());
            st.executeUpdate();
            return true;
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Reiziger reiziger) {
        try {
            PreparedStatement st = conn.prepareStatement("UPDATE reiziger SET voorletters = ?, tussenvoegsel = ?, achternaam = ?, geboortedatum = ? WHERE reiziger_id = ?;");
            st.setString(1, reiziger.getVoorletters());
            st.setString(2, reiziger.getTussenvoegsel());
            st.setString(3, reiziger.getAchternaam());
            st.setDate(4, reiziger.getGeboortedatum());
            st.setInt(5, reiziger.getReiziger_id());
            st.executeUpdate();
            return  true;
        } catch (Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Reiziger reiziger) {
        try {
            PreparedStatement st = conn.prepareStatement("DELETE FROM reiziger WHERE reiziger_id = ?;");
            st.setInt(1, reiziger.getReiziger_id());
            st.executeUpdate();
            return  true;
        } catch (Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Reiziger findByID(int id) {
        try {
            PreparedStatement st = conn.prepareStatement("SELECT * FROM reiziger WHERE reiziger_id = ?;");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            rs.next();
            return new Reiziger(rs.getInt("reiziger_id"), rs.getString("voorletters"), rs.getString("tussenvoegsel"), rs.getString("achternaam"), rs.getDate("geboortedatum"));
        } catch (Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Reiziger> findByGbdatum(String datum) {
        try {
            PreparedStatement st = conn.prepareStatement("SELECT * FROM reiziger WHERE geboortedatum = ?;");
            st.setDate(1, Date.valueOf(datum));
            return getReizigers(st);

        } catch (Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Reiziger> findAll() {
        try {
            PreparedStatement st = conn.prepareStatement("SELECT * FROM reiziger;");
            return getReizigers(st);
        } catch (Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        }
    }

    private List<Reiziger> getReizigers(PreparedStatement st) throws SQLException {
        ResultSet rs = st.executeQuery();
        ArrayList<Reiziger> returnList = new ArrayList<>();
        while (rs.next())
        {
            returnList.add(new Reiziger(rs.getInt("reiziger_id"), rs.getString("voorletters"), rs.getString("tussenvoegsel"), rs.getString("achternaam"), rs.getDate("geboortedatum")));
        }
        return returnList;
    }
}
