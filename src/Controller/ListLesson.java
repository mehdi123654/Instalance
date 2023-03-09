/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.LessonDao;
import entity.Lesson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author user
 */
public class ListLesson {

    private ObservableList<Lesson> joins = FXCollections.observableArrayList();

    public ListLesson(int i) {

        LessonDao pdao = LessonDao.getInstance();
        joins = (ObservableList<Lesson>) pdao.displayAll(i);
        System.out.println(joins);
    }

    public ListLesson() {

        LessonDao pdao = LessonDao.getInstance();
        joins = (ObservableList<Lesson>) pdao.displayAll2();
        System.out.println(joins);
    }

    public ObservableList<Lesson> getJoins() {
        return joins;
    }
}
