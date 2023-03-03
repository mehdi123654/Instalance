/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursebase.dao;

import coursebase.entity.Lesson;
import coursebase.utils.ConnexionSingleton;
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
 * @author user
 */
public class LessonDao implements Ldao<Lesson>{
  private static LessonDao instance;
    private Statement st;
    private ResultSet rs;

    private LessonDao() {
        ConnexionSingleton cs = ConnexionSingleton.getInstance();
        try {
            st = cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(LessonDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static LessonDao getInstance() {
        if (instance == null) {
            instance = new LessonDao();
        }
        return instance;
    }

   
  @Override
    public void insert(Lesson o) {
       
        String req = "insert into lesson (cid,name,file) values ('" + o.getCid() + "','" + o.getName() + "','" + o.getFile() + "')";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(LessonDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  @Override
    public void delete(int id) {
        String req = "delete from lesson where lid=" + id;
       

        if (id != 0) {
            try {

                st.executeUpdate(req);

            } catch (SQLException ex) {
                Logger.getLogger(LessonDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("n'existe pas");
        }
    }

    public String displayCourse (int id ) {
       String req = "SELECT title FROM lesson INNER JOIN course ON lesson.cid = course.cid where lesson.lid ="+id;
      
String k = null;
        try {
            rs = st.executeQuery(req);
            while (rs.next()) {
              k=rs.getString(1);
               
            }

        } catch (SQLException ex) {
            Logger.getLogger(LessonDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return k;
    }
 
  @Override
    public List<Lesson> displayAll2() {
       String req = "SELECT * FROM lesson  " ;
        ObservableList<Lesson> list = FXCollections.observableArrayList();

        try {
            rs = st.executeQuery(req);
            while (rs.next()) {
                Lesson p = new Lesson();
                p.setLid(rs.getInt(1));
                  p.setCid(rs.getInt(2));
                p.setName(rs.getString(3));
                p.setFile(rs.getString(4));
             

                list.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(LessonDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    @Override
    public Lesson displayById(int id) {
           String req="select * from lesson where lid ="+id;
           Lesson p=new Lesson();
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
         
                p.setLid(rs.getInt("lid"));
                p.setCid(rs.getInt("cid"));
                p.setName(rs.getString("name"));
               p.setFile(rs.getString("file"));
            }  
        } catch (SQLException ex) {
            Logger.getLogger(LessonDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    return p;
    }


  @Override
    public List<Lesson> displayAll(int i) {
       String req = "SELECT * FROM lesson  where cid =" + i;
        ObservableList<Lesson> list = FXCollections.observableArrayList();

        try {
            rs = st.executeQuery(req);
            while (rs.next()) {
                Lesson p = new Lesson();
                p.setLid(rs.getInt(1));
                  p.setCid(rs.getInt(2));
                p.setName(rs.getString(3));
                p.setFile(rs.getString(4));
             

                list.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(LessonDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }


    @Override
    public boolean update(Lesson p) {
        String qry = "UPDATE lesson SET cid = '" + p.getCid() + "', name = '" + p.getName() + "', file = '" + p.getFile() + "' WHERE lid = " + p.getLid();

        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(LessonDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public void deleteall(int[] myArray) {
       for (int i = 0; i < myArray.length; i++) {
   String req = "delete from lesson where lid="+ myArray[i] ;
    try {

                st.executeUpdate(req);

            } catch (SQLException ex) {
                Logger.getLogger(LessonDao.class.getName()).log(Level.SEVERE, null, ex);
            }
}
    }

    
    }
    

