/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;

import entity.Profile;
import entity.User;

/**
 *
 * @author Louay
 */
public interface ProfileDAO {
    public boolean createProfile(Profile p)throws SQLException;
    public boolean updateProfile(Profile p, User u)throws SQLException;
}
