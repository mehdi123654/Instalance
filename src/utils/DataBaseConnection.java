/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author fatha
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DataBaseConnection {
    private static DataBaseConnection instance;
    private Connection connection;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/instalancedb";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";
    private DataBaseConnection() throws SQLException 
    {
        try{
            this.connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            System.out.println("Established connection !");
        }
        catch(SQLException ex){
        System.err.println("Connection error");
        System.err.println(ex.getMessage());
        }
    }
    public Connection getConnection()
    {
        return connection;
    }
    public static DataBaseConnection getInstance() throws SQLException {
    if (instance == null) {
      instance = new DataBaseConnection();
    } else if (instance.getConnection().isClosed()) {
      instance = new DataBaseConnection();
    }

    return instance;
  }
}
