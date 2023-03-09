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
public class Hackathon extends Event{
    private String prizes;
    private Date submissionDeadline;

    public Hackathon(int event_id, String event_name,String description,  Date start_date, Date end_date, String location, int max_attendees, Date registrationDeadline, Date submissionDeadline ,  String prizes) {
        super(event_id,  event_name,description, start_date, end_date, location, max_attendees, registrationDeadline);
        this.prizes = prizes;
        this.submissionDeadline = submissionDeadline;
    }

    public Hackathon(String event_name,String description,  Date start_date, Date end_date, String location, int max_attendees, Date registrationDeadline, Date submissionDeadline, String prizes) {
        super( event_name,description, start_date, end_date, location, max_attendees, registrationDeadline);
        this.prizes = prizes;
        this.submissionDeadline = submissionDeadline;
    }

    

    public String getPrizes() {
        return prizes;
    }

    public void setPrizes(String prizes) {
        this.prizes = prizes;
    }

    public java.sql.Date getSubmissionDeadline() {
        return submissionDeadline;
    }

    public void setSubmissionDeadline(Date submissionDeadline) {
        this.submissionDeadline = submissionDeadline;
    }

    
}
