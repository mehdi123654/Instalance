/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * FXML Controller class
 *
 * @author belhassan
 */
public class ServController implements Initializable {

    
     @FXML
    private Button mainp;
    @FXML
    private ImageView img;

    @FXML
    private Label nmlb;

    @FXML
    private Label dsclb;

    @FXML
    private Label pric;
 
  
    @FXML
    private WebView webi;

      @FXML
    private Button mps;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
webi.getEngine().loadContent("<div id=\"smart-button-container\">\n" +
"      <div style=\"text-align: center;\">\n" +
"        <div id=\"paypal-button-container\"></div>\n" +
"      </div>\n" +
"    </div>\n" +
"  <script src=\"https://www.paypal.com/sdk/js?client-id=sb&enable-funding=venmo&currency=USD\" data-sdk-integration-source=\"button-factory\"></script>\n" +
"  <script>\n" +
"    function initPayPalButton() {\n" +
"      paypal.Buttons({\n" +
"        style: {\n" +
"          shape: 'rect',\n" +
"          color: 'gold',\n" +
"          layout: 'vertical',\n" +
"          label: 'paypal',\n" +
"          \n" +
"        },\n" +
"\n" +
"        createOrder: function(data, actions) {\n" +
"          return actions.order.create({\n" +
"            purchase_units: [{\"amount\":{\"currency_code\":\"USD\",\"value\":1}}]\n" +
"          });\n" +
"        },\n" +
"\n" +
"        onApprove: function(data, actions) {\n" +
"          return actions.order.capture().then(function(orderData) {\n" +
"            \n" +
"            // Full available details\n" +
"            console.log('Capture result', orderData, JSON.stringify(orderData, null, 2));\n" +
"\n" +
"            // Show a success message within this page, e.g.\n" +
"            const element = document.getElementById('paypal-button-container');\n" +
"            element.innerHTML = '';\n" +
"            element.innerHTML = '<h3>Thank you for your payment!</h3>';\n" +
"\n" +
"            // Or go to another URL:  actions.redirect('thank_you.html');\n" +
"            \n" +
"          });\n" +
"        },\n" +
"\n" +
"        onError: function(err) {\n" +
"          console.log(err);\n" +
"        }\n" +
"      }).render('#paypal-button-container');\n" +
"    }\n" +
"    initPayPalButton();\n" +
"  </script>");

 mps.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Desktop.getDesktop().browse(new URI("https://www.linkedin.com/in/your_profile_page"));
                } catch (IOException | URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        });
    

          mainp.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/Show.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ShowController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        // TODO
    }    
    public void displayName(String price, String desc, String name, String photo) throws FileNotFoundException {
              String o = photo.replace("@", "\\");
                FileInputStream input = new FileInputStream(o);
                Image image = new Image(input);
                       
                img.setImage(image);
                
        pric.setText(price);
        dsclb.setText(desc);
        nmlb.setText(name);
        
    }
}
