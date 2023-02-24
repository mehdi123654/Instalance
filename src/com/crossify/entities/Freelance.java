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
public class Freelance {

    private int id_F, BO_id;
    private String BO_email, category_F, description;
    private float budget;
    private boolean state_F;

    public Freelance() {
    }

    public Freelance(int id_F, int BO_id, String BO_email, String category_F, String description, float budget, boolean state_F) {
        this.id_F = id_F;
        this.BO_id = BO_id;
        this.BO_email = BO_email;
        this.category_F = category_F;
        this.description = description;
        this.budget = budget;
        this.state_F = state_F;
    }

    public Freelance(int BO_id, String BO_email, String category_F, String description, float budget, boolean state_F) {
        this.BO_id = BO_id;
        this.BO_email = BO_email;
        this.category_F = category_F;
        this.description = description;
        this.budget = budget;
        this.state_F = state_F;
    }

    public Freelance(int id_F, String BO_email, String category_F, String description) {
        this.id_F = id_F;
        this.BO_email = BO_email;
        this.category_F = category_F;
        this.description = description;
       
    }
    
    
    public int getId_F() {
        return id_F;
    }

    public int getBO_id() {
        return BO_id;
    }

    public String getBO_email() {
        return BO_email;
    }

    public String getCategory_F() {
        return category_F;
    }

    public String getDescription() {
        return description;
    }

    public float getBudget() {
        return budget;
    }

    public boolean isState_F() {
        return state_F;
    }

    public void setId_F(int id_F) {
        this.id_F = id_F;
    }

    public void setBO_id(int BO_id) {
        this.BO_id = BO_id;
    }

    public void setBO_email(String BO_email) {
        this.BO_email = BO_email;
    }

    public void setCategory_F(String category_F) {
        this.category_F = category_F;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBudget(float budget) {
        this.budget = budget;
    }

    public void setState_F(boolean state_F) {
        this.state_F = state_F;
    }

    @Override
    public String toString() {
        return "BO_id=" + BO_id + ", BO_email=" + BO_email + ", category_F=" + category_F + ", description=" + description + ", budget=" + budget + ", state_F" + state_F;
    }
   
}
