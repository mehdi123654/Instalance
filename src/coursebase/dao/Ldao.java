/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursebase.dao;

import coursebase.entity.Lesson;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author user
 * @param <Lesson>
 */
public interface Ldao<Lesson> {
       public void insert(Lesson o);
 public void deleteall(int[] myArray);
    public void delete(int id);
 public List<Lesson> displayAll2();
    public List<Lesson> displayAll(int i);

    public Lesson displayById(int id);

    public boolean update(Lesson os);
}
