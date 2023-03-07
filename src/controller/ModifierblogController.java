/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TextField;
import work.enteties.Blog;
import work.services.CRUDBlog;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;




/**
 * FXML Controller class
 *
 * @author zeinab
 */
public class ModifierblogController implements Initializable {
     private ListView<Blog> afficherblog;

    @FXML
    private TextField fx_title;
    @FXML
    private TextField fx_body;
    @FXML
  
    private Button modifier_blog;
    @FXML
    private Button return1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fx_title.setText(Affiche_blogController.title);
        fx_body.setText(Affiche_blogController.body);
        
        
       
    }    

    @FXML
    private void modifier_blog(ActionEvent event) {
      


        CRUDBlog inter = new CRUDBlog();
        String title = fx_title.getText();        
        String body = fx_body.getText();
        Blog B = new Blog(Affiche_blogController.id_blog,title, body);
        inter.ModifyBlog(B);
        
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
 alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Blog modifier avec succés!");
        alert.show();
    }

    @FXML
    private void return1(ActionEvent event) {
         try {

            Parent page1
                    = FXMLLoader.load(getClass().getResource("/view/Affiche_blog.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Location_blogController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    }
    


   
