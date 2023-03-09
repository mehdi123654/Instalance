/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;

import entity.Blog;
/**
 *
 * @author zeinab
 */
public interface InterfaceServices {
      public void AddBlog(Blog B);
    public void ModifyBlog(Blog B);
    public void DeleteBlog(int id_blog);
    public List<Blog> DisplayBlog();
    public void update(Blog b,int id) ;
       public List<Blog> Sort();
       
}
