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
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import entity.Blog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.CRUDBlog;

/**
 * FXML Controller class
 *
 * @author zeinab
 */
public class AjouterController implements Initializable {
  private static final List<String> PROHIBITED_WORDS = Arrays.asList("arse",
"arsehole",
"ass",
"asshole",
"bastard",
"bitch",
"bloody",
"bullshit",
"cock",
"cocksucker",
"crap",
"cunt",
"damn",
"damn it",
"dick",
"dickhead",
"goddamn",
"holy shit",
"fuck",
"nigga",
"nigra ",
"piss",
"prick",
"pussy",
"shit",
"slut",
"whore",
"wanker", "naughty", "ugly","hitler");
     
    @FXML
    private TextArea fx_body;
    @FXML
    private TextField fx_title;
    @FXML
    private Button ajouter;
    @FXML
    private Button return1;

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
             String filteredtitle = filterProfanity(title);
                          String filteredbody = filterProfanity(body);

        Blog B = new Blog(filteredtitle,filteredbody);
        CRUDBlog crud = new CRUDBlog();
        crud.AddBlog(B);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Blog insérée avec succés!");
        alert.show();
    }
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
            Logger.getLogger(AjouterController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
    private String filterProfanity(String inputText) {
        List<String> words = Arrays.asList(inputText.split(" "));
        List<String> filteredWords = words.stream()
                .map(word -> PROHIBITED_WORDS.contains(word.toLowerCase()) ? "&@$!*" : word)
                .collect(Collectors.toList());
        return String.join(" ", filteredWords);
    }
    
}
