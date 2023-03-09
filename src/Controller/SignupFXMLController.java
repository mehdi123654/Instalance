/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.Admin.AdminChoiceFXMLController;
import entity.PasswordEncryption;
import entity.User;

import static service.Session.UserSession.CURRENT_USER;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.UserService;
import service.Session.AuthService;

/**
 * FXML Controller class
 *
 * @author Louay
 */
public class SignupFXMLController extends AuthService implements Initializable {

    public SignupFXMLController() throws SQLException {
        super();
        //TODO Auto-generated constructor stub
    }

    @FXML
    private ComboBox<String> cbRole;
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
    
    UserService us = new UserService();

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbRole.setItems(FXCollections.observableArrayList("Admin", "Freelancer", "Bussines Owner"));
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
        GotoFXML("view/AllUsers/LoginFXML", "Back to login", event);
    }
    
    

    @FXML
    private void addUser(ActionEvent event) throws IOException {
        String username = tfUsername.getText();
        String email = tfEmail.getText();
        String password = tfPass.getText();      
        String Urole = cbRole.getSelectionModel().getSelectedItem();
    
            User u = new User(username, email, password, Urole);
        try {
            if (us.insertUser(u)) {
                AlertWindow("Instalance", "Welcome " + username, Alert.AlertType.INFORMATION);
            } else {
                AlertWindow("Instalance", "Try again ", Alert.AlertType.ERROR);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SignupFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
            Stage stage = (Stage) btnCancel.getScene().getWindow();
            stage.close();
            GotoFXML("view/AllUsers/LoginFXML", "Back to login", event); 
    }

    @FXML
    private void handleCloseButtonAction(ActionEvent event) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
         stage.close();
    }  
}
