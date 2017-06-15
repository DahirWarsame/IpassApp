package nl.hu.v1wac.persistence;

import nl.hu.v1wac.model.Inkomst;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InkomstDAO extends BaseDAO {

    public void save(Inkomst inkomst) {
        System.out.print(inkomst);
        try (Connection conn = super.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO inkomsten " +
                            "(`user_id`, `bedrag`, `soort_inkomen`, `inkomst_datum`, `beschrijving`) " +
                            "VALUES " +
                            "(?,?,?,?,?)");

            stmt.setInt(1, inkomst.getUserID());
            stmt.setDouble(2, inkomst.getBedrag());
            stmt.setString(3, inkomst.getSoortInkomen());
            stmt.setString(4, inkomst.getInkomstDatum());
            stmt.setString(5, inkomst.getBeschrijving());

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public List<Inkomst> findByUserID(int userID) {
        return selectUtgaves("SELECT * FROM inkomsten WHERE user_id= " + userID);
    }

    public Inkomst update(Inkomst inkomst) {
        try (Connection conn = super.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("UPDATE inkomsten SET user_id = ?, bedrag = ?," +
                    " soort_inkomen = ?, inkomst_datum = ?, beschrijving = ? WHERE inkomst_id = ?");


            stmt.setInt(1, inkomst.getUserID());
            stmt.setDouble(2, inkomst.getBedrag());
            stmt.setString(3, inkomst.getSoortInkomen());
            stmt.setString(4, inkomst.getInkomstDatum());
            stmt.setString(5, inkomst.getBeschrijving());

            stmt.setInt(6, inkomst.getInkomstID());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 1) return inkomst;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return null;
    }

    public boolean delete(Inkomst inkomst) {
        boolean succes = false;

        try (Connection conn = super.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM inkomsten WHERE inkomst_id = ?");
            stmt.setInt(1, inkomst.getInkomstID());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 1) {
                succes = true;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return succes;
    }

    private List<Inkomst> selectUtgaves(String query) {
        List<Inkomst> inkomsten = new ArrayList<>();

        try (Connection conn = super.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet dbResultSet = stmt.executeQuery(query);

            while (dbResultSet.next()) {
                int inkomstID = dbResultSet.getInt("inkomst_id");
                int userID = dbResultSet.getInt("user_id");
                double bedrag = dbResultSet.getDouble("bedrag");
                String soortUitgave = dbResultSet.getString("soort_inkomst");
                Date inkomstDatum = dbResultSet.getDate("inkomst_datum");
                String beschrijving = dbResultSet.getString("beschrijving");

                inkomsten.add(new Inkomst(inkomstID, userID, bedrag, soortUitgave, inkomstDatum, beschrijving));
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return inkomsten;
    }

}