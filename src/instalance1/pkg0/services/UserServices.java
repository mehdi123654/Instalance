/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instalance1.pkg0.services;

import instalance1.pkg0.dao.UserDAO;
import instalance1.pkg0.entities.PasswordEncryption;
import instalance1.pkg0.entities.User;
import instalance1.pkg0.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Louay
 */
public class UserServices implements UserDAO{
    
    
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    private Connection conn;

    public UserServices() {
        conn = MyConnection.getInstance().getCnx();
    }

    @Override
    public boolean insertUser(User u) throws SQLException {
        boolean connectedUser = false;
        System.out.print("Creating User ********************************");
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String req = "insert into user (username,email,password,role,createdAt,isVerified,isBanned,isConnected) values (?,?,?,?,?,?,?,?)";

        try {
            pst = conn.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, u.getUsername());
            pst.setString(2, PasswordEncryption.cryptage(u.getPassword()));
            pst.setString(3, u.getEmail());
            pst.setString(4, u.getRole());
            pst.setTimestamp(5, u.getCreatedAt());
            pst.setInt(6, 0);
            pst.setInt(7, 0);
            pst.setInt(8, 1);
            
            System.out.print(pst);
            
            int ok = pst.executeUpdate();
            if(ok != -1){
                rs = pst.getGeneratedKeys();
                rs.next();
                System.out.println("Successfully signed user! *****************************");
                System.out.print(rs.getInt(1)  + "    OOOOOOOOOOOOOOOOOOOO");
                connectedUser = true;
            } else {
                System.out.println("Failed to sign user! ************************");
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserServices.class
                    .getName()).log(Level.SEVERE, null, ex);
    }       
        return connectedUser;
}

    @Override
    public boolean updateUser(User u) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String req = "UPDATE `user` SET `username`=?,`email`=?,`password`=?,`role`=? WHERE idUser = ?";
        
        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, u.getUsername());
            pst.setString(2, u.getEmail());
            pst.setString(3, u.getPassword());
            pst.setString(4, u.getRole());
            pst.setInt(5, u.getId());
            //System.out.println(pst.);
            pst.executeUpdate();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserServices.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return false; 
        
    } 

    @Override
    public boolean deleteUser(User u) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        String req = "delete from user where idUser = ?";

        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, u.getId());
            pst.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(UserServices.class
                    .getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public ObservableList<User> showAllUsers() throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        String req = "Select * from `user`";

        ObservableList<User> UsersList = FXCollections.observableArrayList();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {//parcourir le resultset
                UsersList.add(new User(rs.getInt("idUser"), rs.getString("username"), rs.getString("email"),  rs.getString("password"),rs.getString("role")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(UserServices.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return UsersList;
    }

    @Override
    public User selectOneUserByID(int idUser) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<User> sortUsers(String value) {
        String req = "select * from user order by " + value;

        ObservableList<User> list = FXCollections.observableArrayList();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {//parcourir le resultset
                 list.add(new User(rs.getInt("idUser"), rs.getString("username"), rs.getString("email"),  rs.getString("password"),rs.getString("role")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(UserServices.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public ObservableList<User> filterRole(String value) {
        String req = "select * from user where role = '" + value + "'";

        ObservableList<User> list = FXCollections.observableArrayList();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {//parcourir le resultset
                list.add(new User(rs.getInt("idUser"), rs.getString("username"), rs.getString("email"),  rs.getString("password"),rs.getString("role")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(UserServices.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
}
