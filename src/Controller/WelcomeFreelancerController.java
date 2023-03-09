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
public class WelcomeFreelancerController implements Initializable {
   

    @FXML
    private Button prt;
    @FXML
    private Button ofrs;
    @FXML
    private Button mycrs;

    @FXML
    private Button myserv;

    @FXML
    private Button blog;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
        blog.setOnAction(
            event -> {

                try {
                    Parent page1 = FXMLLoader.load(getClass().getResource("/view/affiche_blog.fxml"));
                    Scene scene = new Scene(page1);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(ShowCourseController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    );
       
        mycrs.setOnAction(
                event -> {

                    try {
                        Parent page1 = FXMLLoader.load(getClass().getResource("/view/ShowCourse.fxml"));
                        Scene scene = new Scene(page1);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(ShowCourseController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        );
        ofrs.setOnAction(
            event -> {

                try {
                    Parent page1 = FXMLLoader.load(getClass().getResource("/view/FreelanceManagement_1.fxml"));
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
                    Parent page1 = FXMLLoader.load(getClass().getResource("/view/Display.fxml"));
                    Scene scene = new Scene(page1);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(ShowCourseController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    );
    prt.setOnAction(
        event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/Freelancermanagement.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ShowCourseController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
);
       
            }
}
