/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Profile;
import entity.User;
import utils.DataBaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import dao.ProfileDAO;

/**
 *
 * @author Louay
 */
public class ProfileService implements ProfileDAO{
    
    private Statement ste;
    private PreparedStatement ps;
    private ResultSet rs;

    private final Connection conn;

    /**
     * @throws SQLException
     *
     */
    public ProfileService() throws SQLException {
        conn = DataBaseConnection.getInstance().getConnection();
    }
    
    @Override
    public boolean createProfile(Profile p) throws SQLException {
        System.out.print("Creating User ********************************");
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String req = "insert into profile (firstName,lastName,aboutMe,phoneNumber) values (?,?,?,?,?)";

        try {
            ps = conn.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, p.getFirstName());           
            ps.setString(2, p.getLastName());
            ps.setString(3, p.getAboutMe());
            ps.setInt(4, p.getPhoneNumber());
                      
            System.out.print(ps);
            
            ps.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class
                    .getName()).log(Level.SEVERE, null, ex);
    }       
        return false;
    }
      
    @Override
    public boolean updateProfile(Profile p, User u) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String query = "UPDATE `profile` SET `firstName`=?,`lastName`=?,`phoneNumber`=?,`aboutMe`=? WHERE idUser = ?";
        
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, p.getFirstName());
            ps.setString(2, p.getLastName());
            ps.setString(3, Integer.toString(p.getPhoneNumber()));
            ps.setString(4, p.getAboutMe());
            ps.setInt(5, u.getId());
            //System.out.println(pst.);
            ps.executeUpdate();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return false; 
        
    } 
    
}
