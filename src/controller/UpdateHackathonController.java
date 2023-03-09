package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import entity.*;
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
import service.*;

/**
 * FXML Controller class
 *
 * @author fatha
 */
public class UpdateHackathonController implements Initializable {

    @FXML
    private TextField hackathonNameTextField3;

    @FXML
    private TextField IdTextArea3;

    @FXML
    private TextArea descriptionTextArea3;

    @FXML
    private DatePicker startDatePicker3;

    @FXML
    private DatePicker endDatePicker3;

    @FXML
    private TextField locationTextField3;

    @FXML
    private TextField maxAttendeesTextField3;

    @FXML
    private DatePicker registrationDeadlinePicker3;

    @FXML
    private DatePicker submissionDeadlinePicker3;

    @FXML
    private TextArea prizesTextArea3;

    @FXML
    private Button saveButton3;

    @FXML
    private Button cancelButton3;

    private Stage dialogStage3;
    private Hackathon hackathon;
    private boolean isSaveClicked3 = false;
    private BOmanagementController boManagementController;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Set initial values
        startDatePicker3.setValue(LocalDate.now());
        endDatePicker3.setValue(LocalDate.now());
        registrationDeadlinePicker3.setValue(LocalDate.now());
        submissionDeadlinePicker3.setValue(LocalDate.now());
    }

    public boolean isSaveClicked3() {
        return isSaveClicked3;
    }
    /*public UpdateHackathonController(BOmanagementController boManagementController) {
        this.boManagementController = boManagementController;
    }*/
    public void setBOManagementController(BOmanagementController boManagementController) {
        this.boManagementController = boManagementController;
    }

    public void saveHackathon() {
        // Check if input is valid
        if (!isInputValid()) {
            return;
        }

        // Get data from input fields
        int event_id = Integer.parseInt(IdTextArea3.getText());
        String eventName = hackathonNameTextField3.getText();
        String description = descriptionTextArea3.getText();
        LocalDate startDate = startDatePicker3.getValue();
        LocalDate endDate = endDatePicker3.getValue();
        String location = locationTextField3.getText();
        int maxAttendees = Integer.parseInt(maxAttendeesTextField3.getText());
        LocalDate registrationDeadline = registrationDeadlinePicker3.getValue();
        LocalDate submissionDeadline = submissionDeadlinePicker3.getValue();
        String prizes = prizesTextArea3.getText();
        HackathonService hackathonService = new HackathonService();

        Hackathon hackathon3 = hackathonService.getHackathonById(event_id);
        // Create a new Hackathon object
        hackathon3 = new Hackathon(eventName, description, Date.valueOf(startDate),
                Date.valueOf(endDate), location, maxAttendees, Date.valueOf(registrationDeadline),Date.valueOf(submissionDeadline),
                prizes);

        // Add the hackathon to the database
        // Get the existing hackathon from the database
        // Hackathon hackathon3 = hackathonService.getById(event_id);

        // Update the properties of the existing hackathon
        hackathon3.setEvent_name(eventName);
        hackathon3.setDescription(description);
        hackathon3.setStart_date(Date.valueOf(startDate));
        hackathon3.setEnd_date(Date.valueOf(endDate));
        hackathon3.setLocation(location);
        hackathon3.setMax_attendees(maxAttendees);
        hackathon3.setRegistrationDeadline(Date.valueOf(registrationDeadline));
        hackathon3.setRegistrationDeadline(Date.valueOf(submissionDeadline));
       
        hackathon3.setPrizes(prizes);

        // Update the hackathon in the database
        hackathonService.updateHackathon(hackathon3);
        // hackathonService.updateHackathon(hackathon3);

        

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Hackathon modified");
        alert.setContentText("The hackathon has been modified in the database.");
        alert.showAndWait();

        // Close the window
        // closeWindow();
    }

    /**
     * Closes the window.
     */
    public void closeWindow() {
        Stage stage = (Stage) cancelButton3.getScene().getWindow();
        stage.close();
    }

    public boolean isInputValid() {
        String errorMessage = "";

        if (hackathonNameTextField3.getText() == null || hackathonNameTextField3.getText().length() == 0) {
            errorMessage += "Event name is required.\n";
        }

        if (descriptionTextArea3.getText() == null || descriptionTextArea3.getText().length() == 0) {
            errorMessage += "Description is required.\n";
        }

        if (startDatePicker3.getValue() != null && endDatePicker3.getValue() != null && endDatePicker3.getValue().isBefore(startDatePicker3.getValue())) {
            errorMessage += "End date must be after the start date.\n";
        } 

        if (startDatePicker3.getValue() != null && registrationDeadlinePicker3.getValue() != null && startDatePicker3.getValue().isBefore(registrationDeadlinePicker3.getValue())) {
            errorMessage += "start date must be after the registration deadline.\n";
        }
        
        if (startDatePicker3.getValue() != null && submissionDeadlinePicker3.getValue() != null && submissionDeadlinePicker3.getValue().isBefore(startDatePicker3.getValue())) {
            errorMessage += "submission deadline must be after the start date.\n";
        } 

        if (startDatePicker3.getValue() == null) {
            errorMessage += "Start date is required.\n";
        }

        if (endDatePicker3.getValue() == null) {
            errorMessage += "End date is required.\n";
        }

        if (locationTextField3.getText() == null || locationTextField3.getText().length() == 0) {
            errorMessage += "Location is required.\n";
        }

        if (maxAttendeesTextField3.getText() == null || maxAttendeesTextField3.getText().length() == 0) {
            errorMessage += "Max attendees is required.\n";
        } else {
            try {
                Integer.parseInt(maxAttendeesTextField3.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Max attendees must be an integer.\n";
            }
        }

        if (registrationDeadlinePicker3.getValue() == null) {
            errorMessage += "Registration deadline is required.\n";
        }
        if (submissionDeadlinePicker3.getValue() == null) {
            errorMessage += "Submission deadline is required.\n";
        }

        if (prizesTextArea3.getText() == null || prizesTextArea3.getText().length() == 0) {
            errorMessage += "Prizes is required.\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage3);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

    @FXML
    void cancellHackathon(ActionEvent event) throws IOException {
        // Load the FXML file of the UI you want to display
        Parent root = FXMLLoader.load(getClass().getResource("/view/BOmanagement.fxml"));

        // Create a new scene with the loaded FXML file as its root node
        Scene scene = new Scene(root);

        // Get the current stage (window) from the button's scene
        Stage stage = (Stage) cancelButton3.getScene().getWindow();

        // Set the new scene on the stage
        stage.setScene(scene);

        // Show the stage
        stage.show();
    }

    public void setHackathon(Hackathon hackathon) {
        this.hackathon = hackathon;
        // Populate the input fields with the hackathon data
        hackathonNameTextField3.setText(hackathon.getEvent_name());
        descriptionTextArea3.setText(hackathon.getDescription());
        startDatePicker3.setValue(hackathon.getStart_date().toLocalDate());
        endDatePicker3.setValue(hackathon.getEnd_date().toLocalDate());
        locationTextField3.setText(hackathon.getLocation());
        maxAttendeesTextField3.setText(String.valueOf(hackathon.getMax_attendees()));
        registrationDeadlinePicker3.setValue(hackathon.getRegistrationDeadline().toLocalDate());
        submissionDeadlinePicker3.setValue(hackathon.getSubmissionDeadline().toLocalDate());
        prizesTextArea3.setText(hackathon.getPrizes());

        // Set other input fields with the corresponding hackathon data
    }

    @FXML
    private void handleUpdateButtonActionHackathon() {
        // Update the hackathon object with the new values from the UI controls
        HackathonService hackathonService = new HackathonService();
        hackathon.setEvent_name(hackathonNameTextField3.getText());
        hackathon.setDescription(descriptionTextArea3.getText());
        hackathon.setEnd_date(Date.valueOf(startDatePicker3.getValue()));
        hackathon.setEnd_date(Date.valueOf(endDatePicker3.getValue()));
        hackathon.setLocation(locationTextField3.getText());
        hackathon.setMax_attendees(Integer.parseInt(maxAttendeesTextField3.getText()));
        hackathon.setRegistrationDeadline(Date.valueOf(registrationDeadlinePicker3.getValue()));
        hackathon.setPrizes(prizesTextArea3.getText());
        hackathonService.updateHackathon(hackathon);
        // Close the window
        Stage stage = (Stage) saveButton3.getScene().getWindow();
        stage.close();
        boManagementController.refresh();
    }

    @FXML
    private void handleCancelButtonActionHackathon() {
        Stage stage = (Stage) cancelButton3.getScene().getWindow();
        stage.close();
    }

}