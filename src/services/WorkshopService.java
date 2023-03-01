/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.WorkshopInterface;
import entities.Event;
import entities.Workshop;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DataBaseConnection;

/**
 *
 * @author fatha
 */
public class WorkshopService implements WorkshopInterface {

    private Connection connection;
    private EventService eventService;

    public WorkshopService() {

        try {
            this.connection = DataBaseConnection.getInstance().getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(WorkshopService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @Override
public ObservableList<Workshop> getAllWorkshops() {
    ObservableList<Workshop> workshops = FXCollections.observableArrayList();
    String sql = "SELECT * FROM workshop JOIN event ON workshop.event_id = event.event_id";
    try {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int eventId = resultSet.getInt("event_id");
            String description = resultSet.getString("description");
            String eventName = resultSet.getString("event_name");
            Date startDate = resultSet.getDate("start_date");
            Date endDate = resultSet.getDate("end_date");
            String location = resultSet.getString("location");
            int maxAttendees = resultSet.getInt("max_attendees");
            Date registrationDeadline = resultSet.getDate("registrationDeadline");
            String agenda = resultSet.getString("agenda");

            Workshop workshop = new Workshop(eventId,  eventName, description, startDate, endDate, location, maxAttendees, registrationDeadline, agenda);
            workshops.add(workshop);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return workshops;
}


    @Override
    public void addWorkshop(Workshop workshop) {
        String insertEventSql = "INSERT INTO event (description, event_name, start_date, end_date, location, max_attendees, registrationDeadline) VALUES (?, ?, ?, ?, ?, ?, ?)";

        String insertWorkshopSql = "INSERT INTO workshop (event_id, agenda) VALUES (?, ?)";

        try {
            // Insert data into event table
            PreparedStatement eventStatement = connection.prepareStatement(insertEventSql, Statement.RETURN_GENERATED_KEYS);
            eventStatement.setString(1, workshop.getDescription());
            eventStatement.setString(2, workshop.getEvent_name());
            eventStatement.setDate(3, (Date) workshop.getStart_date());
            eventStatement.setDate(4, (Date) workshop.getEnd_date());
            eventStatement.setString(5, workshop.getLocation());
            eventStatement.setInt(6, workshop.getMax_attendees());
            eventStatement.setDate(7, (Date) workshop.getRegistrationDeadline());

            eventStatement.executeUpdate();

            // Get the generated event_id value
            ResultSet generatedKeys = eventStatement.getGeneratedKeys();
            int event_id = 0;
            if (generatedKeys.next()) {
                event_id = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Failed to get generated event_id value.");
            }

            // Insert additional data into workshop table
            PreparedStatement workshopStatement = connection.prepareStatement(insertWorkshopSql);
            workshopStatement.setInt(1, event_id);
            workshopStatement.setString(2, workshop.getAgenda());

            workshopStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*@Override
    public void updateWorkshop(Workshop workshop) {
        String updateSql = "UPDATE event SET description = ?, event_name = ?, start_date = ?, end_date = ?, location = ?, max_attendees = ?, registrationDeadline = ? WHERE event_id = ?";
        String updateWorkshopSql = "UPDATE workshop SET  agenda = ? WHERE event_id = ?";
        try {
            // update the event table
            PreparedStatement statement = connection.prepareStatement(updateSql);
            statement.setString(1, workshop.getDescription());
            statement.setString(2, workshop.getEvent_name());
            statement.setDate(3, (Date) workshop.getStart_date());
            statement.setDate(4, (Date) workshop.getEnd_date());
            statement.setString(5, workshop.getLocation());
            statement.setInt(6, workshop.getMax_attendees());
            statement.setDate(7, (Date) workshop.getRegistrationDeadline());
            statement.setInt(8, workshop.getEvent_id());
            statement.executeUpdate();

            // update the workshop table
            PreparedStatement workshopStatement = connection.prepareStatement(updateWorkshopSql);
            workshopStatement.setString(1, workshop.getAgenda());
            workshopStatement.setInt(2, workshop.getEvent_id());
            workshopStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
    @Override
public void updateWorkshop(Workshop workshop) {
    String updateSql = "UPDATE event SET description = ?, event_name = ?, start_date = ?, end_date = ?, location = ?, max_attendees = ?, registrationDeadline = ? WHERE event_id = ?";
    String updateWorkshopSql = "UPDATE workshop SET  agenda = ? WHERE event_id = ?";
    try (PreparedStatement statement = connection.prepareStatement(updateSql);
         PreparedStatement workshopStatement = connection.prepareStatement(updateWorkshopSql)) {
        // update the event table
        statement.setString(1, workshop.getDescription());
        statement.setString(2, workshop.getEvent_name());
        statement.setObject(3, workshop.getStart_date());
        statement.setObject(4, workshop.getEnd_date());
        statement.setString(5, workshop.getLocation());
        statement.setInt(6, workshop.getMax_attendees());
        statement.setObject(7, workshop.getRegistrationDeadline());
        statement.setInt(8, workshop.getEvent_id());
        statement.executeUpdate();

        // update the workshop table
        workshopStatement.setString(1, workshop.getAgenda());
        workshopStatement.setInt(2, workshop.getEvent_id());
        workshopStatement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    

    @Override
    public void deleteWorkshop(Workshop workshop) throws SQLException {
        String query = "DELETE FROM event WHERE event_id=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, workshop.getEvent_id());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Workshop getById(int id) {
        String sql = "SELECT * FROM workshop WHERE event_id = ?";
        Workshop workshop = null;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int eventId = resultSet.getInt("event_id");
                Event event = eventService.getEventById(eventId);
                String agenda = resultSet.getString("agenda");
                workshop = new Workshop(event.getEvent_id(), event.getDescription(), event.getEvent_name(),
                        event.getStart_date(), event.getEnd_date(), event.getLocation(),
                        event.getMax_attendees(), event.getRegistrationDeadline(), agenda);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return workshop;
    }

}
