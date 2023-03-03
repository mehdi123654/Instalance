/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crossify.controller;

import freelancemanagement.FreelanceManagement;
import com.crossify.entities.Freelance;
import com.crossify.services.CRUDFreelance;
import static freelancemanagement.FreelanceManagement.allOffersScene;
import static com.crossify.controller.FreelanceManagementController.myOffersScene;
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
import javafx.scene.input.MouseDragEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author emnaa
 */
public class MyOffersController implements Initializable {
    ObservableList<Freelance> allOffersList;

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
    @FXML
    private ImageView exit;

    @FXML
    void refreshDisplay(MouseDragEvent event) {
        System.out.println("Refreshing...");
        
        /*clear(allOffersList);
        CRUDFreelance crud = new CRUDFreelance();
        ObservableList<Freelance> refreshedList = FXCollections.observableArrayList();
        refreshedList = crud.displayMyFreelancee(20);
        int col = 0;
        int roww = 1;
        try {
            for (Freelance freelance : refreshedList) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/crossify/view/card.fxml"));
                HBox cardBox = fxmlLoader.load();
                CardController cardController = fxmlLoader.getController();
                cardController.modify.setVisible(true);
                cardController.delete.setVisible(true);
                cardController.setData(freelance);
                if (col == 2) {
                    col = 0;
                    ++roww;
                }
                offerContainer.add(cardBox, col++, roww);
                GridPane.setMargin(cardBox, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CRUDFreelance crud = new CRUDFreelance();
        allOffersList = FXCollections.observableArrayList();
        allOffersList = crud.displayMyFreelancee(20);
        int column = 0;
        int row = 1;
        
        exit.setOnMouseClicked(event -> {
            javafx.application.Platform.exit();
        });

        allOffers.setOnMouseClicked(event -> {
            FreelanceManagement.window.setScene(allOffersScene);
        });

        //AFFICHAGE all the time
        try {
            for (Freelance freelance : allOffersList) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/crossify/view/BO/card.fxml"));
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

    public void clear(ObservableList<Freelance> list) {
        list.clear();
    }

}
