package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class QueryDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public QueryDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void checkDatabaseConnection() {
        try {
            // Using queryForObject to execute a simple SQL query and retrieve a single result
            int result = jdbcTemplate.queryForObject("SELECT 1", Integer.class);
            System.out.println("Database connection successful. Query result: " + result);
        } catch (Exception e) {
            throw new RuntimeException("Error connecting to the database: " + e.getMessage());
        }
    }

    public void getAllDatabases() {
        try {
            // Using queryForList to execute a query that returns a list of values
            List<String> databases = jdbcTemplate.queryForList("SELECT datname FROM pg_database WHERE datistemplate = false", String.class);
            System.out.println("All databases:");
            databases.forEach(System.out::println);
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving database list: " + e.getMessage());
        }
    }

    public void createNewDatabase(String dbName) {
        try {
            // Using execute to execute DDL SQL statements
            jdbcTemplate.execute("CREATE DATABASE " + dbName);
            System.out.println("Database " + dbName + " created successfully.");
        } catch (Exception e) {
            throw new RuntimeException("Error creating database: " + e.getMessage());
        }
    }

    public void createPricesTable(String dbName, String tableName) {
        try {
            jdbcTemplate.execute("CREATE SCHEMA IF NOT EXISTS " + dbName);
            jdbcTemplate.execute("CREATE TABLE " + dbName + "." + tableName + " (Barcode VARCHAR(255), Price DECIMAL, StoreNumberID INT)");
            System.out.println("Table " + tableName + " created successfully in database " + dbName);
        } catch (Exception e) {
            throw new RuntimeException("Error creating table: " + e.getMessage());
        }
    }

    public void insertDataIntoPricesTable(String dbName, String tableName) {
        try {
            Object[][] data = {
                    {"123456789", 10.99, 1},
                    {"987654321", 5.99, 2},
                    {"456789123", 8.49, 1},
                    {"789123456", 15.29, 3},
                    {"654321987", 3.79, 2}
            };
            // Execute batch update to insert data into the table
            for (Object[] row : data) {
                jdbcTemplate.update("INSERT INTO " + dbName + "." + tableName + " (Barcode, Price, StoreNumberID) VALUES (?, ?, ?)", row);
            }
            System.out.println("Data inserted successfully into table " + tableName + " in database " + dbName);
        } catch (Exception e) {
            throw new RuntimeException("Error inserting data into table: " + e.getMessage());
        }
    }

    public void queryFirstThreeRowsPricesTable(String dbName, String tableName) {
        try {
            List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM " + dbName + "." + tableName + " LIMIT 3");
            System.out.println("First three rows from table " + tableName + " in database " + dbName + ":");
            for (Map<String, Object> row : rows) {
                for (Map.Entry<String, Object> entry : row.entrySet()) {
                    System.out.print(entry.getKey() + ": " + entry.getValue() + ", ");
                }
                System.out.println();
            }
        } catch (Exception e) {
            throw new RuntimeException("Error querying data from table: " + e.getMessage());
        }
    }

    public void deleteTable(String dbName, String tableName) {
        try {
            jdbcTemplate.execute("DROP TABLE IF EXISTS " + dbName + "." + tableName);
            System.out.println("Table " + tableName + " deleted successfully from database " + dbName);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting table: " + e.getMessage());
        }
    }

    public void deleteDatabase(String dbName) {
        try {
            jdbcTemplate.execute("DROP DATABASE IF EXISTS " + dbName);
            System.out.println("Database " + dbName + " deleted successfully.");
        } catch (Exception e) {
            throw new RuntimeException("Error deleting database: " + e.getMessage());
        }
    }
}