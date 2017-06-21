package nl.hu.v1wac.persistence;

import com.mysql.cj.api.jdbc.Statement;
import nl.hu.v1wac.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends BaseDAO {

    public String authenticateUser(String email, String password) {
        String user = null;
        String query = "SELECT user_id FROM users WHERE email = ? AND password = ? order by user_id desc limit 1";
        try (Connection con = super.getConnection()) {

            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, email);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next())
                user = rs.getString("user_id");
            con.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return user;
    }
    public User findByID(int id) {
        List<User> users = selectUsers("select * from users u left join personal_data p on u.user_id = p.user_id where u.user_id = " + id);
        if (users.size() > 0) return users.get(0);
        return null;
    }
    public boolean createUser(String email, String password, String voornaam, String tussenvoegsel,
                              String achternaam, String woonplaats, String adres) {
        boolean registerdUser = registerUser(email,password);
        String userID = authenticateUser(email, password);
        boolean addedUserInfo = addUserInfo(userID,voornaam,tussenvoegsel,achternaam,woonplaats,adres);
        if (registerdUser && addedUserInfo) return true;
        return false;
    }
    private boolean registerUser(String email, String password) {
        String addUserQ = "INSERT INTO users (email, password) VALUES(?,?) ";

        try (Connection con = super.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(addUserQ);

            stmt.setString(1, email);
            stmt.setString(2, password);

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 1) return true;
            con.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }



        return false;
    }
    private boolean addUserInfo(String userID,String voornaam, String tussenvoegsel,
                                String achternaam, String woonplaats, String adres){
        String addUserInfoQ = "INSERT INTO personal_data " +
                "(`user_id`, `voornaam`, `tussenvoegsel`, `achternaam`, `woonplaats`, `adres`) VALUES(?,?,?,?,?,?)";
        try (Connection con = super.getConnection()){
            PreparedStatement stmt = con.prepareStatement(addUserInfoQ);
            stmt.setString(1, userID);
            stmt.setString(2, voornaam);
            stmt.setString(3, tussenvoegsel);
            stmt.setString(4, achternaam);
            stmt.setString(5, woonplaats);
            stmt.setString(6, adres);
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 1) return true;
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    private List<User> selectUsers(String query) {
        List<User> users = new ArrayList<>();

        try (Connection conn = super.getConnection()) {
            java.sql.Statement stmt = conn.createStatement();
            ResultSet dbResultSet = stmt.executeQuery(query);

            while (dbResultSet.next()) {
                int userID = dbResultSet.getInt("user_id");
                 String email= dbResultSet.getString("email");
                 String password= dbResultSet.getString("password");
                 String voornaam= dbResultSet.getString("voornaam");
                 String tussenvoegsel= dbResultSet.getString("tussenvoegsel");
                 String achternaam= dbResultSet.getString("achternaam");
                 String woonplaats= dbResultSet.getString("woonplaats");
                 String adres= dbResultSet.getString("adres");
                 int role= dbResultSet.getInt("role");


                users.add(new User(userID,email,password,voornaam,tussenvoegsel,achternaam,woonplaats,adres, role));
            }
            conn.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return users;
    }
}
