/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author belhassan
 */
public class Packag {
    

        
    private SimpleIntegerProperty id_p,id_s;
    private SimpleStringProperty type;
    private SimpleIntegerProperty price;
   

    public Packag() {
    }
public Packag(int id_s ,String type,int price) {
     
         this.id_s = new SimpleIntegerProperty(id_s);
        this.type = new SimpleStringProperty(type);
      this.price = new SimpleIntegerProperty(price);
    
}
public Packag(int id_p,int id_s ,String type,int price) {
      this.id_p = new SimpleIntegerProperty(id_p);
         this.id_s = new SimpleIntegerProperty(id_s);
        this.type = new SimpleStringProperty(type);
      this.price = new SimpleIntegerProperty(price);
    
}   
     public int getid_s() {
        return id_s.get();
    }
    public int getIdp() {
        return id_p.get();
    }
     public String getType() {
        return type.get();
    }
      
   public int getPrice() {
       return price.get();
    }
   
     public void setid_s(int f) {
        this.id_s = new SimpleIntegerProperty(f);
    } 
    public void setIdp(int id_p) {
        this.id_p = new SimpleIntegerProperty(id_p);
    }

   
    public void setType(String type) {
        this.type = new SimpleStringProperty(type);
    }
  public void setPrice(int price) {
        this.price = new SimpleIntegerProperty(price);
    
    }

   
      public SimpleIntegerProperty getIdpProperty() {
       return id_p;
    }

    public SimpleStringProperty getTypeProperty(){
        return type;
    }
  
        
       public SimpleIntegerProperty getPriceProperty() {
       return price;
    }
    
       public SimpleIntegerProperty getid_sProperty() {
       return id_s;
    }

   
  

  
    
    
}

