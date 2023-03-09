/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import entity.Packag;
import entity.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

import dao.PackagDao;
import dao.ServiceDao;

/**
 * FXML Controller class
 *
 * @author belhassan
 */
public class AddServiceController implements Initializable {
    
    ObservableList<String> choices = FXCollections.observableArrayList(
    "Web",
    "Design",
    "Photography",
    "Video editing",
    "E-Commerce",
    "Others"  
);

 FileChooser fileChooser = new FileChooser();
    @FXML
    private Button butt;
       @FXML
    private TextField nm;
    @FXML
    private TextArea dsr;
@FXML
    private ChoiceBox<String> chbx;
@FXML
    private Label lbl;
    @FXML
    private Button dsp;

    @FXML
    private TextField pfild;
    
    @FXML
    private Button insrtimg;

    @FXML
    private ImageView imgfild;
    
        @FXML
    private Label imgurl;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        chbx.setItems(choices);
      
chbx.setOnAction(event -> {
    String selectedChoice = chbx.getValue();

});
          UnaryOperator<Change> numericFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*")) {
                return change;
            }
            return null;
        };
        TextFormatter<String> f = new TextFormatter<>(numericFilter);
        pfild.setTextFormatter(f);
        
        
         butt.setOnAction(event -> {
    
             
             if (dsr.getText().isEmpty()) {
                // display an error message if the field is empty
                lbl.setText("Be Careful Description is empty!");
            } else {
                // display the name entered by the user
                lbl.setText("Hello, " + nm.getText() + "!");
            }
Service s = new Service(nm.getText(),dsr.getText(),Integer.parseInt(pfild.getText()),imgurl.getText(),chbx.getValue());
       ServiceDao sdao = ServiceDao.getInstance();
         sdao.insert(s);
            
            
            PackagDao dao = PackagDao.getInstance();
              
        Packag p1=new Packag(sdao.getSId(),"basic",15);
         Packag p2=new Packag(sdao.getSId(),"standard",30);
          Packag p3=new Packag(sdao.getSId(),"premium",50);
           
             dao.insert(p1);
              dao.insert(p2);
               dao.insert(p3);
               
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Service added with succes!");
        alert.show();
        nm.setText("");
        dsr.setText("");
        pfild.setText("");
  
        });
         
         
         insrtimg.setOnAction(
                event -> {
                      Notifications.create()
              .title("Image Notifications")
              .text("Insert Your Image!")
              .showWarning();
            

                    fileChooser.setTitle("Open File");
                    File file = fileChooser.showOpenDialog(null); // you could pass a stage reference here if you wanted.

                    if (file != null) {
                      
                        //creating the image object
                        InputStream stream;
                        try {
                            stream = new FileInputStream(file.getAbsolutePath());
                            Image image = new Image(stream);
                            String p= file.getAbsolutePath().replace("\\","@");
                            imgurl.setText(p);
                            imgfild.setImage(image);
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(AddServiceController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
            }
);
         
         
         dsp.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/Display.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(DisplayController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }    
    
}
