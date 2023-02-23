/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursebase.controller;

import coursebase.dao.CourseDao;
import coursebase.dao.LessonDao;
import coursebase.entity.Course;
import coursebase.entity.Lesson;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import static java.util.Spliterators.iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class UpdateCourseController implements Initializable {

    @FXML
    private TextField id_field;

    @FXML
    private TextArea desc_field;

    @FXML
    private TextField title_field;

    @FXML
    private TextField price_field;

    @FXML
    private TextField photo_field;

    @FXML
    private Button update_butt;

    @FXML
    private Button back;
    @FXML
    private ChoiceBox<String> categ_choice;
    @FXML
    private TableView<Lesson> lesstable;
    @FXML
    private Button delall;
    @FXML
    private Button dl;

    @FXML
    private TableColumn<Lesson, String> lessonTitlefield;
    @FXML
    private Button lsnad;

    @FXML
    private Button delete;
    private Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * Initializes the controller class.
     *
     * @param id
     * @param desc
     * @param title
     * @param price
     * @param photo
     * @param catg
     */
    public void displayName(String id, String desc, String title, String price, String photo, String catg) {
        id_field.setText(id);
        desc_field.setText(desc);
        title_field.setText(title);
        price_field.setText(price);
        photo_field.setText(photo);
        categ_choice.setValue(catg);

        update_butt.setOnAction(
                event -> {
                    String selectedChoice = categ_choice.getSelectionModel().getSelectedItem();
                    Course p = new Course(Integer.parseInt(id_field.getText()), title_field.getText(), desc_field.getText(), Integer.parseInt(price_field.getText()), selectedChoice, photo_field.getText());
                    CourseDao pdao = CourseDao.getInstance();
                    pdao.update(p);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Course updated succesfully !");
                    alert.show();

                });

        back.setOnAction(event -> {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/coursebase/view/ShowCourse.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ShowCourseController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        ListLesson listdata = new ListLesson(Integer.parseInt(id_field.getText()));
        lesstable.setItems(listdata.getJoins());

        lessonTitlefield.setCellValueFactory(cell -> cell.getValue().getNameProperty());
        lesstable.setOnMouseClicked(event -> {

            try {
                String h = String.valueOf(listdata.getJoins().get(lesstable.getSelectionModel().getSelectedIndex()).getLid());

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/coursebase/view/UpdateLesson.fxml"));
                root = loader.load();

                UpdateLessonController scene2Controller = loader.getController();
                scene2Controller.displayName(h);
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(UpdateCourseController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        delete.setOnAction(event -> {
            int valueOf = listdata.getJoins()
                    .get(lesstable.getSelectionModel().getSelectedIndex())
                    .getLid();

            LessonDao pdao = LessonDao.getInstance();
            pdao.delete(valueOf);

            Lesson selectedItem = lesstable.getSelectionModel().getSelectedItem();
            lesstable.getItems().remove(selectedItem);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("lesson deleted !");
            alert.show();

        });
        delall.setOnAction(event -> {

            ObservableList<Lesson> items = lesstable.getItems();

// Iterate over each item and delete them from the database
            items.stream().map((item) -> item.getLid()).forEachOrdered((valueOf) -> {
                LessonDao pdao = LessonDao.getInstance();
                pdao.delete(valueOf);
            });
            lesstable.getItems().clear();
        });
        dl.setOnAction(event -> {

            CourseDao pdao = CourseDao.getInstance();
            pdao.delete(Integer.parseInt(id_field.getText()));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("course deleted !");
            alert.show();
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/coursebase/view/ShowCourse.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ShowCourseController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        categ_choice.setItems(FXCollections.observableArrayList("Development", "Bussiness", "Marketing", "Mathematics"));

        lsnad.setOnAction(event -> {
            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/coursebase/view/AddLesson.fxml"));
                root = loader.load();

                AddLessonController scene2Controller = loader.getController();
                scene2Controller.displayName(id_field.getText());
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AddLessonController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }
}
