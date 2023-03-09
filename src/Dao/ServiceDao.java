/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Controller.ListData;
import entities.Packag;
import entities.Service;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
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
public class ServiceDao implements Idao<Service>{
    
    private static ServiceDao instance;
    private Statement st;
    private ResultSet rs;
   
  
    
    private ServiceDao() {
        ConnexionSingleton cs=ConnexionSingleton.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ServiceDao getInstance(){
        if(instance==null) 
            instance=new ServiceDao();
        return instance;
    }

    @Override
    public void insert(Service s) {
        String req="insert into Service (name,descr,prix,file,cat) values ('"+s.getName()+"','"+s.getDesc()+"','"+s.getPrix()+"','"+s.getFile()+"','"+s.getCat()+"')";
        try {
           st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   

    @Override
    public void delete(Service s) {
        String req="delete from service where id="+s.getId();
        Service p=displayById(s.getId());
        
          if(s!=null)
              try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDao.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("doesn't exist");
    }
  
    @Override
    public ObservableList<Service> displayAll() {
        String req="select * from service";
        ObservableList<Service> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Service s= new Service();
                s.setId(rs.getInt(1));
                s.setName(rs.getString("name"));
                s.setDesc(rs.getString("descr"));            
               s.setPrix(rs.getInt("prix"));
               s.setFile(rs.getString("file")); 
                s.setCat(rs.getString("cat"));
                list.add(s);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Service> displayAllList() {
        String req="select * from service";
        List<Service> list=new ArrayList<>();
        
       try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Service s= new Service();
                s.setId(rs.getInt(1));
                s.setName(rs.getString("name"));
                s.setDesc(rs.getString("descr"));
                 s.setPrix(rs.getInt("prix"));
                 s.setFile(rs.getString("file"));
                 s.setCat(rs.getString("cat"));
                list.add(s);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    @Override
    public Service displayById(int id) {
           String req="select * from service where id ="+id;
           Service s=new Service();
        try {
            rs=st.executeQuery(req);
           while(rs.next()){
           
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setDesc(rs.getString("descr"));
                s.setPrix(rs.getInt("prix"));
                s.setFile(rs.getString("file"));
                s.setCat(rs.getString("cat"));
              
          }  
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    return s;
    }

    @Override
     public boolean update(int id,String n,String m,int p,String f) {
        String qry = "UPDATE service SET name = '"+n+"', descr = '"+m+"',prix ='"+p+"',file ='"+f+"'  WHERE id = " +id ;
        
        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    @Override
  public List<Packag> inj(Service s)
  {
      String req="select type,price from service inner join packag on service.id=packag.sid ";
        List<Packag> list=new ArrayList<>();
        
       try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Packag r= new Packag();
                r.setType(rs.getString(1));
                  r.setPrice(rs.getInt(2));
                 list.add(r);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
  }
    @Override
    public int getSId() {
    
        String req="SELECT LAST_INSERT_ID()" ;
      
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                 return rs.getInt(1);   
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
     public ArrayList<String> getCategories(){

    
         String req="select cat FROM service";
   ArrayList<String> categories = new ArrayList<>();

        try {
            rs =  st.executeQuery(req);
            while (rs.next()) {
               categories.add(rs.getString("cat"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categories;
}
 
    }
  
    


   
    
