/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crossify.entities;
import java.util.Objects;
/**
 *
 * @author emnaa
 */
public class Application {
    private int idApp, idFreelance, idFreelancer, idBO;
    private String FN, LN, EmailF, urlCV;
    private boolean conf, notif;

    public Application(int idApp, int idFreelance, int idFreelancer, int idBO, String FN, String LN, String EmailF, String urlCV, boolean conf, boolean notif) {
        this.idApp = idApp;
        this.idFreelance = idFreelance;
        this.idFreelancer = idFreelancer;
        this.idBO = idBO;
        this.FN = FN;
        this.LN = LN;
        this.EmailF = EmailF;
        this.urlCV = urlCV;
        this.conf = conf;
        this.notif = notif;
    }

    public Application(int idFreelance, int idFreelancer, int idBO, String FN, String LN, String EmailF, String urlCV, boolean conf, boolean notif) {
        this.idFreelance = idFreelance;
        this.idFreelancer = idFreelancer;
        this.idBO = idBO;
        this.FN = FN;
        this.LN = LN;
        this.EmailF = EmailF;
        this.urlCV = urlCV;
        this.conf = conf;
        this.notif = notif;
    }

    public int getIdApp() {
        return idApp;
    }

    public int getIdFreelance() {
        return idFreelance;
    }

    public int getIdFreelancer() {
        return idFreelancer;
    }

    public int getIdBO() {
        return idBO;
    }

    public String getFN() {
        return FN;
    }

    public String getLN() {
        return LN;
    }

    public String getEmailF() {
        return EmailF;
    }

    public String getUrlCV() {
        return urlCV;
    }

    public boolean isConf() {
        return conf;
    }

    public boolean isNotif() {
        return notif;
    }

    public void setIdApp(int idApp) {
        this.idApp = idApp;
    }

    public void setIdFreelance(int idFreelance) {
        this.idFreelance = idFreelance;
    }

    public void setIdFreelancer(int idFreelancer) {
        this.idFreelancer = idFreelancer;
    }

    public void setIdBO(int idBO) {
        this.idBO = idBO;
    }

    public void setFN(String FN) {
        this.FN = FN;
    }

    public void setLN(String LN) {
        this.LN = LN;
    }

    public void setEmailF(String EmailF) {
        this.EmailF = EmailF;
    }

    public void setUrlCV(String urlCV) {
        this.urlCV = urlCV;
    }

    public void setConf(boolean conf) {
        this.conf = conf;
    }

    public void setNotif(boolean notif) {
        this.notif = notif;
    }
    
}
