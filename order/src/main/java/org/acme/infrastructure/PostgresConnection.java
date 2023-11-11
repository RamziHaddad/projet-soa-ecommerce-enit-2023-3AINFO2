package org.acme.infrastructure;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import jakarta.ejb.Singleton;

@Singleton
public class PostgresConnection {
 private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/db_order";
    private static final String USERNAME = "db_order";
    private static final String PASSWORD = "postgres";

    private Connection connection;

    public PostgresConnection() {
        try {
            // Charger le pilote JDBC PostgreSQL
            Class.forName("org.postgresql.Driver");
            // Établir la connexion à la base de données
            this.connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            System.out.println("Connected to the database.");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to connect to the database.");
        }
    }

    public Connection getConnection() {
        return this.connection;
    }



}
