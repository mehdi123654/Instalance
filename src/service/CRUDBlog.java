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
import utils.MyConnection;

/**
 *
 * @author zeinab
 */
public class CRUDBlog implements InterfaceServices {

    Statement ste;
    Connection conn = MyConnection.getInstance().getConnection();

    @Override
    public void AddBlog(Blog B) {
        try {
            String req = "INSERT INTO `Blog` (`title`, `body`) VALUES (?,?)";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setString(1, B.gettitle());
            ps.setString(2, B.getbody());
          
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void ModifyBlog(Blog B) {

        try {
            String req = "UPDATE blog SET title=?, body=?  WHERE id_blog=?";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(3, B.getid());
                        

            ps.setString(1, B.gettitle());
            ps.setString(2, B.getbody());

            ps.executeUpdate();
            System.out.println("Blog modified successfully");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void DeleteBlog(int id) {
        try {
            String req = "DELETE FROM `Blog` WHERE id_blog = " + id;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Blog deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Blog> DisplayBlog() {
        List<Blog> bl = new ArrayList<>();
        try {
            String req = "SELECT * FROM `Blog`";
            Statement st = conn.createStatement();
            ResultSet result = st.executeQuery(req);

            while (result.next()) {
                Blog resultB = new Blog(result.getInt(1), result.getString(2), result.getString(3));
                bl.add(resultB);
            }
            System.out.println(bl);

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return bl;
    }
      public List<Blog> DisplayBlog4() {
        List<Blog> bl = new ArrayList<>();
        try {
            String req = "SELECT title FROM `Blog`";
            Statement st = conn.createStatement();
            ResultSet result = st.executeQuery(req);

            while (result.next()) {
                Blog resultB = new Blog(result.getString(1));
                bl.add(resultB);
            }
            System.out.println(bl);

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return bl;
    }
    
    
     public void update(Blog b, int id) {
       
        String requete="update blog set title=?, body=? where id_blog=" + id;
        try {
            PreparedStatement pst=conn.prepareStatement(requete);
            pst.setString(1, (String) b.gettitle());
            pst.setString(2, (String) b.getbody());
           
            
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CRUDBlog.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public List<Blog> Sort() {
List<Blog> bl = new ArrayList<>();
        try {
            String req = "SELECT * FROM `Blog` order by title";
            Statement st = conn.createStatement();
            ResultSet result = st.executeQuery(req);

            while (result.next()) {
                Blog resultB = new Blog(result.getInt(1), result.getString(2), result.getString(3));
                bl.add(resultB);
            }
            System.out.println(bl);

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return bl;
    }
  
        
    }

