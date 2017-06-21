package nl.hu.v1wac.persistence;

import nl.hu.v1wac.model.Uitgave;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UitgaveDAO extends BaseDAO {

    public void save(Uitgave uitgave) {
        System.out.print(uitgave);
        System.out.print("in save func");
        try (Connection conn = super.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO uitgaves " +
                            "(`user_id`, `bedrag`, `soort_uitgave`, `kenmerknummer`, `aantal_maanden`, `link`, `afbeelding`, `uitgave_datum`, `beschrijving`) " +
                            "VALUES " +
                            "(?,?,?,?,?,?,?,?,?)");

            stmt.setInt(1, uitgave.getUserID());
            System.out.print(uitgave);
            stmt.setDouble(2, uitgave.getBedrag());
            System.out.print(uitgave);
            stmt.setString(3, uitgave.getSoortUitgave());
            System.out.print(uitgave);
            stmt.setString(4, uitgave.getKenmerknummer());
            System.out.print(uitgave);
            stmt.setInt(5, uitgave.getAantalMaanden());
            System.out.print(uitgave);
            stmt.setString(6, uitgave.getLink());
            System.out.print(uitgave);
            stmt.setString(7, uitgave.getAfbeelding());
            System.out.print(uitgave);
            stmt.setDate(8, uitgave.getUitgaveDatum());
            System.out.print(uitgave);
            stmt.setString(9, uitgave.getBeschrijving());
            System.out.print(uitgave);
            int affectedRows = stmt.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public List<Uitgave> findByUserID(int userID) {
        return selectUtgaves("SELECT * FROM uitgaves WHERE user_id= " + userID);
    }
    public Uitgave findByID(int id) {
        List<Uitgave> uitgaves = selectUtgaves("SELECT * FROM uitgaves WHERE uitgave_id = " + id);
        if (uitgaves.size() > 0) return uitgaves.get(0);
        return null;
    }

    public Uitgave update(Uitgave uitgave) {
        try (Connection conn = super.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("UPDATE uitgaves SET user_id = ?, bedrag = ?," +
                    " soort_uitgave = ?, kenmerknummer = ?, aantal_maanden = ?, link = ?, " +
                    "afbeelding = ?, uitgave_datum = ?, beschrijving = ? WHERE uitgave_id = ?");


            stmt.setInt(1, uitgave.getUserID());
            stmt.setDouble(2, uitgave.getBedrag());
            stmt.setString(3, uitgave.getSoortUitgave());
            stmt.setString(4, uitgave.getKenmerknummer());
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
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM uitgaves WHERE uitgave_id = ?");
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
        List<Uitgave> uitgaves = new ArrayList<>();

        try (Connection conn = super.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet dbResultSet = stmt.executeQuery(query);

            while (dbResultSet.next()) {
                int uitgaveID = dbResultSet.getInt("uitgave_id");
                int userID = dbResultSet.getInt("user_id");
                double bedrag = dbResultSet.getDouble("bedrag");
                String soortUitgave = dbResultSet.getString("soort_uitgave");
                String kenmerknummer = dbResultSet.getString("kenmerknummer");
                int aantalMaanden = dbResultSet.getInt("aantal_maanden");
                String link = dbResultSet.getString("link");
                String afbeelding = dbResultSet.getString("afbeelding");
                Date uitgaveDatum = dbResultSet.getDate("uitgave_datum");
                String beschrijving = dbResultSet.getString("beschrijving");

                uitgaves.add(new Uitgave(uitgaveID, userID, bedrag, soortUitgave, kenmerknummer, aantalMaanden, link
                        , afbeelding, uitgaveDatum, beschrijving));
            }
            conn.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return uitgaves;
    }

    public int totaalUitgave(int id) {
        int totaalUitgave = 0;
        try (Connection conn = super.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet dbResultSet = stmt.executeQuery("SELECT SUM(bedrag) as totaal FROM uitgaves WHERE user_id = "+ id + " GROUP BY user_id");

            while (dbResultSet.next()) {
                totaalUitgave = dbResultSet.getInt("totaal");
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return totaalUitgave;
    }
}