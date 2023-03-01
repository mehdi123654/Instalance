/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.HackathonInterface;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import entities.Event;
import entities.Hackathon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataBaseConnection;

/**
 *
 * @author fatha
 */
public class HackathonService implements HackathonInterface {

    private  Connection connection;
    private  EventService eventService;

    public HackathonService() {

        try {
            this.connection = DataBaseConnection.getInstance().getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(HackathonService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    
    @Override
public ObservableList<Hackathon> getAllHackathons() {
    ObservableList<Hackathon> hackathons = FXCollections.observableArrayList();
    String sql = "SELECT * FROM hackathon JOIN event ON hackathon.event_id = event.event_id";
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
            Date submissionDeadline = resultSet.getDate("submissionDeadline");
            String prizes = resultSet.getString("prizes");

            Hackathon hackathon = new Hackathon(eventId, eventName,description, startDate, endDate, location, maxAttendees, registrationDeadline, submissionDeadline, prizes);
            hackathons.add(hackathon);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return hackathons;
}

    @Override
    public Hackathon getHackathonById(int id) {
        String sql = "SELECT * FROM hackathon WHERE event_id = ?";
        Hackathon hackathon = null;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int eventId = resultSet.getInt("event_id");
                Event event = eventService.getEventById(eventId);

                Date submissionDeadline = resultSet.getDate("submissionDeadline");
                String prizes = resultSet.getString("prizes");

                hackathon = new Hackathon(event.getEvent_id(), event.getDescription(), event.getEvent_name(),
                        event.getStart_date(), event.getEnd_date(), event.getLocation(),
                        event.getMax_attendees(), event.getRegistrationDeadline(), submissionDeadline, prizes);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hackathon;
    }

    /*@Override
public void addHackathon(Hackathon hackathon) {
    String insertSql = "INSERT INTO hackathon (event_id, submissionDeadline, prizes) VALUES (?, ?, ?)";
    try {
        PreparedStatement statement = connection.prepareStatement(insertSql);
        statement.setInt(1, hackathon.getEvent_id());
        statement.setDate(2, new java.sql.Date(hackathon.getSubmissionDeadline().getTime()));
        statement.setString(3, hackathon.getPrizes());

        statement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}*/
    @Override
    public void addHackathon(Hackathon hackathon) {
        String insertEventSql = "INSERT INTO event ( event_name, description, start_date, end_date, location, max_attendees, registrationDeadline) VALUES (?, ?, ?, ?, ?, ?, ?)";

        String insertHackathonSql = "INSERT INTO hackathon (event_id, submissionDeadline, prizes) VALUES (?, ?, ?)";

        try {
            // Insert data into event table
            PreparedStatement eventStatement = connection.prepareStatement(insertEventSql, Statement.RETURN_GENERATED_KEYS);
            eventStatement.setString(1, hackathon.getEvent_name());
            eventStatement.setString(2, hackathon.getDescription());
            eventStatement.setDate(3, (Date) hackathon.getStart_date());
            eventStatement.setDate(4, (Date) hackathon.getEnd_date());
            eventStatement.setString(5, hackathon.getLocation());
            eventStatement.setInt(6, hackathon.getMax_attendees());
            eventStatement.setDate(7, (Date) hackathon.getRegistrationDeadline());

            eventStatement.executeUpdate();

            // Get the generated event_id value
            ResultSet generatedKeys = eventStatement.getGeneratedKeys();
            int event_id = 0;
            if (generatedKeys.next()) {
                event_id = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Failed to get generated event_id value.");
            }

            // Insert additional data into hackathon table
            PreparedStatement hackathonStatement = connection.prepareStatement(insertHackathonSql);
            hackathonStatement.setInt(1, event_id);
            hackathonStatement.setDate(2, (Date) hackathon.getSubmissionDeadline());
            hackathonStatement.setString(3, hackathon.getPrizes());

            hackathonStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*@Override
    public void updateHackathon(Hackathon hackathon) {
        String updateSql = "UPDATE hackathon SET submissionDeadline = ?, prizes = ? WHERE event_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(updateSql);
            statement.setDate(1, (java.sql.Date) hackathon.getSubmissionDeadline());
            statement.setString(2, hackathon.getPrizes());
            statement.setInt(3, hackathon.getEvent_id());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
    @Override
    public void updateHackathon(Hackathon hackathon) {
        String updateSql = "UPDATE event SET  event_name = ?, description = ?, start_date = ?, end_date = ?, location = ?, max_attendees = ?, registrationDeadline = ? WHERE event_id = ?";
        String updateHackathonSql = "UPDATE hackathon SET submissionDeadline = ?, prizes = ? WHERE event_id = ?";
        try {
            // update the event table
            PreparedStatement statement = connection.prepareStatement(updateSql);
            statement.setString(1, hackathon.getEvent_name());
            statement.setString(2, hackathon.getDescription());
            statement.setDate(3, (Date) hackathon.getStart_date());
            statement.setDate(4, (Date) hackathon.getEnd_date());
            statement.setString(5, hackathon.getLocation());
            statement.setInt(6, hackathon.getMax_attendees());
            statement.setDate(7, (Date) hackathon.getRegistrationDeadline());
            statement.setInt(8, hackathon.getEvent_id());
            statement.executeUpdate();

            // update the hackathon table
            PreparedStatement hackathonStatement = connection.prepareStatement(updateHackathonSql);
            hackathonStatement.setDate(1, (Date) hackathon.getSubmissionDeadline());
            hackathonStatement.setString(2, hackathon.getPrizes());
            hackathonStatement.setInt(3, hackathon.getEvent_id());
            hackathonStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*@Override
    public void deleteHackathon(Hackathon hackathon) {
        String deleteSql = "DELETE FROM hackathon WHERE event_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(deleteSql);
            statement.setInt(1, hackathon.getEvent_id());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
    @Override
    public void deleteHackathon(Hackathon hackathon) throws SQLException {
        String query = "DELETE FROM event WHERE event_id=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, hackathon.getEvent_id());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
