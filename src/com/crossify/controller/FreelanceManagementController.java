/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crossify.controller;

import com.crossify.entities.Freelance;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import com.crossify.services.CRUDFreelance;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author emnaa
 */
public class FreelanceManagementController implements Initializable {

    private int showMyOffers = 0, showAll = 1;

    @FXML
    private Button addbtn;

    @FXML
    private HBox cardLayout;

    @FXML
    private ImageView exit;

    @FXML
    private GridPane offerContainer;

    @FXML
    private HBox myOffers;
    
    @FXML
    private HBox allOffers;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //affichage
        CRUDFreelance crud = new CRUDFreelance();
        ObservableList<Freelance> myList = FXCollections.observableArrayList();
        myList = crud.displayNewFreelancee();
        ObservableList<Freelance> allOffersList = FXCollections.observableArrayList();
        allOffersList = crud.displayFreelancee();

        int column = 0;
        int row = 1;
        exit.setOnMouseClicked(event -> {
            System.exit(0);
        });

        addbtn.setOnAction(event -> {

            try {
                Stage newStage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/crossify/view/addOffer.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                //stage.setTitle("Add Offer");
                stage.setScene(new Scene(root1));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        myOffers.setOnMouseClicked(event -> {
            try {
                Stage newStage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/crossify/view/MyOffers.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                
                stage.setScene(new Scene(root1));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        
        

        //show newest always
        for (int i = 0; i < myList.size(); i++) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/crossify/view/card.fxml"));
                HBox cardBox = fxmlLoader.load();
                CardController cardController = fxmlLoader.getController();

                cardController.setData(myList.get(i));

                cardLayout.getChildren().add(cardBox);
            } catch (IOException ex) {
                Logger.getLogger(FreelanceManagementController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

//affichage
        
            try {

                for (Freelance freelance : allOffersList) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/com/crossify/view/card.fxml"));
                    HBox cardBox = fxmlLoader.load();
                    CardController cardController = fxmlLoader.getController();
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
