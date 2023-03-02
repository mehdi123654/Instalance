/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.ServiceDao;
import entities.Service;
import java.util.Collections;
import java.util.Comparator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author belhassan
 */
public class ListData {
    private ObservableList<Service> services=FXCollections.observableArrayList();

    public ListData() {
        
          ServiceDao sdao=ServiceDao.getInstance();
        services= sdao.displayAll();
        System.out.println(services);
    }
    
    public ObservableList<Service> getServices(){
        return services;
    }
   
    
     
}
