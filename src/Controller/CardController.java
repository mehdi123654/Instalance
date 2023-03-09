/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Freelance;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.C;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import service.CRUDApplication;
import service.CRUDFreelance;

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

    @FXML
    public ImageView consultList;
    @FXML
    private ImageView logo;
    @FXML
    private ImageView apply;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CRUDFreelance crud = new CRUDFreelance();
        delete.setVisible(false);
        modify.setVisible(false);
        consultList.setVisible(false);
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
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/addOffer.fxml"));
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
        consultList.setOnMouseClicked(event -> {
            Freelance f = new Freelance();
            Freelance found = new Freelance();
            f = getData();
            found = crud.reaserchById(f);
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/crossify/view/BO/ListApplicants.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                ListApplicantsController listApplicantsController = fxmlLoader.getController();
                listApplicantsController.setId(found.getId_F());
                System.out.println("id sent: "+found.getId_F());

                // Use Platform.runLater to delay the initialization of the second controller
                /*Platform.runLater(() -> {
                    try {
                        listApplicantsController.initialize(null, null);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });*/
                Stage stage = new Stage();
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setScene(new Scene(root1));
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
    private String[] colors = {"915F6D", "E4ADBB", "FFFFFF", "FFE5F4"};

    public void setData(Freelance f) {
        CRUDApplication crudA = new CRUDApplication();
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
        int nb = crudA.nbApplicants(f.getId_F());
        nbCondidats.setText(Integer.toString(nb) + " Applicants");
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
