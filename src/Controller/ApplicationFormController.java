/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Application;
import entity.Freelance;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import service.CRUDApplication;
import service.CRUDFreelance;
 
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

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
    public TextField idInvisible;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idInvisible.setVisible(false);
        apply.setOnAction(event -> {
            CRUDApplication crudApp = new CRUDApplication();
            crudApp.addApplication(getData());

        });
        //storing PDF file cv

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Insert CV");
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("PDF FILES", "*.pdf");
        fileChooser.getExtensionFilters().addAll(filter);

        browse.setOnAction(event -> {
            // Show file chooser dialog
            File selectedFile = fileChooser.showOpenDialog(FreelanceManagementController.addStage);
            if (selectedFile != null) {
                try {
                    // Create a directory to store PDF files if it doesn't exist
                    File pdfDir = new File("pdfs");
                    if (!pdfDir.exists()) {
                        pdfDir.mkdir();
                    }

                    // Save the selected file to the pdf directory with a unique file name
                    String fileName = UUID.randomUUID().toString() + ".pdf";
                    File destinationFile = new File(pdfDir.getAbsolutePath() + File.separator + fileName);
                    Files.copy(selectedFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                    // Set the CV URL text field to the path of the saved PDF file
                    cvURL.setText(destinationFile.getAbsolutePath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public Application getData() {
        CRUDFreelance crud = new CRUDFreelance();

        Freelance found = new Freelance();

        int idFreelancee = Integer.parseInt(idInvisible.getText());
        found = crud.reaserchById(idFreelancee);
        int idFreelancerr = Integer.parseInt(idFreelancer.getText());
        int idBO = (int) found.getBO_id();
        String FNN = FN.getText();
        String LNN = LN.getText();
        String EmailFreelancer = EmailF.getText();
        String url = cvURL.getText();

        Application a = new Application(idFreelancee, idFreelancerr, idBO, FNN, LNN, EmailFreelancer, url
        );
        return a;
    }

}
