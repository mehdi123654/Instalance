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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ExploreController implements Initializable {

    private final ListData listdata = new ListData();

    @FXML
    private StackPane ss;
    @FXML
    private AnchorPane fr;

    @FXML
    private TabPane tp;
    @FXML
    private Tab ac;
    @FXML
    private Button searchbutt;

    @FXML
    private TextField search;

    @FXML
    private ChoiceBox<String> srtb;

    @FXML
    private AnchorPane fr1;

    @FXML
    private StackPane ss1;

    @FXML
    private AnchorPane fr2;

    @FXML
    private StackPane ss2;

    @FXML
    private AnchorPane fr3;

    @FXML
    private StackPane ss3;

    @FXML
    private AnchorPane fr4;

    @FXML
    private StackPane ss4;
    /**
     * Initializes the controller class.
     *
     * @param fil
     * @return
     */
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private StackPane s;
    int i = -600, y = -150;
    int a = 1000, index = 0;

    @Override

    public void initialize(URL url, ResourceBundle rb) {

        for (Course c : listdata.getCourses().filtered(product -> product.getCategory().equals("Mathematics"))) {
            if (i > 0) {
                fr4.setPrefWidth(a += 300);
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
                Label b1 = new Label("" + c.getPrice() + "$");
                Color lightBlue = Color.web("#d473d4");
                b.setBackground(new Background(new BackgroundFill(lightBlue, null, null)));
                b1.setTranslateX(i);
                b1.setTranslateY(-30);

                imageView.setTranslateX(i);
                imageView.setTranslateY(-50);

                i += 300;

                ss4.getChildren().addAll(b1, imageView, b);

                b.setOnAction(
                        even -> {
                            try {

                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/coursebase/view/FXML.fxml"));
                                root = loader.load();
                                FXMLController sceneController = loader.getController();
                                sceneController.displayName(String.valueOf(c.getCid()), c.getTitle(),c.getDecription());
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
        i = -600;
        a = 1000;
        for (Course c : listdata.getCourses().filtered(product -> product.getCategory().equals("Marketing"))) {
            if (i > 0) {
                fr3.setPrefWidth(a += 300);
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
                Label b1 = new Label("" + c.getPrice() + "$");
                Color lightBlue = Color.web("#d473d4");
                b.setBackground(new Background(new BackgroundFill(lightBlue, null, null)));
                b1.setTranslateX(i);
                b1.setTranslateY(-30);

                imageView.setTranslateX(i);
                imageView.setTranslateY(-50);

                i += 300;

                ss3.getChildren().addAll(b1, imageView, b);

                b.setOnAction(
                        even -> {
                            try {

                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/coursebase/view/FXML.fxml"));
                                root = loader.load();
                                FXMLController sceneController = loader.getController();
                                sceneController.displayName(String.valueOf(c.getCid()), c.getTitle(),c.getDecription());
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
        i = -600;
        a = 1000;
        for (Course c : listdata.getCourses().filtered(product -> product.getCategory().equals("Bussiness"))) {
            if (i > 0) {
                fr2.setPrefWidth(a += 300);
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
                Label b1 = new Label("" + c.getPrice() + "$");
                Color lightBlue = Color.web("#d473d4");
                b.setBackground(new Background(new BackgroundFill(lightBlue, null, null)));
                b1.setTranslateX(i);
                b1.setTranslateY(-30);

                imageView.setTranslateX(i);
                imageView.setTranslateY(-50);

                i += 300;

                ss2.getChildren().addAll(b1, imageView, b);

                b.setOnAction(
                        even -> {
                            try {

                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/coursebase/view/FXML.fxml"));
                                root = loader.load();
                                FXMLController sceneController = loader.getController();
                                sceneController.displayName(String.valueOf(c.getCid()), c.getTitle(),c.getDecription());
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

        i = -600;
        a = 1000;
        for (Course c : listdata.getCourses().filtered(product -> product.getCategory().equals("Development"))) {
            if (i > 0) {
                fr1.setPrefWidth(a += 300);
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
                Label b1 = new Label("" + c.getPrice() + "$");
                Color lightBlue = Color.web("#d473d4");
                b.setBackground(new Background(new BackgroundFill(lightBlue, null, null)));
                b1.setTranslateX(i);
                b1.setTranslateY(-30);

                imageView.setTranslateX(i);
                imageView.setTranslateY(-50);

                i += 300;

                ss1.getChildren().addAll(b1, imageView, b);

                b.setOnAction(
                        even -> {
                            try {

                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/coursebase/view/FXML.fxml"));
                                root = loader.load();
                                FXMLController sceneController = loader.getController();
                                sceneController.displayName(String.valueOf(c.getCid()), c.getTitle(),c.getDecription());
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

     

        srtb.setItems(FXCollections.observableArrayList("Name", "Price"));
        //*****************************************sort *****************************************************
        srtb.setOnAction(event -> {
            tp.getSelectionModel().select(ac);

            String searchValue = srtb.getSelectionModel().getSelectedItem();
            if ("Name".equals(searchValue)) {
                ss.getChildren().clear();

                int o = -600;

                int g = 1000;
                for (Course c : listdata.getCourses().sorted((o1, o2) -> o1.getTitle().compareTo(o2.getTitle()))) {
                    if (o > 0) {
                        fr.setPrefWidth(g += 300);

                    }
                    try {
                        String p = c.getPhoto().replace("@", "\\");
                        FileInputStream input = new FileInputStream(p);
                        Image image = new Image(input);
                        ImageView imageView = new ImageView(image);
                        Button b = new Button(c.getTitle());
                        Label b1 = new Label("" + c.getPrice() + "$");
                        b1.setTranslateX(o);
                        b1.setTranslateY(-30);

                        b.setTranslateX(o);
                        Color lightBlue = Color.web("#d473d4");
                        b.setBackground(new Background(new BackgroundFill(lightBlue, null, null)));
                        b.setTranslateY(-50);
                        imageView.setFitWidth(200); // set the desired width
                        imageView.setFitHeight(150); // set the desired height
                        imageView.setImage(image);
                        imageView.setTranslateX(o);
                        imageView.setTranslateY(-50);

                        o += 300;

                        ss.getChildren().addAll(b1, imageView, b);

                        b.setOnAction(
                                even -> {
                                    try {

                                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/coursebase/view/FXML.fxml"));
                                        root = loader.load();
                                        FXMLController sceneController = loader.getController();
                                        sceneController.displayName(String.valueOf(c.getCid()), c.getTitle(),c.getDecription());
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
                ss.getChildren().clear();
                int o = -600, g = 1000;

                for (Course c : listdata.getCourses().sorted((p1, p2) -> Integer.compare(p1.getPrice(), p2.getPrice()))) {
                    if (o > 0) {
                        fr.setPrefWidth(g += 300);
                    }
                    try {
                        String p = c.getPhoto().replace("@", "\\");
                        FileInputStream input = new FileInputStream(p);
                        Image image = new Image(input);
                        ImageView imageView = new ImageView(image);
                        Button b = new Button(c.getTitle());
                        Label b1 = new Label("" + c.getPrice() + "$");
                        b.setTranslateX(o);
                        b.setTranslateY(-50);
                        imageView.setFitWidth(200); // set the desired width
                        imageView.setFitHeight(150); // set the desired height
                        imageView.setImage(image);
                        imageView.setTranslateX(o);
                        imageView.setTranslateY(-50);
                        Color lightBlue = Color.web("#d473d4");
                        b.setBackground(new Background(new BackgroundFill(lightBlue, null, null)));
                        b1.setTranslateX(o);
                        b1.setTranslateY(-30);

                        o += 300;
                        ss.getChildren().add(imageView);
                        ss.getChildren().add(b);
                        ss.getChildren().add(b1);
                        b.setOnAction(
                                even -> {
                                    try {

                                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/coursebase/view/FXML.fxml"));
                                        root = loader.load();

                                        FXMLController sceneController = loader.getController();
                                        sceneController.displayName(String.valueOf(c.getCid()), c.getTitle(),c.getDecription());
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

//**************************************************display courses****************************************************
        i = -600;
        a = 1000;
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
                Color lightBlue = Color.web("#d473d4");
                b.setBackground(new Background(new BackgroundFill(lightBlue, null, null)));
                b.setTranslateX(i);
                b.setTranslateY(-50);
                imageView.setFitWidth(200); // set the desired width
                imageView.setFitHeight(150); // set the desired height
                imageView.setImage(image);
                imageView.setTranslateX(i);
                Label b1 = new Label("" + c.getPrice() + "$");
                b1.setTranslateX(i);
                b1.setTranslateY(-30);

                imageView.setTranslateY(-50);
                Label label = new Label(c.getTitle());
                label.setTranslateX(i);
                label.setTranslateY(y);
                i += 300;
                ss.getChildren().addAll(b1, imageView, b);

                b.setOnAction(
                        even -> {
                            try {

                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/coursebase/view/FXML.fxml"));
                                root = loader.load();

                                FXMLController sceneController = loader.getController();
                                sceneController.displayName(String.valueOf(c.getCid()), c.getTitle(),c.getDecription());
                                //    UpdateCourseController scene2Controller = loader.getController();
                                // scene2Controller.displayName(String.valueOf(c.getCid()), c.getDecription(), c.getTitle(), String.valueOf(c.getPrice()), c.getPhoto(), c.getCategory());
                                stage = (Stage) ((Node) even.getSource()).getScene().getWindow();
                                scene = new Scene(root);
                                stage.setScene(scene);
                                stage.show();
                            } catch (IOException ex) {
                                Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ShowCourseController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        // **********************************************search by title *******************************************
        searchbutt.setOnAction(event -> {
            tp.getSelectionModel().select(ac);
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
                        imageView.setTranslateX(o);
                        imageView.setTranslateY(-50);
                        Label label = new Label(c.getTitle());
                        Label b1 = new Label("" + c.getPrice() + "$");
                        b1.setTranslateX(o);
                        b1.setTranslateY(-30);
                        ss.getChildren().add(b1);
                        label.setTranslateX(o);
                        label.setTranslateY(y);
                        o += 300;
                        ss.getChildren().add(imageView);
                        ss.getChildren().add(b);
                        b.setOnAction(
                                even -> {
                                    try {

                                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/coursebase/view/FXML.fxml"));
                                        root = loader.load();

                                        FXMLController sceneController = loader.getController();
                                        sceneController.displayName(String.valueOf(c.getCid()), c.getTitle(),c.getDecription());
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

                for (Course c : listdata.getCourses().filtered(product -> product.getTitle().equals(searchValue))) {
                    if (l > 0) {
                        fr.setPrefWidth(a += 300);
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
                        Label b1 = new Label("" + c.getPrice() + "$");
                        b1.setTranslateX(l);
                        b1.setTranslateY(-30);
                        ss.getChildren().add(b1);
                        imageView.setTranslateX(l);
                        imageView.setTranslateY(-50);
                        Label label = new Label(c.getTitle());
                        label.setTranslateX(l);
                        label.setTranslateY(y);
                        l += 300;
                        //ss.getChildren().add(label);
                        ss.getChildren().add(imageView);
                        ss.getChildren().add(b);
                        b.setOnAction(
                                even -> {
                                    try {

                                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/coursebase/view/FXML.fxml"));
                                        root = loader.load();

                                        FXMLController sceneController = loader.getController();
                                        sceneController.displayName(String.valueOf(c.getCid()), c.getTitle(),c.getDecription());
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

    }
}
