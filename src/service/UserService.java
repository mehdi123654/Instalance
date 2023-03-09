/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.PasswordEncryption;
import entity.User;
import utils.DataBaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import dao.UserDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Louay
 */
public class UserService implements UserDAO{
    
    
    private Statement ste;
    private PreparedStatement ps;
    private ResultSet rs;

    private Connection conn;

    public UserService() throws SQLException {
        conn = DataBaseConnection.getInstance().getConnection();
    }

    @Override
    public boolean insertUser(User u) throws SQLException {
        boolean connectedUser = false;
        System.out.print("Creating User ********************************");
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String req = "insert into user (username,email,password,role,createdAt,isVerified,isBanned,isConnected) values (?,?,?,?,?,?,?,?)";

        try {
            ps = conn.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, u.getUsername());           
            ps.setString(2, u.getEmail());
            ps.setString(3, PasswordEncryption.cryptage(u.getPassword()));
            ps.setString(4, u.getRole());
            ps.setTimestamp(5, u.getCreatedAt());
            ps.setInt(6, 0);
            ps.setInt(7, 0);
            ps.setInt(8, 1);
            
            System.out.print(ps);
            
            int pstExecute = ps.executeUpdate();
            if(pstExecute != -1){
                rs = ps.getGeneratedKeys();
                rs.next();
                System.out.println("Successfully signed user! *****************************");
                System.out.print(rs.getInt(1)  + "    OOOOOOOOOOOOOOOOOOOO");
                connectedUser = true;
            } else {
                System.out.println("Failed to sign user! ************************");
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class
                    .getName()).log(Level.SEVERE, null, ex);
    }       
        return connectedUser;
}

    @Override
    public boolean updateUser(User u) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String query = "UPDATE `user` SET `username`=?,`email`=?,`password`=?,`role`=? WHERE idUser = ?";
        
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getPassword());
            ps.setString(4, u.getRole());
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

    @Override
    public boolean deleteUser(User u) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        String query = "delete from user where idUser = ?";

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, u.getId());
            ps.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class
                    .getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public ObservableList<User> showAllUsers() throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        String query = "Select * from `user`";

        ObservableList<User> UsersList = FXCollections.observableArrayList();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(query);
            while (rs.next()) {//parcourir le resultset
                UsersList.add(new User(rs.getInt("idUser"), rs.getString("username"), rs.getString("email"),  rs.getString("password"),rs.getString("role")));

            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class
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
            Logger.getLogger(UserService.class
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
            while (rs.next()) {
                list.add(new User(rs.getInt("idUser"), rs.getString("username"), rs.getString("email"),  rs.getString("password"),rs.getString("role")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    @Override
    public boolean BanUser(int id, boolean ban) {
        try {
            ps = conn.prepareStatement( "update FROM user SET ban ='" + ban + "'");
            ps.setBoolean(1, ban);
            ps.executeUpdate();
        } catch (SQLException SQLEx) {
            System.out.print(SQLEx.getMessage());
        }
        return false;
    }

    @Override
    public boolean VerifyUser(int id, boolean verify) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    /*public static void loadView(int idUser, Stage stage) throws SQLException, Exception {
        Connection connection = null;
        User user = new User();
        try {
             // Query the user's role based on their username
            String query = "SELECT role FROM user WHERE idUser = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, user.getId());
            ResultSet rs = stmt.executeQuery();

            // Get the user's role
            String role = "";
            while (rs.next()) {
                role = rs.getString("role");
            }

            // Load the appropriate FXML file based on the user's role
            String fxmlFile = "";
            if (role == null){
                fxmlFile = "Instalance/UserManagement/views/AllUsers/SignupFXML.fxml";
            } else if (role.equals("Admin")) {
                fxmlFile = "Instalance/UserManagement/views/Admin/AdminDashboard.fxml";
            } else {
                // Default to home.fxml if the user's role is not recognized
                fxmlFile ="Instalance/UserManagement/views/AllUsers/HomeFXML.fxml";
            }
            
            Parent root = FXMLLoader.load(Wrapper.class.getResource(fxmlFile));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            
            connection.close();
        } catch (SQLException | IOException e) {
        }        
    }*/

    @Override
    public User getUserbyEmailPass(String username, String pass) {
        String req = "select * from user where username = '" + username + "' and password = '" + PasswordEncryption.cryptage(pass) + "'";

        User u = new User();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            if (rs.first()) {
                u = new User(rs.getInt("id"), rs.getString("username"), rs.getString("email"),  rs.getString("password"),rs.getString("role"));
            }
            System.out.println(u);

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }
    
}
