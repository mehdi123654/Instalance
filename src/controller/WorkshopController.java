/*package controller;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import entities.Workshop;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import services.WorkshopService;

public class WorkshopController implements Initializable {

    @FXML
    private TextField workshopNameTextField;

    @FXML
    private TextArea descriptionTextArea1;

    @FXML
    private DatePicker startDatePicker1;

    @FXML
    private DatePicker endDatePicker1;

    @FXML
    private TextField locationTextField1;

    @FXML
    private TextField maxAttendeesTextField1;

    @FXML
    private DatePicker registrationDeadlinePicker1;

    @FXML
    private TextArea agendaTextArea1;

    @FXML
    private Button saveButton1;

    @FXML
    private Button cancelButton1;

    private WorkshopService workshopService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.workshopService = new WorkshopService();
    }

    @FXML
    void onCancelButtonClick() {
        // Close the window or clear the form
    }

    @FXML
    void onSaveButtonClick() {
        // Get the values from the form fields
        String workshopName = workshopNameTextField.getText();
        String description = descriptionTextArea1.getText();
        LocalDate startDate = startDatePicker1.getValue();
        LocalDate endDate = endDatePicker1.getValue();
        String location = locationTextField1.getText();
        int maxAttendees = Integer.parseInt(maxAttendeesTextField1.getText());
        LocalDate registrationDeadline = registrationDeadlinePicker1.getValue();
        String agenda = agendaTextArea1.getText();

        // Create a new Workshop object with the form values
        Workshop workshop = new Workshop(
                description,
                workshopName,
                Date.valueOf(startDate),
                Date.valueOf(endDate),
                location,
                maxAttendees,
                Date.valueOf(registrationDeadline),
                agenda
        );

        try {
            // Save the Workshop object to the database
            workshopService.addWorkshop(workshop);

            // Close the window or clear the form
        } catch (SQLException e) {
            // Handle the exception
        }
    }

}
 */
 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.mail.MessagingException;

import javafx.fxml.Initializable;
import java.time.LocalDate;
import java.sql.Date;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.*;
import mail.Mailing;
/**
 * FXML Controller class
 *
 * @author fatha
 */
public class WorkshopController implements Initializable {

    @FXML
    private TextField workshopNameTextField1;

    @FXML
    private TextArea descriptionTextArea1;

    @FXML
    private DatePicker startDatePicker1;

    @FXML
    private DatePicker endDatePicker1;

    @FXML
    private TextField locationTextField1;

    @FXML
    private TextField maxAttendeesTextField1;

    @FXML
    private DatePicker registrationDeadlinePicker1;

    @FXML
    private TextArea agendaTextArea1;

    @FXML
    private Button saveButton1;

    @FXML
    private Button cancelButton1;

    private Stage dialogStage1;
    private boolean isSaveClicked1 = false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Set initial values
        startDatePicker1.setValue(LocalDate.now());
        endDatePicker1.setValue(LocalDate.now());
        registrationDeadlinePicker1.setValue(LocalDate.now());
    }

    public boolean isSaveClicked1() {
        return isSaveClicked1;
    }

    public void saveWorkshop() throws MessagingException {
        // Check if input is valid
        if (!isInputValid()) {
            return;
        }

        // Get data from input fields
        String eventName = workshopNameTextField1.getText();
        String description = descriptionTextArea1.getText();
        LocalDate startDate = startDatePicker1.getValue();
        LocalDate endDate = endDatePicker1.getValue();
        String location = locationTextField1.getText();
        int maxAttendees = Integer.parseInt(maxAttendeesTextField1.getText());
        LocalDate registrationDeadline = registrationDeadlinePicker1.getValue();
        String agenda = agendaTextArea1.getText();

        // Create a new Workshop object
        Workshop workshop = new Workshop(eventName, description, Date.valueOf(startDate),
                Date.valueOf(endDate), location, maxAttendees, Date.valueOf(registrationDeadline),
                agenda);

        // Add the workshop to the database
        WorkshopService workshopService = new WorkshopService();
        workshopService.addWorkshop(workshop);
        String subject = "Don't miss Our New Workshop!!!!!";
        String body = "A new workshop has been added:\n\n"
            + "Event Name: " + workshop.getEvent_name() + "\n"
            + "Description: " + workshop.getDescription() + "\n"
            + "Start Date: " + workshop.getStart_date() + "\n"
            + "End Date: " + workshop.getEnd_date() + "\n"
            + "Location: " + workshop.getLocation() + "\n"
            + "Max Attendees: " + workshop.getMax_attendees() + "\n"
            + "Registration Deadline: " + workshop.getRegistrationDeadline() + "\n"
            + "Agenda: " + workshop.getAgenda();
        Mailing.sendEmail("mehdi.fathallah69@gmail.com",subject,body);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Workshop added");
        alert.setContentText("The workshop has been added to the database.");
        alert.showAndWait();

        // Close the window
        //closeWindow();
    }

    /**
     * Closes the window.
     */
    public void closeWindow() {
        Stage stage = (Stage) cancelButton1.getScene().getWindow();
        stage.close();
    }

    public boolean isInputValid() {
        String errorMessage = "";

        if (workshopNameTextField1.getText() == null || workshopNameTextField1.getText().length() == 0) {
            errorMessage += "Event name is required.\n";
        }

        if (descriptionTextArea1.getText() == null || descriptionTextArea1.getText().length() == 0) {
            errorMessage += "Description is required.\n";
        }

        if (startDatePicker1.getValue() == null) {
            errorMessage += "Start date is required.\n";
        }

        if (endDatePicker1.getValue() == null) {
            errorMessage += "End date is required.\n";
        }

        if (locationTextField1.getText() == null || locationTextField1.getText().length() == 0) {
            errorMessage += "Location is required.\n";
        }
      
        if (maxAttendeesTextField1.getText() == null || maxAttendeesTextField1.getText().length() == 0) {
            errorMessage += "Max attendees is required.\n";
        } else {
            try {
                Integer.parseInt(maxAttendeesTextField1.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Max attendees must be an integer.\n";
            }
        }

        if (registrationDeadlinePicker1.getValue() == null) {
            errorMessage += "Registration deadline is required.\n";
        }

        if (agendaTextArea1.getText() == null || agendaTextArea1.getText().length() == 0) {
            errorMessage += "Agenda is required.\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage1);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
    @FXML
    void cancell(ActionEvent event) throws IOException {
    // Load the FXML file of the UI you want to display
    Parent root = FXMLLoader.load(getClass().getResource("../view/BOmanagement.fxml"));

    // Create a new scene with the loaded FXML file as its root node
    Scene scene = new Scene(root);

    // Get the current stage (window) from the button's scene
    Stage stage = (Stage) cancelButton1.getScene().getWindow();

    // Set the new scene on the stage
    stage.setScene(scene);

    // Show the stage
    stage.show();
}

}
