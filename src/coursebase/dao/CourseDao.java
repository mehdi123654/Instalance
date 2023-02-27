/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursebase.dao;

import coursebase.entity.Course;
import coursebase.utils.ConnexionSingleton;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author user
 */
public class CourseDao implements Idao<Course> {

    private static CourseDao instance;
    private Statement st;
    private ResultSet rs;

    private CourseDao() {
        ConnexionSingleton cs = ConnexionSingleton.getInstance();
        try {
            st = cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static CourseDao getInstance() {
        if (instance == null) {
            instance = new CourseDao();
        }
        return instance;
    }

    @Override
    public void insert(Course o) {

        String req = "insert into course (title,description,price,category,photo) values ('" + o.getTitle() + "','" + o.getDecription() + "','" + o.getPrice() + "','" + o.getCategory() + "','" + o.getPhoto() + "')";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) {
     
        String req = "delete from course where cid=" + id;
       

        if (id != 0) {
            try {

                st.executeUpdate(req);

            } catch (SQLException ex) {
                Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("n'existe pas");
        }
    }
    @Override
  public ArrayList<String> getCategories(){

     // String req = " SELECT c.cid,title,description,price,category,photo,count(lesson.lid)  FROM course c  INNER JOIN lesson ON c.cid = lesson.cid GROUP BY c.cid";
         String req="select category FROM course";
   ArrayList<String> categories = new ArrayList<>();

        try {
            rs =  st.executeQuery(req);
            while (rs.next()) {
               categories.add(rs.getString("category"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categories;
    }
    
   
    @Override
    public ObservableList<Course> filter(String cat) {
     // String req = " SELECT c.cid,title,description,price,category,photo,count(lesson.lid)  FROM course c  INNER JOIN lesson ON c.cid = lesson.cid GROUP BY c.cid";
         String req="select * from course where category =" + cat;
     ObservableList<Course> list = FXCollections.observableArrayList();

        try {
            rs = st.executeQuery(req);
            while (rs.next()) {
                Course p = new Course();
                p.setCid(rs.getInt(1));
                  p.setTitle(rs.getString(2));
                p.setDecription(rs.getString(3));
                p.setPrice(rs.getInt(4));
                p.setCategory(rs.getString(5));
                p.setPhoto(rs.getString(6));
              //   p.setnbLessons(rs.getInt(7));

                list.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }   

    @Override
    public ObservableList<Course> displayAll() {
     // String req = " SELECT c.cid,title,description,price,category,photo,count(lesson.lid)  FROM course c  INNER JOIN lesson ON c.cid = lesson.cid GROUP BY c.cid";
         String req="select * from course";
     ObservableList<Course> list = FXCollections.observableArrayList();

        try {
            rs = st.executeQuery(req);
            while (rs.next()) {
                Course p = new Course();
                p.setCid(rs.getInt(1));
                  p.setTitle(rs.getString(2));
                p.setDecription(rs.getString(3));
                p.setPrice(rs.getInt(4));
                p.setCategory(rs.getString(5));
                p.setPhoto(rs.getString(6));
              //   p.setnbLessons(rs.getInt(7));

                list.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Course display(Course c) {
        String req = "select * from course where cid =" + c.getCid();
        Course p = new Course();
        try {
            rs = st.executeQuery(req);
            // while(rs.next()){
            rs.next();

            p.setCid(rs.getInt("cid"));
            p.setDecription(rs.getString("description"));
            p.setPrice(rs.getInt("price"));
            p.setCategory(rs.getString("category"));
            p.setPhoto(rs.getString("photo"));
            //}  
        } catch (SQLException ex) {
            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }
 
    
    public Course search(int id) {
        String req = "SELECT * FROM course WHERE cid LIKE "+ id;
        Course p = new Course();
        try {
            rs = st.executeQuery(req);
            // while(rs.next()){
             p.setCid(rs.getInt("cid"));
            p.setDecription(rs.getString("description"));
            p.setPrice(rs.getInt("price"));
            p.setCategory(rs.getString("category"));
            p.setPhoto(rs.getString("photo"));
            //}  
        } catch (SQLException ex) {
            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    @Override
    public boolean update(Course p) {

        String qry = "UPDATE course SET title = '" + p.getTitle() + "', description = '" + p.getDecription() + "', price = '" + p.getPrice() + "', category = '" + p.getCategory() + "', photo = '" + p.getPhoto() + "' WHERE cid = " + p.getCid();

        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Course displayById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
