/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Louay
 */
public class AdminDashboardFXMain extends Application {
    
     @Override
   public void start(Stage primaryStage) throws Exception {
       try {
       Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("views/Admin/AdminDashboardFXML.fxml"));
       primaryStage.setScene(new Scene(root));
       primaryStage.show();
    } catch (IOException e){
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
