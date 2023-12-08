package org.acme.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import jakarta.ejb.Singleton;

@Singleton
public class PostgresConnection {

    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/db_order";
    private static final String USERNAME = "db_order";
    private static final String PASSWORD = "postgres";

    private Connection connection;

    public PostgresConnection() {
        try {
            // Load the JDBC driver for PostgreSQL
            Class.forName("org.postgresql.Driver");
            // Establish the database connection
            this.connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            System.out.println("Connected to the database.");
        } catch (ClassNotFoundException | SQLException e) {
            // It's generally not a good practice to print the exception stack trace in production code.
            // Logging or handling the exception in a way that makes sense for your application is preferred.
            e.printStackTrace();
            throw new RuntimeException("Failed to connect to the database.");
        }
    }

    // It's a good practice to close the database connection when it's no longer needed.
    public void closeConnection() {
        try {
            if (this.connection != null && !this.connection.isClosed()) {
                this.connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            // Consider logging the exception or handling it in a way that makes sense for your application.
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return this.connection;
    }
}
