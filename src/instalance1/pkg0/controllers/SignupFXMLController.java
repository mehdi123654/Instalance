/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instalance1.pkg0.controllers;

import instalance1.pkg0.controllers.Admin.AdminChoiceFXMLController;
import instalance1.pkg0.entities.User;
import instalance1.pkg0.services.UserServices;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Louay
 */
public class SignupFXMLController implements Initializable {

    @FXML
    private TextField tfUsername;
    @FXML
    private TextField tfEmail;
    @FXML
    private PasswordField tfPass;
    @FXML
    private Label newaccout;
    @FXML
    private Label error_numtel;
    @FXML
    private Button btnSignup;
    @FXML
    private Button btnCancel;
    
    UserServices us = new UserServices();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    public void AlertWindow(String title, String contenu, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contenu);
        alert.showAndWait();
    }
    
    private void GotoFXML(String vue, String title, Event aEvent) {
        try {
            Event event;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(vue + ".fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = (Stage) ((Node) aEvent.getSource()).getScene().getWindow();
            stage.setTitle(title);
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AdminChoiceFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void gotoLogin(MouseEvent event) {
        GotoFXML("../views/AllUsers/LoginFXML", "Back to login", event);
    }

    @FXML
    private void addUser(ActionEvent event) {
        String username = tfUsername.getText();
        String email = tfEmail.getText();
        String password = tfPass.getText();
       
        String role = "Freelancer";
    
            User u = new User(username, email, password, role);
        try {
            if (us.insertUser(u)) {
                AlertWindow("Instalance", "Welcome " + username, Alert.AlertType.INFORMATION);
            } else {
                AlertWindow("Instalance", "Try again", Alert.AlertType.ERROR);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SignupFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
            Stage stage = (Stage) btnCancel.getScene().getWindow();
            stage.close();
            GotoFXML("instalance1/pkg0/views/AllUsers/LoginFXML", "Back to login", event);
    }

    @FXML
    private void handleCloseButtonAction(ActionEvent event) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
         stage.close();
    }
    
}
