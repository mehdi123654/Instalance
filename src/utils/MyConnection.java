/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class MyConnection {
  private static Connection  cnx;
   String url = "jdbc:mysql://localhost:3306/instalancedb";
   String user = "root";
   String pwd = "";
   private static MyConnection instance;
   private MyConnection() {
        
        try {
            cnx = DriverManager.getConnection(url, user, pwd);
            System.out.println("Success Connecting $$$$$$$$$$");
        } catch (SQLException ex) {
            System.out.println("Error while connecting to database $$$$$$$$$$$$");    
        }}

  public static MyConnection getInstance(){
        if (MyConnection.instance == null) {
            MyConnection.instance = new MyConnection();
        }
        return MyConnection.instance;
        
    }
    
  public static Connection getCnx() {
        return cnx;
    }

    


    
}
    
    
    
    
    
    

