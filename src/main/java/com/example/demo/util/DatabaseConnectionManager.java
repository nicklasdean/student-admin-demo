package com.example.demo.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnectionManager {
    private static String user;
    private static String password;
    private static String url;
    private static Connection conn;

    public static Connection getDatabaseConnection() {
        if(conn != null) return conn;

        FileInputStream propertyFile = null;
        Properties prop = new Properties();
        try {
            propertyFile = new FileInputStream("src/main/resources/application.properties");
            prop.load(propertyFile);
            user = prop.getProperty("db.user");
            password = prop.getProperty("db.password");
            url = prop.getProperty("db.url");

        }
        catch(FileNotFoundException e){
            System.out.println("File could not be found");
        }
        catch(IOException e){
            System.out.println("Property could not be loaded");
        }

        finally {
            try {
                propertyFile.close();
            } catch (IOException e) {
                System.out.println("Property could not be found");
            }
        }

        try {
            conn = DriverManager.getConnection(url, user, password);
        }
        catch(SQLException e){
            System.out.println("Message to the developer");
            e.printStackTrace();
        }
        return conn;
    }
}
