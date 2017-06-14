package nl.hu.v1wac.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends BaseDAO {

    public String findUserForUsernameAndPassword(String username, String password) {
        String user = null;
        String query = "SELECT email FROM users WHERE email = ? AND password = ?";
        try (Connection con = super.getConnection()) {

            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next())
                user = rs.getString("email");
            con.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return user;
    }

    public boolean registerUser(String username, String password) {
        String query = "INSERT INTO users (email, password) VALUES(?,?)";

        try (Connection con = super.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(query);

            stmt.setString(1, username);
            stmt.setString(2, password);

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) return true;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return false;
    }
}
