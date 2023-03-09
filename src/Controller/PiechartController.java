/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import dao.ServiceDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author belhassan
 */
public class PiechartController implements Initializable {

    
    
       @FXML
    private PieChart PieChart;
ObservableList<PieChart.Data> list = FXCollections.observableArrayList();

    @FXML
    private Button DS;

    /**
     * Initializes the controller class.
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          ServiceDao pdao = ServiceDao.getInstance();
        ArrayList<String> categories = pdao.getCategories();

        Map<String, Integer> categoryCount = new HashMap<>();
        for (String category : categories) {
            if (categoryCount.containsKey(category)) {
                categoryCount.put(category, categoryCount.get(category) + 1);
            } else {
                categoryCount.put(category, 1);
            }
        }

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (Map.Entry<String, Integer> entry : categoryCount.entrySet()) {
            pieChartData.add(new PieChart.Data(entry.getKey(), entry.getValue()));
        }
         PieChart.setData(pieChartData);
       PieChart.setTitle("Category Statistics");


        // TODO
    }    
    
}
