package controller;

import entities.Hackathon;
import entities.Workshop;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.EventService;
import services.HackathonService;
import services.WorkshopService;
public class BOmanagementController implements Initializable {

    @FXML
    private Button addHackathonButton;

    @FXML
    private Button addWorkshopButton;

    @FXML
    private Button deleteButton;
    @FXML
    private Button addWorkshop;

    @FXML
    private Button addHackathon;

    @FXML
    private Button updateButton;

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
    private TableColumn<Workshop, String> workshopEditColumn;

    @FXML
    private TableColumn<Workshop, Date> workshopRegistrationDeadlineColumn;

    @FXML
    private TableColumn<Workshop, Date> workshopStartDateColumn;

    @FXML
    private TableView<Workshop> workshopsTable;

    @FXML
    private TextField searchField;

    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        WorkshopService workshopService = new WorkshopService();
        HackathonService hackathonService = new HackathonService();
        ObservableList <Workshop> WorkshopList = workshopService.getAllWorkshops();
        ObservableList <Hackathon> HackathonList = hackathonService.getAllHackathons();
        //initial Fieldteredlist
        
        FilteredList<Workshop> filteredWorkshops = new FilteredList<>(WorkshopList, b ->true);
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
            ObservableList<Hackathon> HackathonSortedList = hackathonsTable.getItems().sorted((w1, w2) -> {
                String hackathonName1 = w1.getEvent_name();
                String hackathonName2 = w2.getEvent_name();
                return hackathonName1.compareToIgnoreCase(hackathonName2);
            });
            hackathonsTable.setItems(HackathonSortedList);
        });
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredWorkshops.setPredicate(workshop -> {
                // If the search field is empty, show all workshops
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
    
                // Convert the search query to lowercase for case-insensitive search
                String lowerCaseFilter = newValue.toLowerCase();
    
                // Check if the workshop's name or description contains the search query
                if (workshop.getEvent_name().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (workshop.getDescription().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
    
                // If the search query doesn't match the workshop's name or description, don't show it
                return false;
            });
            workshopsTable.setItems(filteredWorkshops);
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
   
    /*
     * public void loadData() {
     * 
     * // add cell of button edit
     * Callback<TableColumn<Workshop, String>, TableCell<Workshop, String>>
     * cellFoctory = (
     * TableColumn<Workshop, String> param) -> {
     * // make cell containing buttons
     * final TableCell<Workshop, String> cell = new TableCell<Workshop, String>() {
     * 
     * @Override
     * public void updateItem(String item, boolean empty) {
     * super.updateItem(item, empty);
     * // that cell created only on non-empty rows
     * if (empty) {
     * setGraphic(null);
     * setText(null);
     * 
     * } else {
     * FontAwesomeIconView deleteIcon = new
     * FontAwesomeIconView(FontAwesomeIcon.TRASH);
     * FontAwesomeIconView editIcon = new
     * FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);
     * ((Node) deleteIcon).setOnMouseClicked((MouseEvent event) -> {
     * 
     * Delete();
     * });
     * ((Node) updateIcon).setOnMouseClicked((MouseEvent event) -> {
     * workshopsTable.getSelectionModel().getSelectedItem();
     * FXMLLoader loader = new FXMLLoader();
     * loader.setLocation(getClass().getResource("../view/Update.fxml"));
     * try {
     * loader.load();
     * } catch (IOException ex) {
     * Logger.getLogger(BOmanagementController.class.getName()).log(Level.SEVERE,
     * null, ex);
     * }
     * 
     * });
     * }
     * }
     * };
     * return cell;
     * };
     * workshopEditColumn.setCellFactory(cellFoctory);
     * workshopsTable.setItems(WorkshopList);
     * }
     */

    public void Delete() {
        
        EventService eventService = new EventService();
        try {
            // Get the selected row from the table
            int selectedIndex = workshopsTable.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                // Extract the ID of the event from the selected row
                int eventId = workshopEvent_idColumn.getCellData(selectedIndex);
                // Call the deleteEvent() method with the ID of the event
                eventService.deleteEvent(eventId);
                // Remove the selected row from the table
                workshopsTable.getItems().remove(selectedIndex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BOmanagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Deletee() {
        EventService eventService = new EventService();
        try {
            // Get the selected row from the table
            int selectedIndex = hackathonsTable.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                // Extract the ID of the event from the selected row
                int eventId = hackathonEvent_idColumn.getCellData(selectedIndex);
                // Call the deleteEvent() method with the ID of the event
                eventService.deleteEvent(eventId);
                // Remove the selected row from the table
                hackathonsTable.getItems().remove(selectedIndex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BOmanagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void addWorkshop(ActionEvent event) throws IOException {
        // Load the FXML file of the UI you want to display
        Parent root = FXMLLoader.load(getClass().getResource("../view/Workshop.fxml"));

        // Create a new dialog with the loaded FXML file as its content
        // Dialog<Void> dialog = new Dialog<>();
        // dialog.getDialogPane().setContent(root);

        // Create a new scene with the loaded FXML file as its root node
        Scene scene = new Scene(root);

        // Get the current stage (window) from the button's scene
        Stage stage = (Stage) addWorkshopButton.getScene().getWindow();

        // Set the owner of the dialog to the current stage
        // dialog.initOwner(stage);

        // Add a handler for the close request
        // dialog.setOnCloseRequest(e -> dialog.close());

        // Show the dialog and wait for it to close
        // dialog.showAndWait();

        // Set the new scene on the stage
        stage.setScene(scene);

        // Show the stage
        stage.show();
    }

    @FXML
    void addHackathon(ActionEvent event) throws IOException {
        // Load the FXML file of the UI you want to display
        Parent root = FXMLLoader.load(getClass().getResource("../view/Hackathon.fxml"));

        // Create a new scene with the loaded FXML file as its root node
        Scene scene = new Scene(root);

        // Get the current stage (window) from the button's scene
        Stage stage = (Stage) addHackathonButton.getScene().getWindow();

        // Set the new scene on the stage
        stage.setScene(scene);

        // Show the stage
        stage.show();
    }

    @FXML
    void Update(ActionEvent event) throws IOException {
        // Get the selected workshop from the workshops table
        Workshop selectedWorkshop = workshopsTable.getSelectionModel().getSelectedItem();

        if (selectedWorkshop != null) {
            // Create a new FXMLLoader to load the update workshop UI
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/UpdateWorkshop.fxml"));
            Parent root = loader.load();

            // Get the controller for the update workshop UI
            UpdateWorkshopController updateWorkshopController = loader.getController();

            // Pass the selected workshop data to the update workshop controller
            updateWorkshopController.setWorkshop(selectedWorkshop);
            updateWorkshopController.setBOManagementController(this);
            // Create a new scene and display it
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } else {
            // Display an error message if no workshop is selected
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("No Workshop Selected");
            alert.setContentText("Please select a workshop to update.");
            alert.showAndWait();
        }
    }

    public void refresh() {
        WorkshopService workshopService = new WorkshopService();
        ObservableList<Workshop> workshopList = workshopService.getAllWorkshops();
        workshopsTable.setItems(workshopList);
    }

}
