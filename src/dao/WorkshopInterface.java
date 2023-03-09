/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Workshop;
import java.sql.SQLException;
import javafx.collections.ObservableList;

/**
 *
 * @author fatha
 */
public interface WorkshopInterface {
    ObservableList<Workshop> getAllWorkshops() throws SQLException;
    void addWorkshop(Workshop workshop) throws SQLException;
    void updateWorkshop(Workshop workshop) throws SQLException;
    void deleteWorkshop(Workshop workshop) throws SQLException;
    Workshop getById(int id);
}
