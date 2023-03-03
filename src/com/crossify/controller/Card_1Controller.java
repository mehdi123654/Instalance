/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crossify.controller;

import com.crossify.entities.Freelance;
import com.crossify.services.CRUDFreelance;
import com.crossify.services.EmailSender;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javax.mail.MessagingException;

/**
 * FXML Controller class
 *
 * @author emnaa
 */
public class Card_1Controller implements Initializable {

    @FXML
    private HBox box;
    @FXML
    private ImageView logo;
    @FXML
    private Label category;
    @FXML
    private Label id;
    @FXML
    private Label description;
    @FXML
    private Label emailBO;
    @FXML
    private Label nbCondidats;
    @FXML
    private Label State;
    @FXML
    private ImageView apply;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CRUDFreelance crud = new CRUDFreelance();

        id.setVisible(false);

        apply.setOnMouseClicked(event -> {
            Freelance f = new Freelance();
            Freelance found = new Freelance();
            f = getData();
            found = crud.reaserchById(f);
            try {
                Stage newStage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/crossify/view/Freelancer/applicationForm.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                ApplicationFormController appFormController = fxmlLoader.getController();
                Stage stage = new Stage();

                //SEND EMAIL
                try {
                    EmailSender.sendEmail(found.getBO_email(), "Someone Consulted Your Freelance Offer", "Offer's Description:"+found.getDescription()+"/n Do you want to consult applicants list?");
                } catch (MessagingException e) {
                    e.printStackTrace();
                }

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

        File file = new File(f.getUrlLogo());
        String url;
        try {
            url = file.toURI().toURL().toString();
            Image image = new Image(url);
            logo.setImage(image);
        } catch (MalformedURLException ex) {
            Logger.getLogger(CardController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
