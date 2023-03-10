/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Louay
 */
public class UserFXMain extends Application {
    
    @Override
   public void start(Stage primaryStage) throws Exception {
       try {
       Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("views/AllUsers/SignupFXML.fxml"));
       primaryStage.setScene(new Scene(root));
       primaryStage.show();
    } catch (Exception e){
        e.printStackTrace();   
    }
   }
   
   
    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */

    public static void main(String[] args) throws SQLException, IOException {
       launch(args);
    }
    
}
