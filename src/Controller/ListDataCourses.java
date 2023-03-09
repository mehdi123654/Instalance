/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CourseDao;
import entity.Course;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author user
 */
class ListDataCourses {

    private ObservableList<Course> courses = FXCollections.observableArrayList();

    public ListDataCourses() {

        CourseDao pdao = CourseDao.getInstance();
        courses = pdao.displayAll();
        System.out.println(courses);
    }

    public ObservableList<Course> getCourses() {
        return courses;
    }
}
