/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import entities.Service;
import java.awt.Desktop;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.S;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author belhassan
 */
public class ShowController implements Initializable {

    private ListData listdat;
   
    @FXML
    private AnchorPane AP;

    @FXML
    private StackPane SP;
private Stage stage;
    private Scene scene;
    private Parent root ;
      private ListData listdata;
      
       @FXML
    private TextField srf;

    @FXML
        private Button srch;
    
    @FXML
    private WebView wb;
        @FXML
    private Button mp;
        
        
    @FXML
    private Button ads;
  
   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
       mp.setOnAction((ActionEvent e) -> {
    String rl = "https://www.google.com/maps/place/esprit+ecole+d'ing%C3%A9nieurs/@36.899229,10.1874636,17z/data=!3m1!4b1!4m6!3m5!1s0x12e2cb75abbb1733:0x557a99cdf6c13b7b!8m2!3d36.899229!4d10.1896523!16s%2Fg%2F11dybgg6rl";
    try {
        Desktop.getDesktop().browse(new URI(rl));
    } catch (IOException | URISyntaxException ex) {
        ex.printStackTrace();
    }
});
        
        ads.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/AddService.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AddServiceController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
       
  
wb.getEngine().load("https://www.youtube.com/embed/ggHACGb0mtU");

        int i = -420, y = -150;
 listdata = new ListData();
        int a = 1000;
        for (Service s1 : listdata.getServices()){
            if (i > 0) {
                AP.setPrefWidth(a += 300);
            }
            try {
                String ol = s1.getFile().replace("@", "\\");
                FileInputStream input = new FileInputStream(ol);
                Image image = new Image(input);
                ImageView imageView = new ImageView(image);
                Button b = new Button(s1.getName());
                b.setTranslateX(i);
                b.setTranslateY(50);
                imageView.setFitWidth(250); // set the desired width
                imageView.setFitHeight(200); // set the desired height
                imageView.setImage(image);

                imageView.setTranslateX(i);
                imageView.setTranslateY(-40);
                Label label = new Label(s1.getName());
                label.setTranslateX(i);
                label.setTranslateY(y);
                i += 300;

                SP.getChildren().add(imageView);
                SP.getChildren().add(b);

                 b.setOnAction(
                        even -> {
                            try {

                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Serv.fxml"));
                                root = loader.load();
                                ServController Controller = loader.getController();
                                Controller.displayName( String.valueOf(s1.getPrix()), s1.getDesc(), s1.getName(),s1.getFile());
                                stage = (Stage) ((Node) even.getSource()).getScene().getWindow();
                                scene = new Scene(root);
                                stage.setScene(scene);
                                stage.show();
                            } catch (IOException ex) {
                                Logger.getLogger(DisplayController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });
            } catch (FileNotFoundException ex) {
                Logger.getLogger(DisplayController.class.getName()).log(Level.SEVERE, null, ex);
        }

}   
    }}
    
