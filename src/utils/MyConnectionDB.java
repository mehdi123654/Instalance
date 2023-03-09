/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author emnaa
 */
public class MyConnectionDB {

    private Connection conn;
    String url = "jdbc:mysql://localhost:3306/instalancedb";
    String user = "root";
    String pwd = "";
    private static MyConnectionDB instance;

    private MyConnectionDB() {
        try {
            conn = DriverManager.getConnection(url, user, pwd);
            System.out.println("Connexion etabished!!!");
        } catch (SQLException ex) {
            System.out.println("Error! Connexion problem");
        }
    }

    public static MyConnectionDB getInstance() {
        if (MyConnectionDB.instance == null) {
            MyConnectionDB.instance = new MyConnectionDB();
        }
        return MyConnectionDB.instance;
    }

    public Connection getConnection() {
        return MyConnectionDB.getInstance().conn;
    }
}
