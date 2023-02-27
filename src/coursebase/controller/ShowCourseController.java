/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursebase.controller;

import coursebase.entity.Course;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ShowCourseController implements Initializable {

    @FXML
    private Button stat;
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
    private ScrollPane scr;
    @FXML
    private Button add_button;

    private ListData listdata;
   
    @FXML
    private Button f;
    @FXML
    private StackPane ss;
    @FXML
    private AnchorPane fr;
    @FXML
    private ChoiceBox<String> chb;
    @FXML
    private Button searchbutt;

    @FXML
    private TextField search;
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
        listdata = new ListData();
        chb.setItems(FXCollections.observableArrayList("Development", "Bussiness", "Marketing", "Mathematics"));
       chb.setOnAction(event -> {
         String searchValue = chb.getSelectionModel().getSelectedItem();     
  
    if (searchValue.isEmpty()) {
        ss.getChildren().clear();
      int o = -600;

                        int g = 1000;
                        for (Course c : listdata.getCourses()) {
                            if (o > 0) {
                                fr.setPrefWidth(g += 300);
                            }
                            try {
                                String p = c.getPhoto().replace("@", "\\");
                                FileInputStream input = new FileInputStream(p);
                                Image image = new Image(input);
                                ImageView imageView = new ImageView(image);
                                Button b = new Button(c.getTitle());
                                b.setTranslateX(o);
                                b.setTranslateY(-50);
                                imageView.setFitWidth(200); // set the desired width
                                imageView.setFitHeight(150); // set the desired height
                                imageView.setImage(image);
                                //  g+=300;
                                imageView.setTranslateX(o);
                                imageView.setTranslateY(-50);
                              //  Label label = new Label(c.getTitle());
                               // label.setTranslateX(o);
                               // label.setTranslateY(y);
                                o += 300;
                                //ss.getChildren().add(label);
                                ss.getChildren().add(imageView);
                                ss.getChildren().add(b);
                                //ss.getChildren().add(aa);
                                //  scr.setContent(ss);
 b.setOnAction(
                even -> {
                               try {
         
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/coursebase/view/UpdateCourse.fxml"));
            root = loader.load();

            UpdateCourseController scene2Controller = loader.getController();
            scene2Controller.displayName(String.valueOf(c.getCid()),c.getDecription(),c.getTitle(),String.valueOf(c.getPrice()),c.getPhoto(),c.getCategory());
            stage = (Stage) ((Node) even.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(UpdateCourseController.class.getName()).log(Level.SEVERE, null, ex);
        }

                });
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(ShowCourseController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }
    } else {
         int l = -600;
ss.getChildren().clear();
                        int h = 1000;
                        for (Course c : listdata.getCourses().filtered(product -> product.getCategory().equals(searchValue))) {
                            if (l > 0) {
                                fr.setPrefWidth(h += 300);
                            }
                            try {
                                String p = c.getPhoto().replace("@", "\\");
                                FileInputStream input = new FileInputStream(p);
                                Image image = new Image(input);
                                ImageView imageView = new ImageView(image);
                                Button b = new Button(c.getTitle());
                                b.setTranslateX(l);
                                b.setTranslateY(-50);
                                imageView.setFitWidth(200); // set the desired width
                                imageView.setFitHeight(150); // set the desired height
                                imageView.setImage(image);
                                //  g+=300;
                                imageView.setTranslateX(l);
                                imageView.setTranslateY(-50);

                                l += 300;

                                ss.getChildren().add(imageView);
                                ss.getChildren().add(b);

  b.setOnAction(
                even -> {
                               try {
         
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/coursebase/view/UpdateCourse.fxml"));
            root = loader.load();

            UpdateCourseController scene2Controller = loader.getController();
            scene2Controller.displayName(String.valueOf(c.getCid()),c.getDecription(),c.getTitle(),String.valueOf(c.getPrice()),c.getPhoto(),c.getCategory());
            stage = (Stage) ((Node) even.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(UpdateCourseController.class.getName()).log(Level.SEVERE, null, ex);
        }

                });
        
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(ShowCourseController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }
       
  
}
       
       });
        
          int i = -600, y = -150;

                        int a = 1000;
                        for (Course c : listdata.getCourses()) {
                            if (i > 0) {
                                fr.setPrefWidth(a += 300);
                            }
                            try {
                                String p = c.getPhoto().replace("@", "\\");
                                FileInputStream input = new FileInputStream(p);
                                Image image = new Image(input);
                                ImageView imageView = new ImageView(image);
                                Button b = new Button(c.getTitle());
                                b.setTranslateX(i);
                                b.setTranslateY(-50);
                                imageView.setFitWidth(200); // set the desired width
                                imageView.setFitHeight(150); // set the desired height
                                imageView.setImage(image);
                                imageView.setTranslateX(i);
                                imageView.setTranslateY(-50);
                                Label label = new Label(c.getTitle());
                                label.setTranslateX(i);
                                label.setTranslateY(y);
                                i += 300;
                                ss.getChildren().add(imageView);
                                ss.getChildren().add(b);
 b.setOnAction(
                even -> {
                               try {
         
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/coursebase/view/UpdateCourse.fxml"));
            root = loader.load();

            UpdateCourseController scene2Controller = loader.getController();
            scene2Controller.displayName(String.valueOf(c.getCid()),c.getDecription(),c.getTitle(),String.valueOf(c.getPrice()),c.getPhoto(),c.getCategory());
            stage = (Stage) ((Node) even.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(UpdateCourseController.class.getName()).log(Level.SEVERE, null, ex);
        }

                });
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(ShowCourseController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }
        
       searchbutt.setOnAction(event -> {
    String searchValue = search.getText().trim();
    if (searchValue.isEmpty()) {
        ss.getChildren().clear();
      int o = -600;

                        int g = 1000;
                        for (Course c : listdata.getCourses()) {
                            if (o > 0) {
                                fr.setPrefWidth(g += 300);
                            }
                            try {
                                String p = c.getPhoto().replace("@", "\\");
                                FileInputStream input = new FileInputStream(p);
                                Image image = new Image(input);
                                ImageView imageView = new ImageView(image);
                                Button b = new Button(c.getTitle());
                                b.setTranslateX(o);
                                b.setTranslateY(-50);
                                imageView.setFitWidth(200); // set the desired width
                                imageView.setFitHeight(150); // set the desired height
                                imageView.setImage(image);
                                //  g+=300;
                                imageView.setTranslateX(o);
                                imageView.setTranslateY(-50);
                                Label label = new Label(c.getTitle());
                                label.setTranslateX(o);
                                label.setTranslateY(y);
                                o += 300;
                                //ss.getChildren().add(label);
                                ss.getChildren().add(imageView);
                                ss.getChildren().add(b);
                                //ss.getChildren().add(aa);
                                //  scr.setContent(ss);
 b.setOnAction(
                even -> {
                               try {
         
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/coursebase/view/UpdateCourse.fxml"));
            root = loader.load();

            UpdateCourseController scene2Controller = loader.getController();
            scene2Controller.displayName(String.valueOf(c.getCid()),c.getDecription(),c.getTitle(),String.valueOf(c.getPrice()),c.getPhoto(),c.getCategory());
            stage = (Stage) ((Node) even.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(UpdateCourseController.class.getName()).log(Level.SEVERE, null, ex);
        }

                });
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(ShowCourseController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }
    } else {
         int l = -600;
ss.getChildren().clear();
                        int h = 1000;
                        for (Course c : listdata.getCourses().filtered(product -> product.getTitle().equals(searchValue))) {
                            if (l > 0) {
                                fr.setPrefWidth(h += 300);
                            }
                            try {
                                String p = c.getPhoto().replace("@", "\\");
                                FileInputStream input = new FileInputStream(p);
                                Image image = new Image(input);
                                ImageView imageView = new ImageView(image);
                                Button b = new Button(c.getTitle());
                                b.setTranslateX(l);
                                b.setTranslateY(-50);
                                imageView.setFitWidth(200); // set the desired width
                                imageView.setFitHeight(150); // set the desired height
                                imageView.setImage(image);
                                //  g+=300;
                                imageView.setTranslateX(l);
                                imageView.setTranslateY(-50);
                                Label label = new Label(c.getTitle());
                                label.setTranslateX(l);
                                label.setTranslateY(y);
                                l += 300;
                                //ss.getChildren().add(label);
                                ss.getChildren().add(imageView);
                                ss.getChildren().add(b);
                                //ss.getChildren().add(aa);
                                //  scr.setContent(ss);
  b.setOnAction(
                even -> {
                               try {
         
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/coursebase/view/UpdateCourse.fxml"));
            root = loader.load();

            UpdateCourseController scene2Controller = loader.getController();
            scene2Controller.displayName(String.valueOf(c.getCid()),c.getDecription(),c.getTitle(),String.valueOf(c.getPrice()),c.getPhoto(),c.getCategory());
            stage = (Stage) ((Node) even.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(UpdateCourseController.class.getName()).log(Level.SEVERE, null, ex);
        }

                });
        
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(ShowCourseController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }
       
  
}
});

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
        stat.setOnAction(
                event -> {
                    try {
                        Parent page1 = FXMLLoader.load(getClass().getResource("/coursebase/view/PieChart.fxml"));
                        Scene scene = new Scene(page1);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(AddCourseController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                });

//        String selectedChoice = chb.getSelectionModel().getSelectedItem();
//        
//        if ("Development".equals(selectedChoice)) {
//            l = new Listfiltered(selectedChoice);
//
//            int i = -600, a = 1000;
//            for (Course c : l.getCourses()) {
//                if (i > 0) {
//                    fr.setPrefWidth(a += 300);
//                }
//                try {
//                    String p = c.getPhoto().replace("@", "\\");
//                    FileInputStream input = new FileInputStream(p);
//                    Image image = new Image(input);
//                    ImageView imageView = new ImageView(image);
//                    Button b = new Button(c.getTitle());
//                    b.setTranslateX(i);
//                    b.setTranslateY(-50);
//                    imageView.setFitWidth(200); // set the desired width
//                    imageView.setFitHeight(150); // set the desired height
//                    imageView.setImage(image);                 
//                    imageView.setTranslateX(i);
//                    imageView.setTranslateY(-50);
////                Label label = new Label(c.getTitle());
////                label.setTranslateX(i);
////                label.setTranslateY(y);
//                    i += 300;
//                    //ss.getChildren().add(label);
//                    ss.getChildren().add(imageView);
//                    ss.getChildren().add(b);
//                    //ss.getChildren().add(aa);                 
//                    b.setOnAction(
//                            event -> {
//                                try {
//
//                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/coursebase/view/UpdateCourse.fxml"));
//                                    root = loader.load();
//
//                                    UpdateCourseController scene2Controller = loader.getController();
//                                    scene2Controller.displayName(String.valueOf(c.getCid()), c.getDecription(), c.getTitle(), String.valueOf(c.getPrice()), c.getPhoto(), c.getCategory());
//                                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                                    scene = new Scene(root);
//                                    stage.setScene(scene);
//                                    stage.show();
//                                } catch (IOException ex) {
//                                    Logger.getLogger(UpdateCourseController.class.getName()).log(Level.SEVERE, null, ex);
//                                }
//
//                            });
//
//                } catch (FileNotFoundException ex) {
//                    Logger.getLogger(ShowCourseController.class.getName()).log(Level.SEVERE, null, ex);
//                }
//
//            }
//        } 
//
//            int i = -600, y = -150;
//
//            int a = 1000;
//            for (Course c : listdata.getCourses()) {
//                if (i > 0) {
//                    fr.setPrefWidth(a += 300);
//                }
//                try {
//                    String p = c.getPhoto().replace("@", "\\");
//                    FileInputStream input = new FileInputStream(p);
//                    Image image = new Image(input);
//                    ImageView imageView = new ImageView(image);
//                    Button b = new Button(c.getTitle());
//                    b.setTranslateX(i);
//                    b.setTranslateY(-50);
//                    imageView.setFitWidth(200); // set the desired width
//                    imageView.setFitHeight(150); // set the desired height
//                    imageView.setImage(image);
//                     //  g+=300;
//                    imageView.setTranslateX(i);
//                    imageView.setTranslateY(-50);
//                Label label = new Label(c.getTitle());
//                   label.setTranslateX(i);
//                label.setTranslateY(y);
//                    i += 300;
//                    //ss.getChildren().add(label);
//                    ss.getChildren().add(imageView);
//                    ss.getChildren().add(b);
//                    //ss.getChildren().add(aa);
//                    //  scr.setContent(ss);
//                    b.setOnAction(
//                           event -> {
//                               try {
//
//                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/coursebase/view/UpdateCourse.fxml"));
//                                    root = loader.load();
//
//                                    UpdateCourseController scene2Controller = loader.getController();
//                                   scene2Controller.displayName(String.valueOf(c.getCid()), c.getDecription(), c.getTitle(), String.valueOf(c.getPrice()), c.getPhoto(), c.getCategory());
//                                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                                    scene = new Scene(root);
//                                    stage.setScene(scene);
//                                    stage.show();
//                                } catch (IOException ex) {
//                                    Logger.getLogger(UpdateCourseController.class.getName()).log(Level.SEVERE, null, ex);
//                                }
//
//                            });
//
//                } catch (FileNotFoundException ex) {
//                    Logger.getLogger(ShowCourseController.class.getName()).log(Level.SEVERE, null, ex);
//                }
//
//           }
//        listdata = new ListData();
//        courseTable.setItems(listdata.getCourses());
//        cidcolumn.setCellValueFactory(cell -> cell.
//                getValue().getCidProperty());
//        titlecolumn.setCellValueFactory(cell -> cell.
//                getValue().getTitleProperty());
//        pricecolumn.setCellValueFactory(cell -> cell.
//                getValue().getPriceProperty());
//        desccolumn.setCellValueFactory(cell -> cell.
//                getValue().getDecriptionProperty());
//        categorycolumn.setCellValueFactory(cell -> cell.
//                getValue().getCategoryProperty());
//        photocolumn.setCellValueFactory(cell -> cell.
//                getValue().getPhotoProperty());
//    
//
//    courseTable.setOnMouseClicked (event  
//        -> {
//
//            try {
//            String id = String.valueOf(listdata.getCourses().get(courseTable.getSelectionModel().getSelectedIndex()).getCid());
//            String desc = String.valueOf(listdata.getCourses().get(courseTable.getSelectionModel().getSelectedIndex()).getDecription());
//            String title = String.valueOf(listdata.getCourses().get(courseTable.getSelectionModel().getSelectedIndex()).getTitle());
//            String price = String.valueOf(listdata.getCourses().get(courseTable.getSelectionModel().getSelectedIndex()).getPrice());
//            String photo = String.valueOf(listdata.getCourses().get(courseTable.getSelectionModel().getSelectedIndex()).getPhoto());
//            String catg = String.valueOf(listdata.getCourses().get(courseTable.getSelectionModel().getSelectedIndex()).getCategory());
//
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/coursebase/view/UpdateCourse.fxml"));
//            root = loader.load();
//
//            UpdateCourseController scene2Controller = loader.getController();
//            scene2Controller.displayName(id, desc, title, price, photo, catg);
//            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
//        } catch (IOException ex) {
//            Logger.getLogger(UpdateCourseController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
//
//    );
        cidcolumn.setVisible(false);
    }

}
