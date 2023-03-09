/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;

import entity.Hackathon;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author fatha
 */
public class HackathonDetailsController implements Initializable {
        @FXML
        private Label hackathonNameLabel;
        @FXML
        private Label hackathonIDLabel;
        @FXML
        private Label hackathonStartDateLabel;
        @FXML
        private Label hackathonEndDateLabel;
        @FXML
        private Label hackathonRegistrationDeadlineLabel;
        @FXML
        private Label hackathonLocationLabel;
        @FXML
        private Label hackathonMaxAttendeesLabel;
        @FXML
        private Label hackathonSubmissionDeadlineLabel;
        @FXML
        private Label hackathonPrizesLabel;
        @FXML
        private Label hackathonDescriptionLabel;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    public void setHackathonDetails(Hackathon hackathon) {
        hackathonNameLabel.setText(hackathon.getEvent_name());
        hackathonIDLabel.setText(Integer.toString(hackathon.getEvent_id()));
        hackathonStartDateLabel.setText(hackathon.getStart_date().toString());
        hackathonEndDateLabel.setText(hackathon.getEnd_date().toString());
        hackathonRegistrationDeadlineLabel.setText(hackathon.getRegistrationDeadline().toString());
        hackathonLocationLabel.setText(hackathon.getLocation());
        hackathonMaxAttendeesLabel.setText(Integer.toString(hackathon.getMax_attendees()));
        hackathonSubmissionDeadlineLabel.setText(hackathon.getSubmissionDeadline().toString());
        hackathonPrizesLabel.setText(hackathon.getPrizes());
        hackathonDescriptionLabel.setText(hackathon.getDescription());
    }
}
