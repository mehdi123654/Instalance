/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crossify.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author emnaa
 */
public class ApplicationFormController implements Initializable {

    @FXML
    private ImageView exit;
    @FXML
    private TextField EmailF;
    @FXML
    private TextField LN;
    @FXML
    private TextField FN;
    @FXML
    private TextField idFreelancer;
    @FXML
    private TextField cvURL;
    @FXML
    private Button browse;
    @FXML
    private Button apply;
    @FXML
    private TextField idInvisible;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
