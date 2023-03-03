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

/**
 * FXML Controller class
 *
 * @author fatha
 */
public class UpdateWorkshopController implements Initializable {

    @FXML
    private TextField workshopNameTextField2;

    @FXML
    private TextField IdTextArea2;

    @FXML
    private TextArea descriptionTextArea2;

    @FXML
    private DatePicker startDatePicker2;

    @FXML
    private DatePicker endDatePicker2;

    @FXML
    private TextField locationTextField2;

    @FXML
    private TextField maxAttendeesTextField2;

    @FXML
    private DatePicker registrationDeadlinePicker2;

    @FXML
    private TextArea agendaTextArea2;

    @FXML
    private Button saveButton2;

    @FXML
    private Button cancelButton2;

    private Stage dialogStage2;
    private Workshop workshop;
    private boolean isSaveClicked2 = false;
    private BOmanagementController boManagementController;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Set initial values
        startDatePicker2.setValue(LocalDate.now());
        endDatePicker2.setValue(LocalDate.now());
        registrationDeadlinePicker2.setValue(LocalDate.now());
    }

    public boolean isSaveClicked2() {
        return isSaveClicked2;
    }
    /*public UpdateWorkshopController(BOmanagementController boManagementController) {
        this.boManagementController = boManagementController;
    }*/
    public void setBOManagementController(BOmanagementController boManagementController) {
        this.boManagementController = boManagementController;
    }

    public void saveWorkshop() {
        // Check if input is valid
        if (!isInputValid()) {
            return;
        }

        // Get data from input fields
        int event_id = Integer.parseInt(IdTextArea2.getText());
        String eventName = workshopNameTextField2.getText();
        String description = descriptionTextArea2.getText();
        LocalDate startDate = startDatePicker2.getValue();
        LocalDate endDate = endDatePicker2.getValue();
        String location = locationTextField2.getText();
        int maxAttendees = Integer.parseInt(maxAttendeesTextField2.getText());
        LocalDate registrationDeadline = registrationDeadlinePicker2.getValue();
        String agenda = agendaTextArea2.getText();
        WorkshopService workshopService = new WorkshopService();

        Workshop workshop2 = workshopService.getById(event_id);
        // Create a new Workshop object
        workshop2 = new Workshop(eventName, description, Date.valueOf(startDate),
                Date.valueOf(endDate), location, maxAttendees, Date.valueOf(registrationDeadline),
                agenda);

        // Add the workshop to the database
        // Get the existing workshop from the database
        // Workshop workshop2 = workshopService.getById(event_id);

        // Update the properties of the existing workshop
        workshop2.setEvent_name(eventName);
        workshop2.setDescription(description);
        workshop2.setStart_date(Date.valueOf(startDate));
        workshop2.setEnd_date(Date.valueOf(endDate));
        workshop2.setLocation(location);
        workshop2.setMax_attendees(maxAttendees);
        workshop2.setRegistrationDeadline(Date.valueOf(registrationDeadline));
        workshop2.setAgenda(agenda);

        // Update the workshop in the database
        workshopService.updateWorkshop(workshop2);
        // workshopService.updateWorkshop(workshop2);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Workshop modified");
        alert.setContentText("The workshop has been modified in the database.");
        alert.showAndWait();

        // Close the window
        // closeWindow();
    }

    /**
     * Closes the window.
     */
    public void closeWindow() {
        Stage stage = (Stage) cancelButton2.getScene().getWindow();
        stage.close();
    }

    public boolean isInputValid() {
        String errorMessage = "";

        if (workshopNameTextField2.getText() == null || workshopNameTextField2.getText().length() == 0) {
            errorMessage += "Event name is required.\n";
        }

        if (startDatePicker2.getValue() != null && endDatePicker2.getValue() != null && endDatePicker2.getValue().isBefore(startDatePicker2.getValue())) {
            errorMessage += "End date must be after the start date.\n";
        } 

        if (descriptionTextArea2.getText() == null || descriptionTextArea2.getText().length() == 0) {
            errorMessage += "Description is required.\n";
        }

        if (startDatePicker2.getValue() == null) {
            errorMessage += "Start date is required.\n";
        }

        if (endDatePicker2.getValue() == null) {
            errorMessage += "End date is required.\n";
        }

        if (locationTextField2.getText() == null || locationTextField2.getText().length() == 0) {
            errorMessage += "Location is required.\n";
        }

        if (maxAttendeesTextField2.getText() == null || maxAttendeesTextField2.getText().length() == 0) {
            errorMessage += "Max attendees is required.\n";
        } else {
            try {
                Integer.parseInt(maxAttendeesTextField2.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Max attendees must be an integer.\n";
            }
        }

        if (registrationDeadlinePicker2.getValue() == null) {
            errorMessage += "Registration deadline is required.\n";
        }

        if (agendaTextArea2.getText() == null || agendaTextArea2.getText().length() == 0) {
            errorMessage += "Agenda is required.\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage2);
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
        Stage stage = (Stage) cancelButton2.getScene().getWindow();

        // Set the new scene on the stage
        stage.setScene(scene);

        // Show the stage
        stage.show();
    }

    public void setWorkshop(Workshop workshop) {
        this.workshop = workshop;
        // Populate the input fields with the workshop data
        workshopNameTextField2.setText(workshop.getEvent_name());
        descriptionTextArea2.setText(workshop.getDescription());
        startDatePicker2.setValue(workshop.getStart_date().toLocalDate());
        endDatePicker2.setValue(workshop.getEnd_date().toLocalDate());
        locationTextField2.setText(workshop.getLocation());
        maxAttendeesTextField2.setText(String.valueOf(workshop.getMax_attendees()));
        registrationDeadlinePicker2.setValue(workshop.getRegistrationDeadline().toLocalDate());
        agendaTextArea2.setText(workshop.getAgenda());

        // Set other input fields with the corresponding workshop data
    }

    @FXML
    private void handleUpdateButtonAction() {
        // Update the workshop object with the new values from the UI controls
        WorkshopService workshopService = new WorkshopService();
        workshop.setEvent_name(workshopNameTextField2.getText());
        workshop.setDescription(descriptionTextArea2.getText());
        workshop.setEnd_date(Date.valueOf(startDatePicker2.getValue()));
        workshop.setEnd_date(Date.valueOf(endDatePicker2.getValue()));
        workshop.setLocation(locationTextField2.getText());
        workshop.setMax_attendees(Integer.parseInt(maxAttendeesTextField2.getText()));
        workshop.setRegistrationDeadline(Date.valueOf(registrationDeadlinePicker2.getValue()));
        workshop.setAgenda(agendaTextArea2.getText());
        workshopService.updateWorkshop(workshop);
        // Close the window
        Stage stage = (Stage) saveButton2.getScene().getWindow();
        stage.close();
        boManagementController.refresh();
    }

    @FXML
    private void handleCancelButtonAction() {
        Stage stage = (Stage) cancelButton2.getScene().getWindow();
        stage.close();
    }

}
