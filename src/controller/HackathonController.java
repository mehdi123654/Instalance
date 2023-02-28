/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Hackathon;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.time.LocalDate;
import java.sql.Date;
import java.sql.SQLException;
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
import services.HackathonService;
import utils.DataBaseConnection;

/**
 * FXML Controller class
 *
 * @author fatha
 */
public class HackathonController implements Initializable {

    @FXML
    private TextField eventNameTextField;

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private TextField locationTextField;

    @FXML
    private TextField maxAttendeesTextField;

    @FXML
    private DatePicker registrationDeadlinePicker;

    @FXML
    private DatePicker submissionDeadlinePicker;

    @FXML
    private TextArea prizesTextArea;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    private Stage dialogStage;
    private Hackathon hackathon;
    private boolean isSaveClicked = false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Set initial values
        startDatePicker.setValue(LocalDate.now());
        endDatePicker.setValue(LocalDate.now());
        registrationDeadlinePicker.setValue(LocalDate.now());
        submissionDeadlinePicker.setValue(LocalDate.now());
    }

    /*public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }*/

    public void setHackathon(Hackathon hackathon) {
        this.hackathon = hackathon;

        if (hackathon != null) {
            // Set text fields and date pickers to the values of the hackathon object
            eventNameTextField.setText(hackathon.getEvent_name());
            descriptionTextArea.setText(hackathon.getDescription());
            startDatePicker.setValue(hackathon.getStart_date().toLocalDate());
            endDatePicker.setValue(hackathon.getEnd_date().toLocalDate());
            locationTextField.setText(hackathon.getLocation());
            maxAttendeesTextField.setText(String.valueOf(hackathon.getMax_attendees()));
            registrationDeadlinePicker.setValue(hackathon.getRegistrationDeadline().toLocalDate());
            submissionDeadlinePicker.setValue(hackathon.getSubmissionDeadline().toLocalDate());
            prizesTextArea.setText(hackathon.getPrizes());
        }
    }

    public boolean isSaveClicked() {
        return isSaveClicked;
    }

    /*@FXML
    private void handleSave() {
        if (isInputValid()) {
            hackathon.setEvent_name(eventNameTextField.getText());
            hackathon.setDescription(descriptionTextArea.getText());
            hackathon.setStart_date(startDatePicker.getValue());
            hackathon.setEnd_date(endDatePicker.getValue());
            hackathon.setLocation(locationTextField.getText());
            hackathon.setMax_attendees(Integer.parseInt(maxAttendeesTextField.getText()));
            hackathon.setRegistrationDeadline(registrationDeadlinePicker.getValue());
            hackathon.setSubmissionDeadline(submissionDeadlinePicker.getValue());
            hackathon.setPrizes(prizesTextArea.getText());
            
            isSaveClicked = true;
            dialogStage.close();
        }
    }*/
    public void saveHackathon() {
        // Check if input is valid
        if (!isInputValid()) {
            return;
        }

        // Get data from input fields
        String eventName = eventNameTextField.getText();
        String description = descriptionTextArea.getText();
        LocalDate startDate = startDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();
        String location = locationTextField.getText();
        int maxAttendees = Integer.parseInt(maxAttendeesTextField.getText());
        LocalDate registrationDeadline = registrationDeadlinePicker.getValue();
        LocalDate submissionDeadline = submissionDeadlinePicker.getValue();
        String prizes = prizesTextArea.getText();

        // Create a new Hackathon object
        Hackathon hackathon = new Hackathon(eventName, description, Date.valueOf(startDate),
                Date.valueOf(endDate), location, maxAttendees, Date.valueOf(registrationDeadline),
                Date.valueOf(submissionDeadline), prizes);

        // Add the hackathon to the database
            HackathonService hackathonService = new HackathonService();
            hackathonService.addHackathon(hackathon);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Hackathon added");
            alert.setContentText("The hackathon has been added to the database.");
            alert.showAndWait();
        

        // Close the window
        //closeWindow();
    }

    /**
     * Closes the window.
     */
    public void closeWindow() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void handleSave() {
        if (isInputValid()) {
            hackathon.setEvent_name(eventNameTextField.getText());
            hackathon.setDescription(descriptionTextArea.getText());
            hackathon.setStart_date(java.sql.Date.valueOf(startDatePicker.getValue()));
            hackathon.setEnd_date(java.sql.Date.valueOf(endDatePicker.getValue()));
            hackathon.setLocation(locationTextField.getText());
            hackathon.setMax_attendees(Integer.parseInt(maxAttendeesTextField.getText()));
            hackathon.setRegistrationDeadline(java.sql.Date.valueOf(registrationDeadlinePicker.getValue()));
            hackathon.setSubmissionDeadline(java.sql.Date.valueOf(submissionDeadlinePicker.getValue()));
            hackathon.setPrizes(prizesTextArea.getText());
            isSaveClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    public void handleCancel() {
        dialogStage.close();
    }

    public boolean isInputValid() {
        String errorMessage = "";

        if (eventNameTextField.getText() == null || eventNameTextField.getText().length() == 0) {
            errorMessage += "Event name is required.\n";
        }

        if (descriptionTextArea.getText() == null || descriptionTextArea.getText().length() == 0) {
            errorMessage += "Description is required.\n";
        }

        if (startDatePicker.getValue() == null) {
            errorMessage += "Start date is required.\n";
        }

        if (endDatePicker.getValue() == null) {
            errorMessage += "End date is required.\n";
        }

        if (locationTextField.getText() == null || locationTextField.getText().length() == 0) {
            errorMessage += "Location is required.\n";
        }

        if (maxAttendeesTextField.getText() == null || maxAttendeesTextField.getText().length() == 0) {
            errorMessage += "Max attendees is required.\n";
        } else {
            try {
                Integer.parseInt(maxAttendeesTextField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Max attendees must be an integer.\n";
            }
        }

        if (registrationDeadlinePicker.getValue() == null) {
            errorMessage += "Registration deadline is required.\n";
        }

        if (submissionDeadlinePicker.getValue() == null) {
            errorMessage += "Submission deadline is required.\n";
        }

        if (prizesTextArea.getText() == null || prizesTextArea.getText().length() == 0) {
            errorMessage += "Prizes is required.\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
    @FXML
void cancel(ActionEvent event) throws IOException {
    // Load the FXML file of the UI you want to display
    Parent root = FXMLLoader.load(getClass().getResource("../view/BOmanagement.fxml"));

    // Create a new scene with the loaded FXML file as its root node
    Scene scene = new Scene(root);

    // Get the current stage (window) from the button's scene
    Stage stage = (Stage) cancelButton.getScene().getWindow();

    // Set the new scene on the stage
    stage.setScene(scene);

    // Show the stage
    stage.show();
}

}
