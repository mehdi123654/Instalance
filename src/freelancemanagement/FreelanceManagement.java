/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freelancemanagement;

import com.crossify.controller.FreelanceManagementController;
import com.crossify.entities.Freelance;
import com.crossify.services.CRUDFreelance;
import com.crossify.utils.MyConnection;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author emnaa
 */
public class FreelanceManagement extends Application {

    /**
     * @param args the command line arguments
     */
    double x, y = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {        
                
       Parent root = FXMLLoader.load(getClass().getResource("/com/crossify/view/FreelanceManagement.fxml"));
    
        primaryStage.initStyle(StageStyle.UNDECORATED);

        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - x);
            primaryStage.setY(event.getScreenY() - y);
        });
        primaryStage.setTitle("Crossify");
        primaryStage.setScene(new Scene(root, 890, 700));
        primaryStage.show();
        
        
    }

    public static void main(String[] args) {
        MyConnection connexion = MyConnection.getInstance();
        //Freelance f = new Freelance(20,"nounou@esprit.tn", "nounou", "Looking for a freelancer", 160.2f, true);
         //CRUDFreelance crud = new CRUDFreelance();
        //crud.addFreelance2(f);
        launch(args);
        
        //Freelance f1 = new Freelance(22,"e@esprit.tn", "Photography", "Looking for a photographer", 160.444f, false);
        //Freelance f2 = new Freelance(0,"f@esprit.tn", "Digital Marketing", "Looking for a digital marketing expert", 25.7f, true);

        /*Freelance f = new Freelance(1, 0, "updated", "updated", "updated", 160.2f, false);

        CRUDFreelance crud = new CRUDFreelance();
        //List<Freelance> offers = new ArrayList<Freelance>();
        ObservableList<Freelance> myList = FXCollections.observableArrayList();
        crud.addFreelance2(f);
        //crud.addFreelance2(f1);
        //crud.addFreelance2(f2);
        crud.modifyFreelance(f);
        crud.deleteFreelance(0);
        //myList=crud.displayFreelancee();
        for (int i = 0; i < myList.size(); i++) {
            System.out.println(myList.get(i).toString());

        }*/
    }

}
