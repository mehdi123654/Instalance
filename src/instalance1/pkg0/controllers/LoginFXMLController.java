/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instalance1.pkg0.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Louay
 */
public class LoginFXMLController implements Initializable {

    @FXML
    private TextField tfEmail;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void resetPassword(MouseEvent event) {
    }

    @FXML
    private void redirectToSignup(MouseEvent event) {
    }

    @FXML
    private void loginUser(ActionEvent event) {
    }

    @FXML
    private void handleCloseButtonAction(MouseEvent event) {
    }
    
}
