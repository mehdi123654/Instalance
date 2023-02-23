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
public class Ajouter_blogController implements Initializable {

    @FXML
    private TextField fx_title;
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
        
        String title = fx_title.getText();
        String body = fx_body.getText();
       if(title.length()==0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("erreur donner title");
        alert.show();
        }else if(title.length()>20){
            Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("title ne depasse pas 20 caractere");
        alert.show();
        
        
        
        
        }else if (body.length()==0){
             Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("erreur donner un body");
        alert.show();
        
        }else {
        Blog B = new Blog(title, body);
        CRUDBlog crud = new CRUDBlog();
        crud.AddBlog(B);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Blog insérée avec succés!");
        alert.show();
    }
    }

}
 
