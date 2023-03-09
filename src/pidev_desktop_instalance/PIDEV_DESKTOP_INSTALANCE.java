/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_desktop_instalance;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author fatha
 */
public class PIDEV_DESKTOP_INSTALANCE extends Application {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    /*public static void main(String[] args) {
        try {
            try (Connection conn = DataBaseConnection.getConnection()) {
                System.out.println("Connection successful!");
                EventService eventService = new EventService(conn);
                HackathonService hackathonService = new HackathonService(conn);
                
// Add the event to the database
                Event event = new Event("jdid", "This is a new event", Date.valueOf("2023-03-01"), Date.valueOf("2023-03-31"), "New Location", 50, Date.valueOf("2023-02-28"));//*/
//Event event = new Event("a", "Hackathon 1", sqlDate, sqlDate, "San Francisco", 100, sqlDate);
//Event event = new Event("a", "Hackathon 1", new Date(), new Date(), "San Francisco", 100, new Date());
    //eventService.addEvent(event);
    //System.out.println("Event added!!");
// Retrieve the event from the database
    /*Event event2 = eventService.getEventById(4);
                Event event3 = new Event("Modified event", "haha", Date.valueOf("2006-03-06"),Date.valueOf("2006-03-06"),"idk",150,Date.valueOf("2006-03-06"));
                eventService.updateEvent(4,event3);
                System.out.println("Updated");*/
    

// Add the hackathon to the database
    //Hackathon hackathon = new Hackathon(11,"hackathon jdid","hackathon meghir event", Date.valueOf("2001-10-20"),  Date.valueOf("2001-10-20"), "morneg", 69, Date.valueOf("2001-10-20"),  Date.valueOf("2001-10-20"), "yes");
    //hackathonService.addHackathon(hackathon);
    //hackathon=new Hackathon(4,"hackathon jdid jdid","hackathon tbdadel", Date.valueOf("2001-03-24"),  Date.valueOf("2001-03-24"), "mornegia", 79, Date.valueOf("2001-03-24"),  Date.valueOf("2001-03-24"), "no");
    //hackathonService.updateHackathon(hackathon);
    //hackathonService.deleteHackathon(hackathon);
    //System.out.println("Successfully deleted" + hackathon.getEvent_name() + " hackathon");
    //eventService.deleteEvent(event2);
    //}
    //} catch (SQLException e) {
    //System.out.println("Connection failed: " + e.getMessage());
    //}
    //}
    @Override
public void start(Stage primaryStage) throws Exception{
    FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/User.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    primaryStage.setScene(scene);
    primaryStage.show();
}

    public static void main(String[] args) {
        launch(args);
    }
}
