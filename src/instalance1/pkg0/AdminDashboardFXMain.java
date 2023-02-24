/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instalance1.pkg0;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Louay
 */
public class AdminDashboardFXMain extends Application {
    
     @Override
   public void start(Stage primaryStage) throws Exception {
       try {
       Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("instalance1/pkg0/views/Admin/AdminDashboard.fxml"));
       primaryStage.setScene(new Scene(root));
       primaryStage.show();
    } catch (Exception e){
        e.printStackTrace();   
    }
   }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
