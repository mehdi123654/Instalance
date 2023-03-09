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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author emnaa
 */
public class FreelanceManagement extends Application {

    public static Stage window;
    public static Scene allOffersScene;
    double x, y = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        //window=primaryStage;
        //Parent root = FXMLLoader.load(getClass().getResource("/com/crossify/view/BO/FreelanceManagement.fxml"));

        //window.initStyle(StageStyle.UNDECORATED);
        

        /*root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {
            window.setX(event.getScreenX() - x);
            window.setY(event.getScreenY() - y);
        });*/
        //allOffersScene=new Scene(root, 890, 700);
        //window.setScene(allOffersScene);
        //window.show();*/
        // create buttons
        Button freelancerButton = new Button("Freelancer");
        Button businessOwnerButton = new Button("Business Owner");
        Button adminButton = new Button("Admin");

        // set button actions
        freelancerButton.setOnAction(event -> openFXMLFile("/com/crossify/view/Freelancer/FreelanceManagement_1.fxml"));
        businessOwnerButton.setOnAction(event -> openFXMLFile("/com/crossify/view/BO/FreelanceManagement.fxml"));
        //adminButton.setOnAction(event -> openFXMLFile("admin.fxml"));

        // create layout
        VBox root = new VBox(freelancerButton, businessOwnerButton/*, adminButton*/);

        // create scene
        Scene scene = new Scene(root, 200, 150);

        // set scene and show stage
        primaryStage.setScene(scene);
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("Choose your role");
        primaryStage.show();

    }

    public static void main(String[] args) {
        MyConnection connexion = MyConnection.getInstance();
        launch(args);

    }

    private void openFXMLFile(String fxmlFileName) {
        try {
            //window = prim;
            // load FXML file
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFileName));
            //window.initStyle(StageStyle.UNDECORATED);
            root.setOnMousePressed(event -> {
                x = event.getSceneX();
                y = event.getSceneY();
            });
            root.setOnMouseDragged(event -> {
                window.setX(event.getScreenX() - x);
                window.setY(event.getScreenY() - y);
            });
            
            window =new Stage();
            // create scene and show stage
            allOffersScene = new Scene(root, 890, 700);
            window.setScene(allOffersScene);
            window.show();
            //Stage stage = new Stage();
            //window = stage;
            //window.initStyle(StageStyle.UNDECORATED);
            //stage.initStyle(StageStyle.UNDECORATED);
            //stage.setScene(scene);
            //stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
