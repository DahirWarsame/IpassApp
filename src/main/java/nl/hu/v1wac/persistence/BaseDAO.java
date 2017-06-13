package nl.hu.v1wac.persistence;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.apache.tomcat.dbcp.dbcp2.DriverManagerConnectionFactory;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;

public class BaseDAO {
    private Connection con;

    public BaseDAO() {
        try {
            String username = "dahir";
            String password = "admin";
            URI dbUri = new URI("localhost:3306");
            String dbUrl = String.format("jdbc:mysql://localhost:%d/no_spang", 3306);

            con = DriverManager.getConnection(dbUrl, username, password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected final Connection getConnection() {
        try {
            return con;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}

