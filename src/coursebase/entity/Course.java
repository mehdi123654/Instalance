/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursebase.entity;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


/**
 *
 * @author user
 */
public class Course {

    private SimpleIntegerProperty cid, price,nbLessons;
    private SimpleStringProperty title, decription, photo, category;

    public Course() {
       
    }

    public Course(String title, String decription, int price, String category, String photo) {

        this.price = new SimpleIntegerProperty(price);
        this.title = new SimpleStringProperty(title);
        this.decription = new SimpleStringProperty(decription);
        this.photo = new SimpleStringProperty(photo);     
        this.category = new SimpleStringProperty(category);
          
    }

    public Course(int cid,String title,  String decription,int price,String category,  String photo ) {
        this.cid = new SimpleIntegerProperty(cid);
        this.price = new SimpleIntegerProperty(price);
        this.title = new SimpleStringProperty(title);
        this.decription = new SimpleStringProperty(decription);
        this.photo = new SimpleStringProperty(photo);
        this.category = new SimpleStringProperty(category);
        
    }
  

    public int getCid() {
        return cid.get();
    }

    public void setCid(int cid) {
        this.cid = new SimpleIntegerProperty(cid);
    }

    public int getPrice() {
        return price.get();
    }

    public void setPrice(int price) {
        this.price = new SimpleIntegerProperty(price);
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title = new SimpleStringProperty(title);
    }

    public String getDecription() {
        return decription.get();
    }

    public void setDecription(String decription) {
        this.decription = new SimpleStringProperty(decription);
    }

    public String getPhoto() {
        return photo.get();
    }

    public void setPhoto(String photo) {
        this.photo = new SimpleStringProperty(photo);
    }

    public String getCategory() {
        return category.get();
    }

    public void setCategory(String category) {
        this.category = new SimpleStringProperty(category);
    }
    
    public SimpleStringProperty getDecriptionProperty(){
        return decription;
    }
    public SimpleStringProperty getCategoryProperty(){
        return category;
    }
    public SimpleStringProperty getPhotoProperty(){
        return photo;
    }
     public SimpleIntegerProperty getPriceProperty(){
        return price;
    }
      public SimpleIntegerProperty getCidProperty(){
        return cid;
    }

    public SimpleStringProperty getTitleProperty() {
        return title;
    }
//  public void setnbLessons(int lid) {
//        this.nbLessons = new SimpleIntegerProperty(lid);
//    }
//     public SimpleIntegerProperty getnbLessonsProperty(){
//        return nbLessons;
//    }

}
