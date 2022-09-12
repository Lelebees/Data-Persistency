import java.sql.*;

public class Main {

    public static void main (String[] args)
    {
        try {
            Connection DBConnection = DriverManager.getConnection("jdbc:postgresql://localhost/ovchip", "postgres", "CPlugLF6x41u2m1aw8Wd");
            PreparedStatement st = DBConnection.prepareStatement("SELECT * FROM reiziger");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                for (int i = 1; i < 5; i++) {
                    System.out.print(rs.getString(i) + " ");
                }
                System.out.println("");
            }
            rs.close();
            st.close();

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            e.getStackTrace();
        }
    }
}
