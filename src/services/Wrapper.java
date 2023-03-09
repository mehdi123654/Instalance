/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.WrapperDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.MyConnection;



/**
 *
 * @author Louay
 */
public class Wrapper implements WrapperDAO{
    
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    public Wrapper() {
        this.conn = MyConnection.getCnx();
    }

    @Override
    public boolean isAdmin(int id) {
        try {

            ps = conn.prepareStatement("select * FROM user WHERE idUser ='" + id + "' ");
            rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("role").equals("Admin")) {
                    return true;
                } else
                    return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isFreelancer(int id) {
        try {

            ps = conn.prepareStatement("select * FROM user WHERE idUser ='" + id + "' ");
            rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("role").equals("Freelancer")) {
                    return true;
                } else
                    return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isBusinessOwner(int id) {
        try {

            ps = conn.prepareStatement("select * FROM user WHERE idUser ='" + id + "' ");
            rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("role").equals("BOwner")) {
                    return true;
                } else
                    return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    
}








