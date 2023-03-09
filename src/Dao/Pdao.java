/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import java.util.List;
/**
 *
 * @author belhassan
 * @param <Packag>
 */
public interface Pdao<Packag> {
 
    public void insert(Packag p);
    public List<Packag> displayAll();
    public Packag displayById(int id_p);
    public boolean update(int id_p, int p);
  
}

