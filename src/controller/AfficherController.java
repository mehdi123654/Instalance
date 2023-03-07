/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import work.enteties.ToggleSwitch;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
/**
 * FXML Controller class
 *
 * @author zeinab
 */
public class AfficherController implements Initializable {

    @FXML
    private TextField titleA;
    @FXML
    private TextArea bodyA;
    @FXML
    private Button return1;
    @FXML
    private AnchorPane paneMain;
    @FXML
    private Button pdf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        titleA.setEditable(false);
       bodyA.setEditable(false);
       titleA.setText(Affiche_blogController.title);
        bodyA.setText(Affiche_blogController.body);
               
                
ToggleSwitch button = new ToggleSwitch();
SimpleBooleanProperty isOn = button.switchOnProperty();
isOn.addListener((observable,oldValue,newValue) -> {
    if(newValue){
        button.getScene().getRoot().getStylesheets().add(getClass().getResource("/css/Styles.css").toString());
        System.out.println("Adding the css");
    }
        else{
         button.getScene().getRoot().getStylesheets().remove(getClass().getResource("/css/Styles.css").toString());
        System.out.println("Removing the css");
    }
});

paneMain.getChildren().add(button);
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

    @FXML
    private void pdf(ActionEvent event) throws DocumentException, FileNotFoundException {
        
        
        Document document = new Document();
FileChooser f = new FileChooser () ;
f.getExtensionFilters() .add(new FileChooser.ExtensionFilter("PDF","*.pdf"));
File a = f.showSaveDialog (new Stage());
       if(a!=null)
       {
  PdfWriter.getInstance(document, new FileOutputStream(a.getAbsoluteFile()));
document.open();

document.add(new Paragraph("Title :"+titleA.getText()));
document.add(new Paragraph ("Body:"+bodyA.getText()));
document.close();
Alert al = new Alert (AlertType.CONFIRMATION);
al.setHeaderText ("PDF Enregistered");
al.show();
       }
       else
       {
       Alert al = new Alert (AlertType.WARNING);
al.setHeaderText ("NON ENREGISTRED!!");
al.show();
       }
      
    }
    
}
