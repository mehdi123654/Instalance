/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;

import entity.User;
import javafx.collections.ObservableList;

/**
 *
 * @author Louay
 */
public interface UserDAO {
    public boolean insertUser(User u)throws SQLException;
    public boolean updateUser(User u)throws SQLException;
    public boolean deleteUser(User u)throws SQLException;
    public boolean BanUser(int id, int ban);
    public boolean VerifyUser(int id, boolean verify);
    public ObservableList<User> showAllUsers() throws SQLException;
    public User selectOneUserByID(int idUser) throws SQLException;
    public ObservableList<User> sortUsers(String value);
    public ObservableList<User> filterRole(String value);
    public User getUserbyEmailPass(String email, String pass);
    
    
}
