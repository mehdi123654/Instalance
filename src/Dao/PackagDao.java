/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import entities.Packag;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.ConnexionSingleton;

/**
 *
 * @author belhassan
 */
    
public class  PackagDao implements Pdao<Packag>{
    
    private static PackagDao instance;

  
    private Statement st;
    private ResultSet rs;
    
    private PackagDao() {
        ConnexionSingleton cs=ConnexionSingleton.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(PackagDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static PackagDao getInstance(){
        if(instance==null) 
            instance=new PackagDao();
        return instance;
    }

    @Override
    public void insert(Packag p) {
        String req="insert into packag (sid,type,price) values ('"+p.getid_s()+"','"+p.getType()+"','"+p.getPrice()+"')";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   


    @Override
    public ObservableList<Packag> displayAll() {
        String req="select * from packag";
        ObservableList<Packag> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Packag p= new Packag();
                p.setIdp(rs.getInt(1));
                p.setType(rs.getString("type"));
                p.setPrice(rs.getInt("price"));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Packag> displayAllList() {
        String req="select * from packag";
        List<Packag> list=new ArrayList<>();
        
       try {
             rs=st.executeQuery(req);
            while(rs.next()){
                Packag p= new Packag();
                p.setIdp(rs.getInt(1));
                p.setType(rs.getString("type"));
                       
                p.setPrice(rs.getInt("price"));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    /**
     *
     * @param id_p
     * @return
     */
    @Override
    public Packag displayById(int id_p) {
           String req="select * from packag where pid ="+id_p;
        Packag p=new Packag();
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
               
                p.setIdp(rs.getInt(1));
                p.setType(rs.getString("type"));
                       
                p.setPrice(rs.getInt("price"));
            //}  
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    return p;
    }

    
    @Override
    public boolean update(int p, int p_id) {
  
     String qry = "UPDATE packag SET  price ='"+p+"' WHERE pid = " +p_id ;
        
        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

  
   

   

  


  

    


    
    
}

