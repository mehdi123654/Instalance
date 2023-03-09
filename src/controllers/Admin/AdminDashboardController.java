/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.Admin;

import Instalance.UserManagement.entities.User;
import services.UserService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Louay
 */
public class AdminDashboardController implements Initializable {

    private ComboBox<String> cbSort;
    private TextField tfUsername;
    private TextField tfEmail;
    private PasswordField tfpassword;
    private ComboBox<String> cbrole;
    private TableView<User> tableuser;
    private TableColumn<User, String> colUsername;
    private TableColumn<User, String> colEmail;
    private TableColumn<User, String> colPass;
    private TableColumn<User, String> colRole;
    private RadioButton rbAll;
    private ComboBox<String> cbrechpar;
    UserService us = new UserService();
    @FXML
    private Button btnOverview;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnCustomers;
    @FXML
    private Button btnMenus;
    @FXML
    private Button btnPackages;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnSignout;
    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;
    @FXML
    private VBox pnItems;
    @FXML
    private ListView<?> lvUser;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cbrole.setItems(FXCollections.observableArrayList("Admin", "Freelancer", "Bussines Owner"));
        cbSort.setItems(FXCollections.observableArrayList("username", "email"));
        cbrechpar.setItems(FXCollections.observableArrayList("username", "email"));
        try {
            updateTable();
        } catch (SQLException ex) {
            Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    
    public void init() throws SQLException {
        updateTable();
        tfUsername.clear();
        tfEmail.clear();
        tfpassword.clear();
        cbrole.setValue(null);
    }
    
    public void AlertWindow(String title, String contenu, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contenu);
        alert.showAndWait();
    }
    
    private void fillForm(Event event){
        
        User u;
        u = tableuser.getSelectionModel().getSelectedItem();
        tfUsername.setText(u.getUsername());
        tfEmail.setText(u.getEmail());
        //tfpassword.setText(u.getPassword());
    }

    private void updateUser(ActionEvent event) throws SQLException {
        
        User u;
        u = tableuser.getSelectionModel().getSelectedItem();
        
        System.out.println(u);
        String username = tfUsername.getText();
        String email = tfEmail.getText();
        String password = tfpassword.getText();        
        String Urole = cbrole.getSelectionModel().getSelectedItem();

        u.setUsername(username);
        u.setEmail(email);
        u.setPassword(password);       
        u.setRole(Urole);
        
        if (us.updateUser(u)) {
            AlertWindow("Updating " + Urole, Urole + " ************ Updated Sucessfully *******************", Alert.AlertType.INFORMATION);
        } else {
            AlertWindow("Updating " + Urole, "****************** Update Failed ************************", Alert.AlertType.ERROR);
        }
        init();
    }

    private void deleteUser(ActionEvent event) throws SQLException {
        User u = tableuser.getSelectionModel().getSelectedItem();
        if (us.deleteUser(u)) {
            AlertWindow("Deleting " + u.getRole(), u.getRole() + " ************** Successfully deleted ****************", Alert.AlertType.INFORMATION);
        } else {
            AlertWindow("Deleting " + u.getRole(), "***************** Failed Deleting ", Alert.AlertType.ERROR);
        }
        init();
    }

    private void sortUsers(ActionEvent event) {
        
        String sort = (String) cbSort.getSelectionModel().getSelectedItem();
        ObservableList<User> users;
        users = us.sortUsers(sort);
        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPass.setCellValueFactory(new PropertyValueFactory<>("password"));
     
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        tableuser.setItems(users);
    }

    private void getAdmins(ActionEvent event) {
        ObservableList<User> users = us.filterRole("Admin");
        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPass.setCellValueFactory(new PropertyValueFactory<>("password"));
        
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        tableuser.setItems(users);
    }

    private void getClients(ActionEvent event) {
        ObservableList<User> users = us.filterRole("Freelancer");
        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPass.setCellValueFactory(new PropertyValueFactory<>("password"));
        
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        tableuser.setItems(users);
    }

    private void updateTable() throws SQLException {
        System.out.println("Updating table ********************************************************");
        rbAll.setSelected(true);
        ObservableList<User> users = us.showAllUsers();
        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPass.setCellValueFactory(new PropertyValueFactory<>("password"));
     
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        tableuser.setItems(users);
    }

    private void handleReturnMenuAdmin(ActionEvent event) {
        GotoFXML("AdminChoiceFxml", "Instalance", event);
    }

    
    private void GotoFXML(String vue, String title, Event aEvent) {
        try {
            Event event;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(vue + ".fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = (Stage) ((Node) aEvent.getSource()).getScene().getWindow();
            stage.setTitle(title);
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleClicks(ActionEvent event) {
    }
    
}
