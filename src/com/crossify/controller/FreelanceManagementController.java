/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crossify.controller;

import com.crossify.entities.Freelance;
import freelancemanagement.FreelanceManagement;
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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author emnaa
 */
public class FreelanceManagementController implements Initializable {

    public static Stage addStage;
    public static Scene addOfferScene, myOffersScene;
    private int searchTyped = 1;

    private int showMyOffers = 0, showAll = 1;

    @FXML
    private Label categoriesLabel;
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

    @FXML
    private TextField searchBar;

    @FXML
    private ListView<String> categoriesList;

    @FXML
    private Label allOffersLabel;

    @FXML
    private Label highDemandeLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //affichage
        CRUDFreelance crud = new CRUDFreelance();

        ObservableList<Freelance> myList = FXCollections.observableArrayList();

        myList = crud.displayNewFreelancee();

        final ObservableList<Freelance> allOffersList = FXCollections.observableArrayList();
        allOffersList.setAll(crud.displayFreelancee());

        // Create the searchedOffers and displayedOffers lists and initialize them with allOffers
        ObservableList<Freelance> searchedOffers = FXCollections.observableArrayList(allOffersList);
        ObservableList<Freelance> displayedOffers = FXCollections.observableArrayList(allOffersList);

        //getting the categories : CALL
        ObservableList<String> categories = FXCollections.observableArrayList(crud.getAllCategories());

        //adding categories to the list view
        categoriesList.getItems().addAll(categories);
        categoriesList.setVisible(false);

        exit.setOnMouseClicked(event -> {
            javafx.application.Platform.exit();
        });

        addbtn.setOnAction(event -> {

            try {
                addStage = new Stage();
                addStage.initStyle(StageStyle.UNDECORATED);
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/crossify/view/BO/addOffer.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                addOfferScene = new Scene(root1);
                addStage.setScene(addOfferScene);
                addStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        myOffers.setOnMouseClicked(event -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/crossify/view/BO/MyOffers.fxml"));
                Parent root2 = (Parent) fxmlLoader.load();
                myOffersScene = new Scene(root2);
                FreelanceManagement.window.setScene(myOffersScene);
                FreelanceManagement.window.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        //search bar
        searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                displayedOffers.setAll(allOffersList);
            } else {
                searchedOffers.clear();
                displayedOffers.clear();
                searchedOffers.setAll(crud.advancedreaserch(newValue));
                displayedOffers.setAll(searchedOffers);
                displayOffers(displayedOffers, 0, 1);
            }
        });

        //filters
        //Filter By CATEGORY
        categoriesLabel.setOnMouseClicked(event -> {
            if (!categoriesList.isVisible()) {
                categoriesList.setVisible(true);
            }
        });
        categoriesList.setOnMouseClicked(event -> {
            String selectedCategory = categoriesList.getSelectionModel().getSelectedItem();
            if (selectedCategory != null) {
                searchedOffers.clear();
                displayedOffers.clear();
                displayedOffers.setAll(crud.filterByCategory(selectedCategory));
                displayOffers(displayedOffers, 0, 1);
                categoriesList.setVisible(false);
            }
        });
        //Filter BY DEMAND
        highDemandeLabel.setOnMouseClicked(event -> {
            searchedOffers.clear();
            displayedOffers.clear();
            displayedOffers.setAll(crud.sortByDemand());
            displayOffers(displayedOffers, 0, 1);
        });

        //afficher kol chy ml all offers label
        allOffersLabel.setOnMouseClicked(event -> {
            searchedOffers.clear();
            displayedOffers.clear();
            displayedOffers.setAll(allOffersList);
            displayOffers(displayedOffers, 0, 1);
        });

        //show newest always
        for (int i = 0;
                i < myList.size();
                i++) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/crossify/view/BO/card.fxml"));
                HBox cardBox = fxmlLoader.load();
                CardController cardController = fxmlLoader.getController();
                cardController.setData(myList.get(i));
                cardLayout.getChildren().add(cardBox);
            } catch (IOException ex) {
                Logger.getLogger(FreelanceManagementController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //affichage
        displayOffers(displayedOffers, 0, 1);

    }

    public void displayOffers(ObservableList<Freelance> list, int column, int row) {
        try {
            // clear the offerContainer first
            offerContainer.getChildren().clear();

            for (Freelance freelance : list) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/crossify/view/BO/card.fxml"));
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
