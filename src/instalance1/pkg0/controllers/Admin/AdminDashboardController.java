/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instalance1.pkg0.controllers.Admin;

import instalance1.pkg0.entities.User;
import instalance1.pkg0.services.UserServices;
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
import javafx.scene.control.MenuBar;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Louay
 */
public class AdminDashboardController implements Initializable {

    @FXML
    private TextField tfSearch;
    @FXML
    private ComboBox<String> cbSort;
    @FXML
    private TextField tfUsername;
    @FXML
    private TextField tfEmail;
    @FXML
    private PasswordField tfpassword;
    @FXML
    private ComboBox<String> cbrole;
    @FXML
    private Button btncreate;
    @FXML
    private TableView<User> tableuser;
    @FXML
    private TableColumn<User, String> colUsername;
    @FXML
    private TableColumn<User, String> colEmail;
    @FXML
    private TableColumn<User, String> colPass;
    @FXML
    private TableColumn<User, String> colRole;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDel;
    @FXML
    private Button btnSort;
    @FXML
    private RadioButton rbAdmin;
    @FXML
    private ToggleGroup role;
    @FXML
    private RadioButton rbClient;
    @FXML
    private RadioButton rbAll;
    @FXML
    private ComboBox<String> cbrechpar;
    @FXML
    private MenuBar menu;
    @FXML
    private Button btnReturnMenu;
    @FXML
    private Button pdf;
    @FXML
    private Button stat;
    
    UserServices us = new UserServices();

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

    @FXML
    private void updateUser(ActionEvent event) throws SQLException {
        
        User u;
        u = tableuser.getSelectionModel().getSelectedItem();

        String username = tfUsername.getText();
        String email = tfEmail.getText();
        String password = tfpassword.getText();
       
        
        String Urole;
        Urole = cbrole.getSelectionModel().getSelectedItem();

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

    @FXML
    private void deleteUser(ActionEvent event) throws SQLException {
        User u = tableuser.getSelectionModel().getSelectedItem();
        if (us.deleteUser(u)) {
            AlertWindow("Deleting " + u.getRole(), u.getRole() + " ************** Successfully deleted ****************", Alert.AlertType.INFORMATION);
        } else {
            AlertWindow("Deleting " + u.getRole(), "***************** Failed Deleting ", Alert.AlertType.ERROR);
        }
        init();
    }

    @FXML
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

    @FXML
    private void getAdmins(ActionEvent event) {
        ObservableList<User> users = us.filterRole("Admin");
        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPass.setCellValueFactory(new PropertyValueFactory<>("password"));
        
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        tableuser.setItems(users);
    }

    @FXML
    private void getClients(ActionEvent event) {
        ObservableList<User> users = us.filterRole("Freelancer");
        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPass.setCellValueFactory(new PropertyValueFactory<>("password"));
        
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        tableuser.setItems(users);
    }

    @FXML
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

    @FXML
    private void handleReturnMenuAdmin(ActionEvent event) {
        GotoFXML("AdminChoiceFxml", "Instalance", event);
    }

    @FXML
    private void Pdf(ActionEvent event) {
    }

    @FXML
    private void OnClickedPrint(ActionEvent event) {
    }

    @FXML
    private void OnClickedStatistics(ActionEvent event) {
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
