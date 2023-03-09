/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Admin;

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
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Louay
 */
public class AdminChoiceFXMLController implements Initializable {

    @FXML
    private Button profil;
    @FXML
    private ImageView logout;
    @FXML
    private Label UserName;
    @FXML
    private Label Role;
    @FXML
    private Button btnUser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    private void GotoFXML(String vue, String title,Event aEvent) {
           try {
               Event event;
               FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(vue + ".fxml"));
               Parent root1 = (Parent) fxmlLoader.load();
               Stage stage =(Stage)((Node) aEvent.getSource()).getScene().getWindow() ;
               stage.setTitle(title);
               stage.setScene(new Scene(root1));
               stage.show();
           } catch (IOException ex) {
               Logger.getLogger(AdminChoiceFXMLController.class.getName()).log(Level.SEVERE, null, ex);
           }
       }

    @FXML
    private void redirectToManageProfile(ActionEvent event) {
        GotoFXML("AdminProfileFXML", "AdminProfile",event);
    }

    @FXML
    private void Logout(MouseEvent event) {
    }

    @FXML
    private void redirectToAdminDashboard(ActionEvent event) {
        GotoFXML("AdminDashboard","Menu for Admin",event);
    }
    
}
