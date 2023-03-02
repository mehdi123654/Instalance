/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.PackagDao;
import entities.Packag;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author belhassan
 */
public class ListPackag {
    private ObservableList<Packag> packags=FXCollections.observableArrayList();

    public ListPackag() {
        
        PackagDao pdao=PackagDao.getInstance();
        packags= pdao.displayAll();
        System.out.println(packags);
    }
    
    public ObservableList<Packag> getPackags(){
        return packags;
    }
}