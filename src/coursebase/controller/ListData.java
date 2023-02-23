/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursebase.controller;

import coursebase.dao.CourseDao;
import coursebase.entity.Course;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author user
 */
class ListData {

    private ObservableList<Course> courses = FXCollections.observableArrayList();

    public ListData() {

        CourseDao pdao = CourseDao.getInstance();
        courses = pdao.displayAll();
        System.out.println(courses);
    }

    public ObservableList<Course> getCourses() {
        return courses;
    }
}
