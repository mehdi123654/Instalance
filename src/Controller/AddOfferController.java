/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Freelance;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import service.CRUDFreelance;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author emnaa
 */
public class AddOfferController implements Initializable {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    private int empty = 1, probfloat = 0, probEmail = 0;
    
    //private Stage addStage;

    /*public AddOfferController(Stage addStage) {
        this.addStage = addStage;
    }*/

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
    public Button Update;

    @FXML
    public TextField idInvisible;

    @FXML
    public ImageView exit;

    @FXML
    private Button browse;

    @FXML
    private TextField LogoURL;

    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        

        String urlLogo;
        idInvisible.setText("0");
        CRUDFreelance crud = new CRUDFreelance();
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

            crud.addFreelance2(getData());
            /*if (!idBO.getText().isEmpty() && !emailBO.getText().isEmpty() && !category.getText().isEmpty() && !budget.getText().isEmpty() && !description.getText().isEmpty()) {
                empty = 0;
            } else {
                empty = 1;
            }
            //if (empty == 0 && probfloat == 0 && probEmail == 0) {
                
                crud.addFreelance2(getData());
                //empty = 1;

           // } else {
                //Alert alert = new Alert(Alert.AlertType.ERROR);
                //alert.setTitle("Could not post the offer");
                //alert.setHeaderText("An error has occurred! ");
                //alert.setContentText("Check the info fields! ");
                //alert.showAndWait();
            }*/
        });

        Update.setOnAction(event -> {
            Freelance updatedFree = getData();
            crud.modifyFreelance(updatedFree);
        });

        exit.setOnMouseClicked(event -> {
            //saker scene jdida
            Stage stage = (Stage) exit.getScene().getWindow();
            stage.close();

        });
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Logo");
        FileChooser.ExtensionFilter filter1 = new FileChooser.ExtensionFilter("JPEG FILES", "*.jpg");
        FileChooser.ExtensionFilter filter2 = new FileChooser.ExtensionFilter("PNG FILES", "*.png");
        fileChooser.getExtensionFilters().addAll(filter1, filter2);
        browse.setOnAction(event -> {
            //filechooser
            File selectedFile = fileChooser.showOpenDialog(FreelanceManagementController.addStage);
            if (selectedFile != null) {
                LogoURL.setText(selectedFile.getAbsolutePath().toString());
            }

        });

    }

    public Freelance getData() {
        int idFree = Integer.parseInt(idInvisible.getText());
        int idBOw = Integer.parseInt(idBO.getText());
        String email = emailBO.getText();
        String cat = category.getText();
        float bud = Float.parseFloat(budget.getText());
        String descr = description.getText();
        String url = LogoURL.getText();
        Freelance f = new Freelance(idFree, idBOw, email, cat, descr, url, bud, true);
        return f;
    }

    private boolean isValidEmail(String email) {
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }
}
