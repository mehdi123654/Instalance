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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class UpdateLessonController implements Initializable {

    @FXML
    private Button delete;
    @FXML
    private Label lid;
    @FXML
    private TextField title;

    @FXML
    private TextField file;

    @FXML
    private Label cid;
    @FXML
    private Button save;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    void displayName(String h) {

        lid.setText(h);
        int id = Integer.parseInt(lid.getText());
        LessonDao pdao = LessonDao.getInstance();
        title.setText(pdao.displayById(id).getName());
        file.setText(pdao.displayById(id).getFile());
//       title.setText( pdao.displayById(id).getName());
//           file.setText( pdao.displayById(id).getFile());
        cid.setText(String.valueOf(pdao.displayById(id).getCid()));

        save.setOnAction(
                event -> {

                    Lesson p = new Lesson(Integer.parseInt(lid.getText()), Integer.parseInt(cid.getText()), title.getText(), file.getText());
                    LessonDao s = LessonDao.getInstance();
                    s.update(p);

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

        delete.setOnAction(event -> {

            LessonDao dao = LessonDao.getInstance();
            dao.delete(Integer.parseInt(lid.getText()));
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/coursebase/view/ShowCourse.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ShowCourseController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("lesson deleted !");
            alert.show();

        });

    }

}
