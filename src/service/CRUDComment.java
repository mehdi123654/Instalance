/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import entity.Blog;
import entity.Comment;
import utils.MyConnectionDB;
/**
 *
 * @author zeinab
 */
public class CRUDComment implements InterfaceServices2{
    Statement ste;
Connection conn = MyConnectionDB.getInstance().getConnection();

    @Override
    public void AddComment(Comment C) {
try {
            String req = "INSERT INTO `comment` ( `body`,`id_blog`) VALUES (?,?)";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setString(1, C.getbody());
              ps.setInt(2, C.getId_blog());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void ModifyComment(Comment C) {

 try {
            String req ="UPDATE comment SET body=? WHERE id_comment=?";
            PreparedStatement ps= conn.prepareStatement(req);
            
            ps.setString(1, C.getbody());
            ps.setInt(2, C.getid());

           ps.executeUpdate();
                        System.out.println("Comment modified successfully");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void DeleteComment(int id_comment) {
      
        
        try {
            String req = "DELETE FROM `comment` WHERE id_comment = " + id_comment;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Comment deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }  
    }

    @Override
    public List<Comment> DisplayComment() {
        List<Comment> co = new ArrayList<>();
        try {
        String req = "SELECT * FROM `comment`";
        Statement st = conn.createStatement();
        ResultSet result = st.executeQuery(req);
        
        while (result.next()) {
           Comment resultB = new Comment(result.getInt(1),  result.getString(2),result.getInt(3));
            co.add(resultB);
        }
        System.out.println(co);
      
    } catch (SQLException ex) {
         System.out.println(ex);   
    }
   return co;
}
    
   
            
    
}
