/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package work.enteties;
import java.util.Objects;

/**
 *
 * @author zeinab
 */
public class Comment {
     private int id_comment,id_blog;
    private String body;
    
    
    public Comment(int id_comment ,String body,int id_blog) {
        this.id_comment=id_comment;
       
        this.body=body;
        this.id_blog=id_blog;
    }
    
       public Comment(String body,int id_blog) {
        this.body=body;
        this.id_blog=id_blog;
    }

    public Comment(int id_comment, String body) {
        this.body=body;
        this.id_comment=id_comment;
    }
       
        @Override
    public String toString() {
        return body;
    }

    public int getid() {
        return id_comment;
    }
    
    
     public String getbody() {
        return body;
    } 

    public int getId_blog() {
        return id_blog;
    }

    public void setId_blog(int id_blog) {
        this.id_blog = id_blog;
    }
    
     
       public void setid(int id_comment) {
        this.id_comment = id_comment;
    }
       
       
    
       
     
         public void setbody(String body) {
        this.body= body;
    }
         
          @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + this.id_comment;
        hash = 11 * hash + Objects.hashCode(this.body);

        return hash;
    }

    
    
}
