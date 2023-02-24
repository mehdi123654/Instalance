/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crossify.controller;

import com.crossify.entities.Freelance;
import com.crossify.services.CRUDFreelance;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author emnaa
 */
public class AddOfferController implements Initializable {
    
    private static final Pattern EMAIL_PATTERN = Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    private int empty = 1, probfloat = 0, probEmail = 0;
    
    @FXML
    public TextField budget;
    
    @FXML
    public TextField category;
    
    @FXML
    public TextArea description;
    
    @FXML
    public TextField emailBO;
    
    @FXML
    public TextField idBO;
    
    @FXML
    public Button add;
    
    @FXML
    public TextField idInvisible;
    
    @FXML
    public ImageView exit;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idInvisible.setVisible(false);
        //control de saisie
        //no empty fields
        //number
        idBO.setTextFormatter(new TextFormatter<>(change -> {
            String text = change.getText();
            if (text.matches("[0-9]*")) {
                return change;
            }
            return null;
        }));
        //float
        budget.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("^\\d*\\.?\\d*$")) {
                budget.setStyle("-fx-background-color: white;");
                probfloat = 0;
            } else {
                probfloat = 1;
                budget.setStyle("-fx-background-color: red;");
            }
        });

        //email
        emailBO.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!isValidEmail(newValue)) {
                emailBO.setStyle("-fx-background-color: red;");
                probEmail = 0;
            } else {
                probEmail = 1;
                emailBO.setStyle("-fx-background-color: white;");
            }
        });

        //when can i call the add button?
        add.setOnAction(event -> {
            if (!idBO.getText().isEmpty() && !emailBO.getText().isEmpty() && !category.getText().isEmpty() && !budget.getText().isEmpty() && !description.getText().isEmpty()) {
                empty = 0;
            } else {
                empty = 1;
            }
            if (empty == 0 && probfloat == 0 && probEmail == 0) {
                CRUDFreelance crud = new CRUDFreelance();
                crud.addFreelance2(getData());
                empty = 1;
                
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Could not post the offer");
                alert.setHeaderText("An error has occurred! ");
                alert.setContentText("Check the info fields! ");
                alert.showAndWait();
            }
        });
        
        exit.setOnMouseClicked(event -> {
            //saker scene jdida
            Stage stage = (Stage) exit.getScene().getWindow();
            stage.close();
            
        });
        
    }
    
    public Freelance getData() {
        int idFree =Integer.parseInt(idInvisible.getText());
        int idBOw = Integer.parseInt(idBO.getText());
        String email = emailBO.getText();
        String cat = category.getText();
        float bud = Float.parseFloat(budget.getText());
        String descr = description.getText();
        Freelance f = new Freelance(idFree,idBOw, email, cat, descr, bud, true);
        return f;
    }
    
    private boolean isValidEmail(String email) {
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }
}
