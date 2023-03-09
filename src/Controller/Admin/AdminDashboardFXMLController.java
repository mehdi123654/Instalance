/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Admin;

import entity.User;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import service.UserService;

/**
 * FXML Controller class
 *
 * @author Louay
 */
public class AdminDashboardFXMLController implements Initializable {

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
    private TextField tfSearch;
    @FXML
    private TableView<User> tvUsers;
    @FXML
    private TableColumn<User, String> tcUsername;
    @FXML
    private TableColumn<User, String> tcEmail;
    @FXML
    private TableColumn<User, String> tcRole;
    @FXML
    private RadioButton rbAll;
    @FXML
    private RadioButton rbAdmins;
    @FXML
    private RadioButton rbFreelancers;
    @FXML
    private RadioButton rbBOwner;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnBan;
    @FXML
    private TextField tfUsername;
    @FXML
    private TextField tfEmail;
    @FXML
    private PasswordField tfPassword;
    @FXML
    private Button btnAdd;
    
    UserService us = new UserService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
        tfPassword.clear();
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
        u = (User) tvUsers.getSelectionModel().getSelectedItem();
        tfUsername.setText(u.getUsername());
        tfEmail.setText(u.getEmail());
        //tfpassword.setText(u.getPassword());
    }

    private void updateUser(ActionEvent event) throws SQLException {
        
        User u;
        u = (User) tvUsers.getSelectionModel().getSelectedItem();
        
        System.out.println(u);
        String username = tfUsername.getText();
        String email = tfEmail.getText();
        String password = tfPassword.getText();        

        u.setUsername(username);
        u.setEmail(email);
        u.setPassword(password);       
        
        if (us.updateUser(u)) {
            AlertWindow("Updating " + username, username + " ************ Updated Sucessfully *******************", Alert.AlertType.INFORMATION);
        } else {
            AlertWindow("Updating " + username, "****************** Update Failed ************************", Alert.AlertType.ERROR);
        }
        init();
    }
    @FXML
    private void handleClicks(ActionEvent event) {
    }

    @FXML
    private void logout(ActionEvent event) {
    }

    @FXML
    private void searchUsers(ActionEvent event) {
    }

    @FXML
    private void showAll(ActionEvent event) {
    }

    @FXML
    private void sortAdmins(ActionEvent event) {
        ObservableList<User> users = us.filterRole("Admin");
        tcUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        tcEmail.setCellValueFactory(new PropertyValueFactory<>("email"));        
        tcRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        tvUsers.setItems(users);
    }

    @FXML
    private void sortFreelancers(ActionEvent event) {
        ObservableList<User> users = us.filterRole("Freelancer");
        tcUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        tcEmail.setCellValueFactory(new PropertyValueFactory<>("email"));        
        tcRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        tvUsers.setItems(users);
    }

    @FXML
    private void sortBowners(ActionEvent event) {
        ObservableList<User> users = us.filterRole("BOwner");
        tcUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        tcEmail.setCellValueFactory(new PropertyValueFactory<>("email"));        
        tcRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        tvUsers.setItems(users);
    }

    @FXML
    private void deleteUser(ActionEvent event) {
        
        try {
            User u = (User) tvUsers.getSelectionModel().getSelectedItem();
            if (us.deleteUser(u)) {
                AlertWindow("Deleting " + u.getRole(), u.getRole() + " ************** Successfully deleted ****************", Alert.AlertType.INFORMATION);
            } else {
                AlertWindow("Deleting " + u.getRole(), "***************** Failed Deleting ", Alert.AlertType.ERROR);
            }
            init();
        } catch (SQLException ex) {
            Logger.getLogger(AdminDashboardFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void updateTable() throws SQLException {
        System.out.println("Updating table ********************************************************");
        rbAll.setSelected(true);
        ObservableList<User> users = us.showAllUsers();
        tcUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        tcEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tcRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        tvUsers.setItems(users);
    }

    @FXML
    private void banUser(ActionEvent event) {
    }

    @FXML
    private void addAdmin(ActionEvent event) {
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
    
}
