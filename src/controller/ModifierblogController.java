/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private TextField fx_likee;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fx_title.setText(Afficher_blogController.title);
        fx_body.setText(Afficher_blogController.body);
    }    

    @FXML
    private void modifier_comment(ActionEvent event) {
        CRUDBlog inter = new CRUDBlog();
        String title = fx_title.getText();        
        String body = fx_body.getText();
        Blog B = new Blog(Afficher_blogController.id_blog,title, body);
        inter.ModifyBlog(B);
        
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
 alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Blog modifier avec succés!");
        alert.show();
    }
    }
    


   
