/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crossify.utils;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author emnaa
 */
public class MyConnection {

    private Connection conn;
    String url = "jdbc:mysql://localhost:3306/emna";
    String user = "root";
    String pwd = "";
    private static MyConnection instance;

    private MyConnection() {
        try {
            conn = DriverManager.getConnection(url, user, pwd);
            System.out.println("Connexion etabished!!!");
        } catch (SQLException ex) {
            System.out.println("Error! Connexion problem");
        }
    }

    public static MyConnection getInstance() {
        if (MyConnection.instance == null) {
            MyConnection.instance = new MyConnection();
        }
        return MyConnection.instance;
    }

    public static Connection getConnection() {
        return MyConnection.getInstance().conn;
    }
}
