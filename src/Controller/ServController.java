/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import entities.EmailSender;
import java.awt.AWTException;
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
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javax.mail.MessagingException;
 
/**
 * FXML Controller class
 *
 * @author belhassan
 */
public class ServController implements Initializable {
      @FXML
    private Button pyflc;

        @FXML
    private Button eml;
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
   @FXML
    private Button scr;
   
    @FXML
    private Button wtsp;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
  
        
 wtsp.setOnAction(e -> {
    String phoneNumber = "+21656152776"; // Replace with the recipient's phone number (including the country code)
    String message = "Hello, world!"; // Replace with the message you want to send

    try {
        String encodedPhoneNumber = URLEncoder.encode(phoneNumber, StandardCharsets.UTF_8.toString());
        String encodedMessage = URLEncoder.encode(message, StandardCharsets.UTF_8.toString());
        String ul = "https://wa.me/" + encodedPhoneNumber + "?text=" + encodedMessage;
        Desktop.getDesktop().browse(new URI(ul));
    } catch (IOException | URISyntaxException ex) {
        ex.printStackTrace();
    }
});
        
  

           EmailSender emailSender = new EmailSender("InstalancePI@hotmail.com","belhassan@123");
         eml.setOnAction(event -> {
            try {
                emailSender.sendEmail("belha9457@gmail.com", "Test email", "This is a test email sent from JavaFX.");
                System.out.println("Email sent.");
            } catch (MessagingException e) {
                System.err.println("Error sending email: " + e.getMessage());
            }
        });
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
                    Desktop.getDesktop().browse(new URI("https://www.linkedin.com/feed/"));
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
