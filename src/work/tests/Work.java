/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package work.tests;

import work.utils.MyConnection;
import work.enteties.Blog;
import work.services.CRUDBlog;
import work.enteties.Comment;
import work.services.CRUDComment;

/**
 *
 * @author zeinab
 */
public class Work {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        MyConnection connexion = MyConnection.getInstance();
        /*Blog b = new Blog("aaa", "llll");
        Blog b1 = new Blog("two", "hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
        Blog b5 = new Blog("ttt", "amodifierb");

        CRUDBlog crud = new CRUDBlog();
        crud.AddBlog(b1);
      
        
        crud.ModifyBlog(new Blog(37, "ffffffffffffyuck", "youuuu"));
        crud.DisplayBlog();
*/
       // Comment c = new Comment("firstattempt",37);
       //CRUDComment crudc = new CRUDComment();
        //crudc.AddComment(c);
       // crudc.ModifyComment(new Comment(22,"modiffffyyyyyyyyyyyyyyyyyyy",37));
      // crudc.DeleteComment(23);
       // crudc.DisplayComment();
    //   CRUDBlog crud = new CRUDBlog();
    //   Blog b = new Blog("aaa", "llll");
     //  crud.update( new Blog("aaa", "llll"), 38);
    }
    
}
