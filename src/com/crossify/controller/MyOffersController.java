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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author emnaa
 */
public class MyOffersController implements Initializable {

    @FXML
    private ImageView refresh;
    @FXML
    private HBox allOffers;
    @FXML
    private HBox myOffers;
    @FXML
    private Button addbtn;
    @FXML
    private GridPane offerContainer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CRUDFreelance crud = new CRUDFreelance();
        ObservableList<Freelance> allOffersList = FXCollections.observableArrayList();
        allOffersList = crud.displayMyFreelancee(20);
        int column = 0;
        int row = 1;
        
        
        
        allOffers.setOnMouseClicked(event -> {
            try {
                Stage newStage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/crossify/view/FreelanceManagement.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                
                stage.setScene(new Scene(root1));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        try {

                for (Freelance freelance : allOffersList) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/com/crossify/view/card.fxml"));
                    HBox cardBox = fxmlLoader.load();
                    CardController cardController = fxmlLoader.getController();
                    cardController.modify.setVisible(true);
                    cardController.delete.setVisible(true);
                    cardController.setData(freelance);
                    if (column == 2) {
                        column = 0;
                        ++row;
                    }
                    offerContainer.add(cardBox, column++, row);
                    GridPane.setMargin(cardBox, new Insets(10));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
    }    
    
}
