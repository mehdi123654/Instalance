/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
public class Ajouter_commentController implements Initializable {

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
    private TextField fx_id_blog;
    @FXML
    private TextArea fx_body;
    @FXML
    private Button ajouter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          fx_id_blog.setEditable(false);
       fx_id_blog.setText(Integer.toString(Affiche_blogController.id_blog));
       fx_id_blog.setVisible(false);
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
       
       } else {
            String filteredText = filterProfanity(body);
        Comment B = new Comment(filteredText,id_blog);
        CRUDComment crud = new CRUDComment();
        crud.AddComment(B);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Comment insérée avec succés!");
        alert.show();
    }
    }

    @FXML
    private void return1(ActionEvent event) {
        try {

            Parent page1
                    = FXMLLoader.load(getClass().getResource("/view/Afficher.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Ajouter_commentController.class.getName()).log(Level.SEVERE, null, ex);
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
    
