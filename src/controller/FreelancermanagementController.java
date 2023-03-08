/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import entities.Hackathon;
import entities.Workshop;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.HackathonService;
import services.WorkshopService;


/**
 * FXML Controller class
 *
 * @author fatha
 */
public class FreelancermanagementController implements Initializable {

    @FXML
    private TableColumn<Workshop, Integer> hackathonEvent_idColumn;

    @FXML
    private TableColumn<Hackathon, String> hackathonDescriptionColumn;

    @FXML
    private TableColumn<Hackathon, Date> hackathonEndDateColumn;

    @FXML
    private TableColumn<Hackathon, String> hackathonLocationColumn;

    @FXML
    private TableColumn<Hackathon, Integer> hackathonMaxAttendeesColumn;

    @FXML
    private TableColumn<Hackathon, String> hackathonNameColumn;

    @FXML
    private TableColumn<Hackathon, String> hackathonPrizesColumn;

    @FXML
    private TableColumn<Hackathon, Date> hackathonRegistrationDeadlineColumn;

    @FXML
    private TableColumn<Hackathon, Date> hackathonStartDateColumn;

    @FXML
    private TableColumn<Hackathon, Date> hackathonSubmissionDeadlineColumn;

    @FXML
    private TableView<Hackathon> hackathonsTable;

    @FXML
    private TableColumn<Workshop, String> workshopAgendaColumn;

    @FXML
    private TableColumn<Workshop, String> workshopDescriptionColumn;

    @FXML
    private TableColumn<Workshop, Date> workshopEndDateColumn;

    @FXML
    private TableColumn<Workshop, String> workshopLocationColumn;

    @FXML
    private TableColumn<Workshop, Integer> workshopMaxAttendeesColumn;

    @FXML
    private TableColumn<Workshop, Integer> workshopEvent_idColumn;

    @FXML
    private TableColumn<Workshop, String> workshopNameColumn;

    @FXML
    private TableColumn<Workshop, Date> workshopRegistrationDeadlineColumn;

    @FXML
    private TableColumn<Workshop, Date> workshopStartDateColumn;

    @FXML
    private TableView<Workshop> workshopsTable;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        WorkshopService workshopService = new WorkshopService();
        HackathonService hackathonService = new HackathonService();
        ObservableList<Workshop> WorkshopList = workshopService.getAllWorkshops();
        ObservableList<Hackathon> HackathonList = hackathonService.getAllHackathons();
        workshopsTable.setItems(WorkshopList);
        hackathonsTable.setItems(HackathonList);
        workshopNameColumn.setSortable(true);
        workshopNameColumn.setComparator(
                (workshop1, workshop2) -> workshop1.compareTo(workshop2));

        workshopsTable.setOnSort(event -> {
            ObservableList<Workshop> WorkshopSortedList = workshopsTable.getItems().sorted((w1, w2) -> {
                String workshopName1 = w1.getEvent_name();
                String workshopName2 = w2.getEvent_name();
                return workshopName1.compareToIgnoreCase(workshopName2);
            });
            workshopsTable.setItems(WorkshopSortedList);
        });
        hackathonNameColumn.setSortable(true);
        hackathonNameColumn.setComparator(
                (hackathon1, hackathon2) -> hackathon1.compareTo(hackathon2));

        hackathonsTable.setOnSort(event -> {
            ObservableList<Hackathon> HackathonSortedList = hackathonsTable.getItems().sorted((h1, h2) -> {
                String hackathonName1 = h1.getEvent_name();
                String hackathonName2 = h2.getEvent_name();
                return hackathonName1.compareToIgnoreCase(hackathonName2);
            });
            hackathonsTable.setItems(HackathonSortedList);

            
        });
        loadData();
    }
    public void loadData() {
        /*
         * workshopsTable.getSortOrder().add(workshopNameColumn);
         * hackathonsTable.getSortOrder().add(hackathonNameColumn);
         * 
         * workshopNameColumn.setSortable(true);
         * hackathonNameColumn.setSortable(true);
         */

        hackathonEvent_idColumn.setCellValueFactory(new PropertyValueFactory<>("event_id"));
        hackathonDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        hackathonEndDateColumn.setCellValueFactory(new PropertyValueFactory<>("end_date"));
        hackathonLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        hackathonMaxAttendeesColumn.setCellValueFactory(new PropertyValueFactory<>("max_attendees"));
        hackathonNameColumn.setCellValueFactory(new PropertyValueFactory<>("event_name"));
        hackathonPrizesColumn.setCellValueFactory(new PropertyValueFactory<>("prizes"));
        hackathonRegistrationDeadlineColumn.setCellValueFactory(new PropertyValueFactory<>("registrationDeadline"));
        hackathonStartDateColumn.setCellValueFactory(new PropertyValueFactory<>("start_date"));
        hackathonSubmissionDeadlineColumn.setCellValueFactory(new PropertyValueFactory<>("submissionDeadline"));
        workshopEvent_idColumn.setCellValueFactory(new PropertyValueFactory<>("event_id"));
        workshopAgendaColumn.setCellValueFactory(new PropertyValueFactory<>("agenda"));
        workshopDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        workshopEndDateColumn.setCellValueFactory(new PropertyValueFactory<>("end_date"));
        workshopLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        workshopMaxAttendeesColumn.setCellValueFactory(new PropertyValueFactory<>("max_attendees"));
        workshopNameColumn.setCellValueFactory(new PropertyValueFactory<>("event_name"));
        workshopRegistrationDeadlineColumn.setCellValueFactory(new PropertyValueFactory<>("registrationDeadline"));
        workshopStartDateColumn.setCellValueFactory(new PropertyValueFactory<>("start_date"));

    }
    @FXML
void handleWorkshopDoubleClicked(MouseEvent event) {
    if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
        Workshop selectedWorkshop = workshopsTable.getSelectionModel().getSelectedItem();
        if (selectedWorkshop != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/WorkshopDetails.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(loader.load()));
                WorkshopDetailsController controller = loader.getController();
                controller.setWorkshopDetails(selectedWorkshop);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
    
}
