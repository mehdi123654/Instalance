/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursebase.controller;

import coursebase.entity.Course;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ShowCourseController implements Initializable {

    @FXML
    private TableView<Course> courseTable;

    @FXML
    private TableColumn<Course, Number> cidcolumn;

    @FXML
    private TableColumn<Course, String> titlecolumn;

    @FXML
    private TableColumn<Course, String> desccolumn;

    @FXML
    private TableColumn<Course, Number> pricecolumn;

    @FXML
    private TableColumn<Course, String> categorycolumn;

    @FXML
    private TableColumn<Course, String> photocolumn;
    



    @FXML
    private Button add_button;


    private ListData listdata;


    /**
     * Initializes the controller class.
     *
     * @param fil
     * @return
     */
    private Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        add_button.setOnAction(
                event -> {
                    try {
                        Parent page1 = FXMLLoader.load(getClass().getResource("/coursebase/view/AddCourse.fxml"));
                        Scene scene = new Scene(page1);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(AddCourseController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                });
      
        listdata = new ListData();
        courseTable.setItems(listdata.getCourses());
        cidcolumn.setCellValueFactory(cell -> cell.
                getValue().getCidProperty());
        titlecolumn.setCellValueFactory(cell -> cell.
                getValue().getTitleProperty());
        pricecolumn.setCellValueFactory(cell -> cell.
                getValue().getPriceProperty());
        desccolumn.setCellValueFactory(cell -> cell.
                getValue().getDecriptionProperty());
        categorycolumn.setCellValueFactory(cell -> cell.
                getValue().getCategoryProperty());
        photocolumn.setCellValueFactory(cell -> cell.
                getValue().getPhotoProperty());
//        lescol.setCellValueFactory(cell -> cell.
//                getValue().getnbLessonsProperty());


        courseTable.setOnMouseClicked( event -> {

            try {
                String id = String.valueOf(listdata.getCourses().get(courseTable.getSelectionModel().getSelectedIndex()).getCid());
                String desc = String.valueOf(listdata.getCourses().get(courseTable.getSelectionModel().getSelectedIndex()).getDecription());
                String title = String.valueOf(listdata.getCourses().get(courseTable.getSelectionModel().getSelectedIndex()).getTitle());
                String price = String.valueOf(listdata.getCourses().get(courseTable.getSelectionModel().getSelectedIndex()).getPrice());
                String photo = String.valueOf(listdata.getCourses().get(courseTable.getSelectionModel().getSelectedIndex()).getPhoto());
                String catg = String.valueOf(listdata.getCourses().get(courseTable.getSelectionModel().getSelectedIndex()).getCategory());

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/coursebase/view/UpdateCourse.fxml"));
                root = loader.load();

                UpdateCourseController scene2Controller = loader.getController();
                scene2Controller.displayName(id, desc, title, price, photo, catg);
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(UpdateCourseController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

    }

}
