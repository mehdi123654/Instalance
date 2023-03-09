/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;

import entity.Event;
import javafx.collections.ObservableList;

/**
 *
 * @author fatha
 */
public interface EventInterface {
    ObservableList<Event> getAllEvents() throws SQLException;
    void addEvent(Event event) throws SQLException;
    void updateEvent(int event_id,Event event) throws SQLException;
    void deleteEvent(Event event) throws SQLException;
    void deleteEvent(int id) throws SQLException;
    Event getEventById(int id) throws SQLException;
}
