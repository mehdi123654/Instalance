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
import javafx.scene.control.TextField;
import work.enteties.Blog;
import work.enteties.Comment;
import work.services.CRUDBlog;
import work.services.CRUDComment;

/**
 * FXML Controller class
 *
 * @author zeinab
 */
public class Ajouter_commentController implements Initializable {

    @FXML
    private TextField fx_id_blog;
    @FXML
    private TextField fx_body;
    @FXML
    private Button ajouter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouter(ActionEvent event) {
           int id_blog = Integer.parseInt(fx_id_blog.getText());
        String body = fx_body.getText();
       if(body.length()==0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("erreur donner body");
        alert.show();
        }else if(id_blog<0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("erreur donner id blog");
        alert.show();
        
        
        }  
    else {
        Comment B = new Comment(body,id_blog);
        CRUDComment crud = new CRUDComment();
        crud.AddComment(B);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Comment insérée avec succés!");
        alert.show();
    }
    }
    }
    
