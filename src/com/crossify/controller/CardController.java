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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author emnaa
 */
public class CardController implements Initializable {

    @FXML
    private Label State;

    @FXML
    private HBox box;

    @FXML
    private Label category;

    @FXML
    private Label description;

    @FXML
    private Label emailBO;

    @FXML
    private Label nbCondidats;

    @FXML
    public ImageView modify;

    @FXML
    public ImageView delete;

    @FXML
    private Label id;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CRUDFreelance crud = new CRUDFreelance();
        delete.setVisible(false);
        modify.setVisible(false);
        id.setVisible(false);
        delete.setOnMouseClicked(event -> {
            Freelance f = new Freelance();
            f = getData();
            crud.deleteFreelance(f);
        });
        modify.setOnMouseClicked(event -> {
            Freelance f = new Freelance();
            Freelance found = new Freelance();
            f = getData();
            found = crud.reaserchById(f);
            try {
                Stage newStage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/crossify/view/addOffer.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                AddOfferController addOfferController = fxmlLoader.getController();

                addOfferController.category.setText(found.getCategory_F());
                addOfferController.description.setText(found.getDescription());
                addOfferController.emailBO.setText(found.getBO_email());
                addOfferController.idInvisible.setText(Integer.toString(found.getId_F()));
                addOfferController.idBO.setText(Integer.toString(found.getBO_id()));
                addOfferController.budget.setText(Float.toString(found.getBudget()));

                Stage stage = new Stage();

                stage.setScene(new Scene(root1));
                stage.show();
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
    private String[] colors = {"915F6D", "E4ADBB", "FFFFFF", "FFE5F4"};

    public void setData(Freelance f) {
        String stateString;
        if (f.isState_F() == true) {
            stateString = "Available";
        } else {
            stateString = "Closed";
        }
        category.setText(f.getCategory_F());
        description.setText(f.getDescription());
        emailBO.setText(f.getBO_email());
        id.setText(Integer.toString(f.getId_F()));
        nbCondidats.setText("20 Condidats");
        State.setText(stateString);
        box.setStyle("-fx-background-color: #" + colors[(int) (Math.random() * colors.length)] + ";"
                + "-fx-background-radius: 15;"
                + "-fx-effect: dropShadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0,10);");
    }

    public Freelance getData() {
        int idOffer = Integer.parseInt(id.getText());
        String email = emailBO.getText();
        String cat = category.getText();
        String descr = description.getText();
        Freelance f = new Freelance(idOffer, email, cat, descr);
        return f;
    }

}
