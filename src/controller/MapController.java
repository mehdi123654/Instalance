/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;

import entity.Workshop;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

    
/**
 * FXML Controller class
 *
 * @author fatha
 */
public class MapController implements Initializable {
    @FXML
    private WebView googlemaps;

    private String location;
    private BOmanagementController boManagementController;
    public void setLocation(String location)
    {
        this.location=location;
        googlemaps.getEngine().load("https://www.openstreetmap.org/search?query=" + location);
    }
    public void setBOManagementController(BOmanagementController boManagementController) {
        this.boManagementController = boManagementController;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // Get the latitude and longitude of the workshop location using the OpenStreetMap API

       // Load the OpenStreetMap URL with the workshop location as a query parameter
       googlemaps.getEngine().load("https://www.openstreetmap.org/search?query=" + location);
    
    }    
    
}
