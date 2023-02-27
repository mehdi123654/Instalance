/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursebase.dao;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author wiemhjiri
 * @param <Course>
 *
 */
public interface Idao<Course> {
     public ObservableList<Course> filter(String cat) ;
    public void insert(Course o);
    public ArrayList<String> getCategories();
    public void delete(int id);
 //public List<Course> displayAll2();
    public List<Course> displayAll();

    public Course displayById(int id);

    public boolean update(Course os);
}
