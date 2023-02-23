/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import work.enteties.Blog;
import work.services.CRUDBlog;




import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn.CellEditEvent;
/**
 * FXML Controller class
 *
 * @author zeinab
 */
public class Afficher_blogController implements Initializable {

    @FXML
    private ListView<Blog> afficherblog;
    @FXML
    private Button supprimer;
    @FXML
    private Button mod;
    
     static String title,body;
        static int id_blog;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ListView<Blog> list1= afficherblog;
        CRUDBlog inter = new CRUDBlog();
        List<Blog> list2 = inter.DisplayBlog();
        for (int i = 0; i < list2.size(); i++) {
            Blog A = list2.get(i);
            list1.getItems().add(A);

        } 
    }    

    @FXML
    private void supprimer_blog(ActionEvent event) {
        ListView<Blog> list1= afficherblog;
        CRUDBlog inter = new CRUDBlog();
        int selectedIndex = list1.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Blog A = list1.getSelectionModel().getSelectedItem();
            System.out.println(A.getid());
            inter.DeleteBlog(A.getid());
            list1.getItems().remove(selectedIndex);
               Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Blog supprimer avec succés!");
        alert.show();
        } else {
               Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner un blog à supprimer.!");
        alert.show();
        }
    }

    @FXML
    private void mod(ActionEvent event) {
        
             
           ListView<Blog> list = afficherblog;
        CRUDBlog inter = new CRUDBlog();
        int selectedIndex = list.getSelectionModel().getSelectedIndex();
        Blog A = list.getSelectionModel().getSelectedItem();
        id_blog=A.getid();
        title= A.gettitle();
        body=A.getbody();
       
        
         

        try {

            Parent page1
                    = FXMLLoader.load(getClass().getResource("/view/modifierblog.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            
       
        } catch (IOException ex) {
            Logger.getLogger(Location_blogController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    
}


    
