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
import javafx.scene.control.TextArea;
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
public class ModifiercommentController implements Initializable {
     private ListView<Comment> afficherblog;

    @FXML
    private TextArea fx_body;
    @FXML
    private Button modifier_blog;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//fx_id_blog.setText(Integer.toString(Afficher_blogController.id_blog));
        fx_body.setText(Affiche_blogController.body); 
    
    }    

    @FXML
    private void modifier_blog(ActionEvent event) {
            CRUDComment inter = new CRUDComment();
        //int id_blog = Integer.parseInt(fx_id_blog.getText());        
        String body = fx_body.getText();
        Comment B = new Comment(Afficher_commentController.id_comment,body);
        inter.ModifyComment(B);
        
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
 alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Comment modifier avec succés!");
        alert.show();
    }
    }
    
