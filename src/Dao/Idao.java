/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

import entity.Packag;

/**
 *
 * @author belhassan
 * @param <Service>
 */


public interface Idao<Service> {
     public List<Packag> inj(Service s);
    public void insert(Service s);
    public int getSId();
    public void delete(Service s);
    public List<Service> displayAll();
    public Service displayById(int id_p);
    
    public boolean update(int id ,String n , String d,int p,String f);
    
}

    

