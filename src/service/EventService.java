/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Event;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import dao.EventInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DataBaseConnection;

/**
 *
 * @author fatha
 */
public class EventService implements EventInterface {

    private Connection connection;
    

    public EventService() {
        
        try {
            this.connection = DataBaseConnection.getInstance().getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
}
        
        
    

    //EventService() {
    //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //}
    @Override
    public ObservableList<Event> getAllEvents() throws SQLException {
        ObservableList<Event> events = FXCollections.observableArrayList();
        String query = "SELECT * FROM event";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int eventId = resultSet.getInt("event_id");
                String eventName = resultSet.getString("event_name");
                String description = resultSet.getString("description");
                Date startDate = resultSet.getDate("start_date");
                Date endDate = resultSet.getDate("end_date");
                String location = resultSet.getString("location");
                int maxAttendees = resultSet.getInt("max_attendees");
                Date registrationDeadline = resultSet.getDate("registrationDeadline");
                Event event = new Event(eventId, eventName, description, startDate, endDate, location, maxAttendees, registrationDeadline);//
                events.add(event);
            }
        }
        return events;
    }

    @Override
    public Event getEventById(int event_id) throws SQLException {
        String query = "SELECT * FROM event WHERE event_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, event_id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int eventId = resultSet.getInt("event_id");
                String eventName = resultSet.getString("event_name");
                String description = resultSet.getString("description");
                Date startDate = resultSet.getDate("start_date");
                Date endDate = resultSet.getDate("end_date");
                String location = resultSet.getString("location");
                int maxAttendees = resultSet.getInt("max_attendees");
                Date registrationDeadline = resultSet.getDate("registrationDeadline");
                return new Event(eventId, eventName, description, startDate, endDate, location, maxAttendees, registrationDeadline);//
            } else {
                return null;
            }
        }
    }

    @Override
    public void addEvent(Event event) throws SQLException {
        String query = "INSERT INTO event ( event_name, description, start_date, end_date, location, max_attendees, registrationDeadline) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, event.getEvent_name());
            statement.setString(2, event.getDescription());
            statement.setDate(3, (Date) event.getStart_date());
            statement.setDate(4, (Date) event.getEnd_date());
            statement.setString(5, event.getLocation());
            statement.setInt(6, event.getMax_attendees());
            statement.setDate(7, (Date) event.getRegistrationDeadline());
            statement.executeUpdate();
        }
    }

    
    @Override
    public void updateEvent(int eventId, Event event) throws SQLException {
        String query = "UPDATE event SET   event_name=?, description=?, start_date=?, end_date=?, location=?, max_attendees=?, registrationDeadline=? WHERE event_id=? ";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, event.getEvent_name());
            statement.setString(2, event.getDescription());
            statement.setDate(3, (Date) event.getStart_date());
            statement.setDate(4, (Date) event.getEnd_date());
            statement.setString(5, event.getLocation());
            statement.setInt(6, event.getMax_attendees());
            statement.setDate(7, (Date) event.getRegistrationDeadline());
            statement.setInt(8, eventId);
            statement.executeUpdate();
            //int rowsAffected = statement.executeUpdate();
            //System.out.println(rowsAffected + " rows updated.");
        
    }}

    @Override
    public void deleteEvent(Event event) throws SQLException {
        String query = "DELETE FROM event WHERE event_id=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, event.getEvent_id());
            statement.executeUpdate();
        }
    }
     @Override
    public void deleteEvent(int id) throws SQLException {
        String query = "DELETE FROM event WHERE event_id=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

}
