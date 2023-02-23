/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursebase.dao;

import java.util.List;

/**
 *
 * @author wiemhjiri
 * @param <Course>
 *
 */
public interface Idao<Course> {

    public void insert(Course o);

    public void delete(int id);
 //public List<Course> displayAll2();
    public List<Course> displayAll();

    public Course displayById(int id);

    public boolean update(Course os);
}
