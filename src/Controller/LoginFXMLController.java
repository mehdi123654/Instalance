/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static service.Session.UserSession.CURRENT_USER;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import entity.PasswordEncryption;
import entity.User;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.UserService;
import service.Wrapper;
import service.Session.AuthService;

/**
 * FXML Controller class
 *
 * @author Louay
 */
public class LoginFXMLController extends  AuthService implements Initializable {

    public LoginFXMLController() throws SQLException {
        super();
        //TODO Auto-generated constructor stub
    }

    @FXML
    private TextField tfUsername;
    @FXML
    private PasswordField tfPass;
    @FXML
    private CheckBox chSeePass;
    @FXML
    private Label passForgot;
    @FXML
    private Label labNew;
    @FXML
    private Button btnContinue;
    @FXML
    private Button btnClose;
    @FXML
    private Label labelError;
    UserService us = new UserService();

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
            Logger.getLogger(LoginFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void AlertWindow(String title, String contenu, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contenu);
        alert.showAndWait();
    }

    @FXML
    private void resetPassword(MouseEvent event) {
    }

    @FXML
    private void redirectToSignup(MouseEvent event) {
        GotoFXML("views/AllUsers/SignupFXML", "Welcome",event);
    }

    @FXML
    private void loginUser(ActionEvent event) throws IOException {
        Authenticate(tfUsername.getText().trim(), PasswordEncryption.cryptage(tfPass.getText().trim()));
        if((CURRENT_USER.getUser_LoggedIn().getIsBanned())){
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ban");
        alert.setHeaderText("You are banned");
        alert.setContentText("You are banned");
        alert.showAndWait();}
       
            
        if(CURRENT_USER  == null){
           labelError.setText("Bad Credentials");
       }
       if(CURRENT_USER != null){
    
       if((isFreelancer(CURRENT_USER.getUser_LoggedIn().getIdUser()))){
           Parent root = FXMLLoader.load(getClass().getResource("view/Admin/AdminDashboard.fxml"));
        Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
       }
        
       if((isAdmin(CURRENT_USER.getUser_LoggedIn().getIdUser())) && (CURRENT_USER.getUser_LoggedIn().getIsBanned() == false) ){
        Parent root = FXMLLoader.load(getClass().getResource("view/WelcomeFreelancer.fxml"));
        Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

       }
        if((isBusinessOwner(CURRENT_USER.getUser_LoggedIn().getIdUser()))){
           Parent root = FXMLLoader.load(getClass().getResource("view/Admin/Welcome.fxml"));
           Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
       }
       }
    }

    @FXML
    private void handleCloseButtonAction(MouseEvent event) {
    }
    
}
