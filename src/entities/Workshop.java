/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author fatha
 */
public class Workshop extends Event{
    private String agenda;

    public Workshop(int event_id, String description, String event_name, Date start_date, Date end_date, String location, int max_attendees, Date registrationDeadline, String agenda) {
        super(event_id, description, event_name, start_date, end_date, location, max_attendees, registrationDeadline);
        this.agenda = agenda;
    }

    public Workshop( String description, String event_name, Date start_date, Date end_date, String location, int max_attendees, Date registrationDeadline,String agenda) {
        super(description, event_name, start_date, end_date, location, max_attendees, registrationDeadline);
        this.agenda = agenda;
    }

    public String getAgenda() {
        return agenda;
    }

    public void setAgenda(String agenda) {
        this.agenda = agenda;
    }
    
    
}
