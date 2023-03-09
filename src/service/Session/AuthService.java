/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.Session;

import utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import service.Wrapper;

/**
 *
 * @author Louay
 */
public class AuthService extends Wrapper{
    PreparedStatement ps;
    Connection conn;

    public AuthService() throws SQLException {
        this.conn = MyConnection.getInstance().getCnx();
    }
    
    

    public boolean Authenticate(String username, String password) {
            
    

        try {
            ps = conn.prepareStatement
                    ("select * from user where username= '" + username + "' ");
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                if (resultSet != null) {
                    PreparedStatement psCheckPassword = conn.prepareStatement
                            ("select * from user where username = '" + username + "' and password = '" + password + "' ");
                    ResultSet resultSetFinalCheck = psCheckPassword.executeQuery();

                    while (resultSetFinalCheck.next()) {

                        if (resultSetFinalCheck != null) {

                            AuthResponseDTO _this = new AuthResponseDTO();
                            _this.setIdUser((resultSetFinalCheck.getInt("idUser")));
                            _this.setUsername(resultSetFinalCheck.getString("username"));
                            _this.setEmail(resultSetFinalCheck.getString("email"));
                            _this.setRole(resultSetFinalCheck.getString("role"));
                            _this.setIsBanned(resultSetFinalCheck.getBoolean("isBanned"));
                           
                            UserSession.getSameInstance(_this);
                            if (resultSet != null && resultSetFinalCheck != null) {
                                System.out.println(_this);
                            }
                        }

                    }
                }
               System.out.println("bad credentials");
                return false;
            }
           System.out.println("username does not exist");

            return false;
        } catch (SQLException es) {
            es.printStackTrace();
        }
        return false;
    }
}
