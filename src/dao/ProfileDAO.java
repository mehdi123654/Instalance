/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instalance.UserManagement.dao;

import Instalance.UserManagement.entities.Profile;
import Instalance.UserManagement.entities.User;
import java.sql.SQLException;

/**
 *
 * @author Louay
 */
public interface ProfileDAO {
    public boolean createProfile(Profile p)throws SQLException;
    public boolean updateProfile(Profile p, User u)throws SQLException;
}
