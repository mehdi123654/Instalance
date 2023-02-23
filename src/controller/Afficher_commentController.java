/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.Afficher_blogController.id_blog;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import work.enteties.Blog;
import work.enteties.Comment;
import work.services.CRUDBlog;
import work.services.CRUDComment;

/**
 * FXML Controller class
 *
 * @author zeinab
 */
public class Afficher_commentController implements Initializable {

  
  
     static String body;
        //static int id_blog,
                static int id_comment;
    @FXML
    private ListView<Comment> affichercomment;
    @FXML
    private Button supprimer;
    @FXML
    private Button mod;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ListView<Comment> list1= affichercomment;
        CRUDComment inter = new CRUDComment();
        List<Comment> list2 = inter.DisplayComment();
        for (int i = 0; i < list2.size(); i++) {
            Comment A = list2.get(i);
            list1.getItems().add(A);

        } 
    }    

    @FXML
    private void supprimer_comment(ActionEvent event) {
         ListView<Comment> list1= affichercomment;
        CRUDComment inter = new CRUDComment();
        int selectedIndex = list1.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Comment A = list1.getSelectionModel().getSelectedItem();
            System.out.println(A.getid());
            inter.DeleteComment(A.getid());
            list1.getItems().remove(selectedIndex);
               Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Comment supprimer avec succés!");
        alert.show();
        } else {
               Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner un Comment à supprimer.!");
        alert.show();
        }
    }
    

    @FXML
    private void mod(ActionEvent event) {
    
           ListView<Comment> list = affichercomment;
        CRUDComment inter = new CRUDComment();
        int selectedIndex = list.getSelectionModel().getSelectedIndex();
        Comment A = list.getSelectionModel().getSelectedItem();
        //id_blog=A.getId_blog();
        id_comment= A.getid();
        body=A.getbody();
       
        
         

        try {

            Parent page1
                    = FXMLLoader.load(getClass().getResource("/view/modifiercomment.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            
       
        } catch (IOException ex) {
            Logger.getLogger(Location_blogController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    
}
