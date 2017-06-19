package nl.hu.v1wac.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends BaseDAO {

    public String authenticateUser(String email, String password) {
        String user = null;
        String query = "SELECT user_id FROM users WHERE email = ? AND password = ?";
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

    public boolean registerUser(String email, String password) {
        String query = "INSERT INTO users (email, password) VALUES(?,?)";

        try (Connection con = super.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(query);

            stmt.setString(1, email);
            stmt.setString(2, password);

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) return true;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return false;
    }
}
