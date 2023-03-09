/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import service.CRUDApplication;
import service.EmailSender;
import entity.Application;

import javax.mail.MessagingException;

/**
 * FXML Controller class
 *
 * @author emnaa
 */
public class ListApplicantsController implements Initializable {

    @FXML
    private TextField EmailLabel;

    @FXML
    private TextField FNLabel;

    @FXML
    private TextField LNLabel;

    @FXML
    private ListView<Application> applicantsListView;

    @FXML
    private Label cvLinkLabel;

    @FXML
    private TextField dateLabel;

    @FXML
    private ImageView exit;

    @FXML
    private TextField idFreelancerLabel;

    @FXML
    private Label sumLabel;

    private int idReceived;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        exit.setOnMouseClicked(event -> {
            //saker scene jdida
            Stage stage = (Stage) exit.getScene().getWindow();
            stage.close();
        });
        // Create an event handler that updates the labels with the item information
        applicantsListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Application>() {
            @Override
            public void changed(ObservableValue<? extends Application> observable, Application oldValue, Application newValue) {
                if (newValue != null) {
                    FNLabel.setText(newValue.getFN());
                    LNLabel.setText(newValue.getLN());
                    idFreelancerLabel.setText(String.valueOf(newValue.getIdFreelancer()));
                    EmailLabel.setText(newValue.getEmailF());
                    cvLinkLabel.setText(newValue.getUrlCV());
                    // Using DateTimeFormatter
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String dateString = dtf.format(newValue.getAddDate().toLocalDateTime());
                    dateLabel.setText(dateString);
                } else {
                    FNLabel.setText("");
                    LNLabel.setText("");
                    idFreelancerLabel.setText("");
                    EmailLabel.setText("");
                    cvLinkLabel.setText("");
                    dateLabel.setText("");
                }
            }
        });

        cvLinkLabel.setOnMouseClicked(event -> {
            String filePath = cvLinkLabel.getText();
            if (cvLinkLabel.getText() != null) {
                try {
                    File file = new File(filePath);
                    if (file.exists()) {
                        Desktop.getDesktop().open(file); // open the file in the default PDF reader
                    } else {
                        System.out.println("File does not exist.");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void setId(int id) {
        this.idReceived = id;
        System.out.println("set id called now!!");
        System.out.println("id state in the set method: " + this.idReceived);
        CRUDApplication crudApp = new CRUDApplication();

        // If the received ID is not 0, retrieve the data and populate the ListView
        if (this.idReceived != 0) {
            CRUDApplication crudA = new CRUDApplication();
            ObservableList<Application> listApplicants = crudA.displayById(this.idReceived);

            // Set the items and cell factory of the ListView
            applicantsListView.setItems(listApplicants);

            // set the CSS style of the ListView
            applicantsListView.setStyle("-fx-background-color: gray;");
            applicantsListView.setCellFactory(param -> new ListCell<Application>() {
                private final Label firstNameLabel = new Label();
                private final Label lastNameLabel = new Label();
                private final Button confirmButton = new Button("Confirm");
                private final Button notifyButton = new Button("Notify");

                {
                    // set the background color of the confirm button to green
                    confirmButton.setStyle("-fx-background-color: #f4e006;");

                    // set the background color of the notify button to blue
                    notifyButton.setStyle("-fx-background-color: #f4e006;");

                    //style : bold and font
                    firstNameLabel.setStyle("-fx-font-family: 'Microsoft Tai Le'; -fx-font-weight: bold;");
                    lastNameLabel.setStyle("-fx-font-family: 'Microsoft Tai Le'; -fx-font-weight: bold;");

                    firstNameLabel.setStyle("-fx-font-size: 15;");
                    lastNameLabel.setStyle("-fx-font-size: 15;");

                    confirmButton.setOnAction(event -> {
                        Application item = getItem();
                        crudApp.setConfirmed(item.getIdFreelancer(), true);
                        item.setConfirmed(true);
                        updateItem(item, isEmpty());
                    });
                    notifyButton.setOnAction(event -> {
                        Application item = getItem();
                        //SEND EMAIL
                        if (item.isConf()) {
                            // Send acceptance email
                            try {
                                EmailSender.sendEmail(item.getEmailF(), "Application Confirmation Email", "Hello dear "+item.getFN()+" "+item.getLN()+", \n"+"We are very happy to inform you that with reference to your Freelance application, "
                                        + "We are offering you this email as a confirmation to start your work "
                                        + "with our team. We will be contacting you soon for more information. \n"
                                +"Congratulations! \n"
                                        + "Kind regards,");
                            } catch (MessagingException e) {
                                e.printStackTrace();
                            }
                        } else {
                            // Send rejection email
                            try {
                                EmailSender.sendEmail(item.getEmailF(), "Freelance Offre Application", "Hi \n"+item.getFN()+" "+item.getLN()+", \n"
                                        +"We appreciate your interest in Brainnest and the time you've invested in applying for Freelance offer. \n"
                                        + "Regrettably, this time we've decided to move forward with another candidate. \n"
                                + "We do believe you would be a valuable addition to our team, and we'll keep you in mind for other positions.\n"
                                        + "Good luck! \n"
                                        + "All the best,"
);
                            } catch (MessagingException e) {
                                e.printStackTrace();
                            }
                        }
                        crudApp.setNotified(item.getIdFreelancer(), true);
                        item.setNotified(true);
                        updateItem(item, isEmpty());
                    });

                }

                @Override
                protected void updateItem(Application item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty || item == null) {
                        setGraphic(null);
                    } else {
                        firstNameLabel.setText("Applicant:    " + item.getFN() + "  " + item.getLN());
                        confirmButton.setText(item.isConf() ? "Confirmed" : "Confirm");
                        notifyButton.setText(item.isNotif() ? "Notified" : "Notify");

                        // create an HBox and add the buttons to it
                        HBox buttonBox = new HBox(confirmButton, notifyButton);
                        buttonBox.setSpacing(257);

                        // set the background color of the VBox based on the index of the item
                        int index = getIndex();
                        String color = index % 2 == 0 ? "#E4ADBB" : "#FFFFFF"; // alternate between two colors
                        String style = String.format("-fx-background-color: %s;", color);
                        VBox vbox = new VBox(firstNameLabel, lastNameLabel, buttonBox);
                        vbox.setStyle(style);
                        setGraphic(vbox);
                    }
                }
            });
        }
    }

    public int getId() {
        return idReceived;
    }

}
