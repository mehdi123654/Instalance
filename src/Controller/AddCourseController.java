/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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

import dao.CourseDao;
import entity.Course;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.stage.FileChooser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import javafx.scene.image.ImageView;

import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AddCourseController implements Initializable {

    FileChooser fileChooser = new FileChooser();
    @FXML
    private Label photo;

  

    @FXML
    private ChoiceBox<String> category;

    @FXML
    private TextField price;

    @FXML
    private Button search_button;

    @FXML
    private TextField title;
    @FXML
    private Button btn;
    @FXML
    private ImageView img;
    @FXML
    private TextField desc;
    @FXML
    private Button showall_btn;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
fileChooser.getExtensionFilters().addAll(
         new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
 );
        category.setItems(FXCollections.observableArrayList("Development", "Bussiness", "Marketing", "Mathematics"));
        UnaryOperator<Change> numericFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*")) {
                return change;
            }
            return null;
        };
        TextFormatter<String> formatter = new TextFormatter<>(numericFilter);
        price.setTextFormatter(formatter);
        search_button.setOnAction(
                event -> {

                    fileChooser.setTitle("Open File");
                    File file = fileChooser.showOpenDialog(null); // you could pass a stage reference here if you wanted.

                    if (file != null) {
                      
                        //creating the image object
                        InputStream stream;
                        try {
                            stream = new FileInputStream(file.getAbsolutePath());
                            Image image = new Image(stream);
                         String p= file.getAbsolutePath().replace("\\","@");                     
                       
                              photo.setText(p);
                            img.setImage(image);
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(AddCourseController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
        );
        btn.setOnAction(
                event -> {
                     String selectedChoice = category.getSelectionModel().getSelectedItem();
                    if(title.getText()!=null&& desc.getText()!=null  && price.getText()!=null && selectedChoice!=null && photo.getText()!=null){
                   
                    Course p = new Course(title.getText(), desc.getText(), Integer.parseInt(price.getText()), selectedChoice,photo.getText());
                    CourseDao pdao = CourseDao.getInstance();
                    pdao.insert(p);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Course added successfully!");
                    alert.show();}
                    else {
                         Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Please fill all fields!");
                    alert.show();
                    }

                });

        showall_btn.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/ShowCourse.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ShowCourseController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

}
