package org.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        // Load the spring application context
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        // Retrieve bean from the context
        QueryDao queryDao = context.getBean(QueryDao.class);

        // Execute queries
        try {
            // Check database connection
            queryDao.checkDatabaseConnection();
            // Get names of all databases
            queryDao.getAllDatabases();
            // Create a new database
            String retailprices_database = "retailprices";
            queryDao.createNewDatabase(retailprices_database);
            // Create a new table in the new database
            String prices_table = "prices";
            queryDao.createPricesTable(retailprices_database,prices_table);
            // Insert data into the prices table
            queryDao.insertDataIntoPricesTable(retailprices_database,prices_table);
            // Query the first 3 rows from the prices table
            queryDao.queryFirstThreeRowsPricesTable(retailprices_database,prices_table);
            // Delete the table
            queryDao.deleteTable(retailprices_database,prices_table);
            // Delete the database
            queryDao.deleteDatabase(retailprices_database);
        } catch (Exception e) {
            System.err.println("Error executing queries: " + e.getMessage());
        } finally {
            // Close the application context
            context.close();
        }
    }
}