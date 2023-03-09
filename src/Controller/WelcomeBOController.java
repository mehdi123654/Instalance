/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
//import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author user
 */
public class WelcomeBOController implements Initializable {
   

    @FXML
    private Button mycrs;

    @FXML
    private Button myserv;

    @FXML
    private Button evnt;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
        mycrs.setOnAction(
            event -> {

                try {
                    Parent page1 = FXMLLoader.load(getClass().getResource("/view/explore.fxml"));
                    Scene scene = new Scene(page1);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(ShowCourseController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    );
       
    myserv.setOnAction(
                event -> {

                    try {
                        Parent page1 = FXMLLoader.load(getClass().getResource("/view/Show.fxml"));
                        Scene scene = new Scene(page1);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(ShowController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        );
        evnt.setOnAction(
            event -> {

                try {
                    Parent page1 = FXMLLoader.load(getClass().getResource("/view/BOmanagement.fxml"));
                    Scene scene = new Scene(page1);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(BOmanagementController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    );
        
       
            }
}
