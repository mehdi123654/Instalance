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
public class Blog {
    private int id_blog;
    private String title,body;
    
    public Blog(int id_blog,String title,String body) {
        this.id_blog=id_blog;
        this.title=title;
        this.body=body;
    }
    
    public Blog(String title) {
       
        this.title=title;
    
    }
    
       public Blog(String title,String body) {
           this.title=title;
        this.body=body;
    }
  

  


     @Override
    public String toString() {
        return title ;
    }


    public int getid() {
        return id_blog;
    }
    
    
     public String getbody() {
        return body;
    } 
     public String gettitle() {
        return title;
    }
     
       public void setid(int id_blog) {
        this.id_blog = id_blog;
    }
       
       
       
    
       
       public void settitle(String title) {
        this.title= title;
    }
       
         public void setbody(String body) {
        this.body= body;
    }
    
         @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + this.id_blog;
        hash = 11 * hash + Objects.hashCode(this.title);
        hash = 11 * hash + Objects.hashCode(this.body);

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Blog other = (Blog) obj;
        if (this.id_blog != other.id_blog) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        return true;
    }
    
    
}
