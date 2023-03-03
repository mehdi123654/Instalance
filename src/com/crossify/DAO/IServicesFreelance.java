/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crossify.DAO;

import com.crossify.entities.Freelance;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author emnaa
 */
public interface IServicesFreelance {
    public void addFreelance(Freelance f);
    public void addFreelance2 (Freelance f);
    public void modifyFreelance(Freelance f);
    public void deleteFreelance(Freelance f);
    public List<Freelance> displayFreelance();
    public ObservableList<Freelance> displayFreelancee();
    public ObservableList<Freelance> displayNewFreelancee();
    public ObservableList<Freelance> displayMyFreelancee(int id);
    public Freelance reaserchById(Freelance f);
    public ObservableList<Freelance> advancedreaserch(String search);
    public ObservableList<String> getAllCategories() ;
    public ObservableList<Freelance> filterByCategory(String cat);
}
