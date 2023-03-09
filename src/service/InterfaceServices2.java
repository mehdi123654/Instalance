/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import java.util.List;

import entity.Comment;
/**
 *
 * @author zeinab
 */
public interface InterfaceServices2 {
    public void AddComment(Comment C);
    public void ModifyComment(Comment C);
    public void DeleteComment(int id_Comment);
    public List<Comment> DisplayComment();
}
