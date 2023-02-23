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
public class Lesson {
    
    private SimpleIntegerProperty cid, lid;
    private SimpleStringProperty name, file;
     public Lesson() {
       
    }

    public Lesson(int cid,String name, String file) {

           this.cid = new SimpleIntegerProperty(cid);
        this.name = new SimpleStringProperty(name);
        this.file = new SimpleStringProperty(file);
       
          
    }

    public Lesson(int lid,int cid,String name,  String file ) {
         this.cid = new SimpleIntegerProperty(cid);
        this.lid = new SimpleIntegerProperty(lid);
        this.name = new SimpleStringProperty(name);
        this.file = new SimpleStringProperty(file);
      
        
    }
  

    public int getCid() {
        return cid.get();
    }

    public void setCid(int cid) {
        this.cid = new SimpleIntegerProperty(cid);
    }

    public int getLid() {
        return lid.get();
    }

    public void setLid(int lid) {
        this.lid = new SimpleIntegerProperty(lid);
    }
    public String getFile() {
        return file.get();
    }

    public void setFile(String file) {
        this.file = new SimpleStringProperty(file);
    }

    public String getName() {
        return name.get();
    }

    @Override
    public String toString() {
        return "Lesson{" + "cid=" + cid + ", lid=" + lid + ", name=" + name + ", file=" + file + '}';
    }

    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }
    public SimpleStringProperty getFileProperty(){
        return file;
    }
    public SimpleStringProperty getNameProperty(){
        return name;
    }
     public SimpleIntegerProperty getCidProperty(){
        return cid;
    }
      public SimpleIntegerProperty getLidProperty(){
        return lid;
    }

}
