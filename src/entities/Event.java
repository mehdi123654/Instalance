/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;
import java.sql.Date;
import java.time.LocalDate;
//import java.util.List;
//import java.util.Objects;
/**
 *
 * @author fatha
 */
public class Event {
    private int event_id;
    private String description;
    private String event_name;
    private Date start_date;  // new attribute
    private Date end_date;
    private String location;
    //private int user_id;
    private int max_attendees;
    private Date registrationDeadline;
    //private List<User> participants;

    public Event(int event_id,  String event_name,String description, Date start_date, Date end_date, String location, int max_attendees, Date registrationDeadline) { 
        this.event_id = event_id;
        this.event_name = event_name;
        this.description = description;
        this.start_date = start_date;
        this.end_date = end_date;
        this.location = location;
        this.max_attendees = max_attendees;
        this.registrationDeadline = registrationDeadline;
    }

    public Event(String event_name,String description,  Date start_date, Date end_date, String location, int max_attendees, Date registrationDeadline) {
        this.event_name = event_name;
        this.description = description;
        this.start_date = start_date;
        this.end_date = end_date;
        this.location = location;
        this.max_attendees = max_attendees;
        this.registrationDeadline = registrationDeadline;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public java.sql.Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public java.sql.Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getMax_attendees() {
        return max_attendees;
    }

    public void setMax_attendees(int max_attendees) {
        this.max_attendees = max_attendees;
    }

    public java.sql.Date getRegistrationDeadline() {
        return registrationDeadline;
    }

    public void setRegistrationDeadline(Date registrationDeadline) {
        this.registrationDeadline = registrationDeadline;
    }

    /*public java.sql.Date getStartDate() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return this.start_date;
    }

    public java.sql.Date getEn_date() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return this.end_date;
    }*/
    
    
    /*public java.sql.Date getRegistrationDeadline() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
    
}
