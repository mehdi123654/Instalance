/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;

import entity.Hackathon;
import javafx.collections.ObservableList;

/**
 *
 * @author fatha
 */
public interface HackathonInterface {
    ObservableList<Hackathon> getAllHackathons() throws SQLException;
    void addHackathon(Hackathon hackathon) throws SQLException;
    void updateHackathon(Hackathon hackathon) throws SQLException;
    void deleteHackathon(Hackathon hackathon) throws SQLException;
    Hackathon getHackathonById(int id);

}
