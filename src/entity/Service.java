/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author belhassan
 */

    
    
    public class Service {
        
    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty descr;
   private SimpleIntegerProperty prix;
    private SimpleStringProperty file;
     private SimpleStringProperty cat;
  
    public Service() {
    }

    
    public Service(int id, String name, String descr,int prix,String file,String cat ) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.descr = new SimpleStringProperty(descr);
         this.prix = new SimpleIntegerProperty(prix);
          this.file= new SimpleStringProperty(file);
             this.cat= new SimpleStringProperty(cat);
     
   
    }

    public Service(String name, String descr,int prix,String file,String cat) {
        this.name = new SimpleStringProperty(name);
        this.descr = new SimpleStringProperty(descr);
         this.prix = new SimpleIntegerProperty(prix);
         this.file = new SimpleStringProperty(file);
             this.cat = new SimpleStringProperty(cat);
   
    }
    public int getId() {
        return id.get();
    }
     public String getName() {
        return name.get();
    }
      public String getDesc() {
        return descr.get();
    }
         public int getPrix() {
        return prix.get();
    }
          public String getFile() {
        return file.get();
    }
           public String getCat() {
        return cat.get();
    }

     
    public void setId(int id) {
        this.id = new SimpleIntegerProperty(id);
    }

   
    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }
  
      
    public void setDesc(String descr) {
        this.descr = new SimpleStringProperty(descr);
    }
   
    
    public void setPrix(int prix) {
        this.prix = new SimpleIntegerProperty(prix);
    }
 public void setFile(String file) {
        this.file = new SimpleStringProperty(file);
    }
 public void setCat(String cat) {
        this.cat = new SimpleStringProperty(cat);
    }
   
      public SimpleIntegerProperty getIdProperty() {
       return id;
    }

    public SimpleStringProperty getNameProperty(){
        return name;
    }
     public SimpleStringProperty getDescProperty(){
        return descr;
     }
      public SimpleIntegerProperty getPrixProperty() {
       return prix;
    } 
       public SimpleStringProperty getFileProperty(){
        return file;
    }
       public SimpleStringProperty getCatProperty(){
        return cat;
    }
  
}
