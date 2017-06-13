package nl.hu.v1wac.persistence;

import nl.hu.v1wac.model.Uitgave;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UitgaveDAO extends BaseDAO {

    public void save(Uitgave uitgave) {
        System.out.print(uitgave);
        try (Connection conn = super.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO uitgave " +
                            "(code, name, continent, region, surfacearea, population, localname, governmentform, code2, latitude, longitude, capital) " +
                            "VALUES " +
                            "(?,?,CAST(? AS continenttype),?,?,?,?,?,?,?,?,?)");

            stmt.setInt(1, uitgave.getUserID());
            stmt.setDouble(2, uitgave.getBedrag());
            stmt.setString(3, uitgave.getSoortUitgave());
            stmt.setInt(4, uitgave.getKenmerknummer());
            stmt.setInt(5, uitgave.getAantalMaanden());
            stmt.setString(6, uitgave.getLink());
            stmt.setString(7, uitgave.getAfbeelding());
            stmt.setDate(8, uitgave.getUitgaveDatum());
            stmt.setString(9, uitgave.getBeschrijving());

            int affectedRows = stmt.executeUpdate();
//            if (affectedRows == 1) return country;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public List<Uitgave> findAll() {
        return selectUtgaves("SELECT * FROM country");
    }

    public List<Uitgave> findByUserID(int userID) {
        List<Uitgave> uitgaves = selectUtgaves("SELECT * FROM uitgaves WHERE user_id= "+userID);
        return uitgaves;
    }

    public List<Uitgave> find10LargestPopulations() {
        return selectUtgaves("SELECT * FROM country ORDER BY population DESC LIMIT 10");
    }

    public List<Uitgave> find10LargestSurfaces() {
        return selectUtgaves("SELECT * FROM country ORDER BY surfacearea DESC LIMIT 10");
    }

    public Uitgave update(Uitgave uitgave) {
        try (Connection conn = super.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("UPDATE uitgave SET user_id = ?, bedrag = ?," +
                    " soort_uitgave = ?, kenmerknummer = ?, aantal_maanden = ?, link = ?, " +
                    "afbeelding = ?, uitgave_datum = ?, beschrijving = ? WHERE code = ?");


            stmt.setInt(1, uitgave.getUserID());
            stmt.setDouble(2, uitgave.getBedrag());
            stmt.setString(3, uitgave.getSoortUitgave());
            stmt.setInt(4, uitgave.getKenmerknummer());
            stmt.setInt(5, uitgave.getAantalMaanden());
            stmt.setString(6, uitgave.getLink());
            stmt.setString(7, uitgave.getAfbeelding());
            stmt.setDate(8, uitgave.getUitgaveDatum());
            stmt.setString(9, uitgave.getBeschrijving());

            stmt.setInt(10, uitgave.getUitgaveID());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 1) return uitgave;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return null;
    }

    public boolean delete(Uitgave uitgave) {
        boolean succes = false;

        try (Connection conn = super.getConnection()) {
            String delete_fk = "DELETE FROM city WHERE countrycode = ?";
            PreparedStatement fk_stmt = conn.prepareStatement(delete_fk);
            fk_stmt.setInt(1, uitgave.getUitgaveID());
            fk_stmt.executeUpdate();

            String delete_fk_2 = "DELETE FROM countrylanguage WHERE countrycode = ?";
            PreparedStatement fk_stmt_2 = conn.prepareStatement(delete_fk_2);
            fk_stmt_2.setInt(1, uitgave.getUitgaveID());
            fk_stmt_2.executeUpdate();

            PreparedStatement stmt = conn.prepareStatement("DELETE FROM country WHERE code = ?");
            stmt.setInt(1, uitgave.getUitgaveID());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 1) {
                succes = true;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return succes;
    }

    private List<Uitgave> selectUtgaves(String query) {
        List<Uitgave> uitgaves = new ArrayList<Uitgave>();

        try (Connection conn = super.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet dbResultSet = stmt.executeQuery(query);

            while (dbResultSet.next()) {
                int uitgaveID = dbResultSet.getInt("uitgaveID");
                int userID = dbResultSet.getInt("userID");
                double bedrag = dbResultSet.getDouble("bedrag");
                String soortUitgave = dbResultSet.getString("soortUitgave");
                int kenmerknummer = dbResultSet.getInt("kenmerknummer");
                int aantalMaanden = dbResultSet.getInt("aantalMaanden");
                String link = dbResultSet.getString("link");
                String afbeelding = dbResultSet.getString("afbeelding");
                Date uitgaveDatum = dbResultSet.getDate("uitgaveDatum");
                String beschrijving = dbResultSet.getString("beschrijving");

                uitgaves.add(new Uitgave(uitgaveID, userID, bedrag, soortUitgave, kenmerknummer, aantalMaanden, link
                , afbeelding, uitgaveDatum, beschrijving));
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return uitgaves;
    }

}