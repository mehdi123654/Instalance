/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instalance1.pkg0.services;

import instalance1.pkg0.entities.User;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Louay
 */
public class Wrapper {
    private static final String ADMIN_ROLE = "admin";
    User user = new User();
    
    Statement statement = cnx.createStatement();
        String query = "SELECT role FROM users WHERE username = 'current_user'";
        ResultSet resultSet = statement.executeQuery(query);
}
