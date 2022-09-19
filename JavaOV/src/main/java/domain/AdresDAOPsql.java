package domain;

import interfaces.AdresDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdresDAOPsql implements AdresDAO {
    Connection conn;
    ReizigerDAOPsql rdao;

    public AdresDAOPsql(Connection conn)
    {
        this.conn = conn;
//        this.rdao = new ReizigerDAOPsql(conn);
    }

    @Override
    public boolean save(Adres adres) {
        try {
            PreparedStatement st = conn.prepareStatement("INSERT INTO adres (adres_id, postcode, huisnummer, straat, woonplaats, reiziger_id) VALUES (?,?,?,?,?,?);");
            st.setInt(1, adres.getID());
            st.setString(2, adres.getPostcode());
            st.setString(3, adres.getHuisnummer());
            st.setString(4, adres.getStraat());
            st.setString(5, adres.getWoonplaats());
            st.setInt(6, adres.getReizigerID());
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
    public boolean update(Adres adres) {
        try {
            PreparedStatement st = conn.prepareStatement("UPDATE adres SET postcode = ?, huisnummer = ?, straat = ?, woonplaats = ?, reiziger_id = ? WHERE adres_id = ?;");
            st.setString(1, adres.getPostcode());
            st.setString(2, adres.getHuisnummer());
            st.setString(3, adres.getStraat());
            st.setString(4, adres.getWoonplaats());
            st.setInt(5, adres.getReizigerID());
            st.setInt(6, adres.getID());
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
    public boolean delete(Adres adres) {
        try {
            PreparedStatement st = conn.prepareStatement("DELETE FROM adres WHERE adres_id = ?;");
            st.setInt(1, adres.getID());
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
    public List<Adres> findAll() {
        try {
            PreparedStatement st = conn.prepareStatement("SELECT * FROM adres;");
            ResultSet rs = st.executeQuery();
            ArrayList<Adres> returnList = new ArrayList<>();
            rs.next();
            while (rs.next())
            {
                returnList.add(constructAdres(rs));
            }
            return returnList;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Adres findByReiziger(Reiziger reiziger) {
        try {
            PreparedStatement st = conn.prepareStatement("SELECT * FROM adres WHERE reiziger_id = ?;");
            st.setInt(1, reiziger.getReiziger_id());
            ResultSet rs = st.executeQuery();
            rs.next();
            return constructAdres(rs);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        }
    }

    private Adres constructAdres(ResultSet rs) throws SQLException {
        return new Adres(rs.getInt("adres_id"), rs.getString("postcode"), rs.getString("huisnummer"), rs.getString("straat"), rs.getString("woonplaats"), rs.getInt("reiziger_id"));
    }
}
