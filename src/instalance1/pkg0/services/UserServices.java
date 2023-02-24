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
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String req = "insert into user (username,password,email,role) values (?,?,?,?)";

        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, u.getUsername());
            pst.setString(3, u.getEmail());
            pst.setString(2, PasswordEncryption.cryptage(u.getPassword()));
            pst.setString(4, u.getRole());
            pst.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(UserServices.class
                    .getName()).log(Level.SEVERE, null, ex);
            return false;
    }       
}

    @Override
    public boolean updateUser(User u) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String req = "update user set username = ? email= ?  ,password= ?, role = ?  where idUser = ?";

        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, u.getUsername());
            pst.setString(3, u.getEmail());
            pst.setString(2, u.getPassword());
            pst.setString(4, u.getRole());
            pst.setInt(5, u.getId());
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
