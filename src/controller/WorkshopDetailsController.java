/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import java.net.URL;
import java.util.ResourceBundle;
import entity.Workshop;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author fatha
 */
public class WorkshopDetailsController implements Initializable {
    @FXML
    private Label workshopNameLabel;
    @FXML
    private Label workshopIDLabel;
    @FXML
    private Label workshopStartDateLabel;
    @FXML
    private Label workshopEndDateLabel;
    @FXML
    private Label workshopRegistrationDeadlineLabel;
    @FXML
    private Label workshopLocationLabel;
    @FXML
    private Label workshopMaxAttendeesLabel;
    @FXML
    private Label workshopAgendaLabel;
    @FXML
    private Button participate;
    @FXML
    private Label workshopDescriptionLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setWorkshopDetails(Workshop workshop) {
        workshopNameLabel.setText(workshop.getEvent_name());
        workshopIDLabel.setText(Integer.toString(workshop.getEvent_id()));
        workshopStartDateLabel.setText(workshop.getStart_date().toString());
        workshopEndDateLabel.setText(workshop.getEnd_date().toString());
        workshopRegistrationDeadlineLabel.setText(workshop.getRegistrationDeadline().toString());
        workshopLocationLabel.setText(workshop.getLocation());
        workshopMaxAttendeesLabel.setText(Integer.toString(workshop.getMax_attendees()));
        workshopAgendaLabel.setText(workshop.getAgenda());
        workshopDescriptionLabel.setText(workshop.getDescription());
    }

}