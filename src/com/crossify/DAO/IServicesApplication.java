/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crossify.DAO;

import com.crossify.entities.Application;
import com.crossify.entities.Freelance;
import javafx.collections.ObservableList;

/**
 *
 * @author emnaa
 */
public interface IServicesApplication {
    public void addApplication(Application a);
    public int nbApplicants(int idOffer);
    public void incrementNB(int idOffer);
    public ObservableList<Application> displayById(int id);
    public void setConfirmed(int idFreelancer, boolean confirmed);
    public void setNotified(int idFreelancer, boolean notified);
    public void sendToPython(int idFreelancer);
}
