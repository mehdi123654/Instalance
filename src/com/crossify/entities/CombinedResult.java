/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crossify.entities;

import java.sql.Timestamp;

/**
 *
 * @author emnaa
 */
public class CombinedResult {
    private int idUser;
    private String search;
    private String resultIds;
    private String category;
    private String description;
    private Timestamp addDate;

    public CombinedResult() {
    }
    
    

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getResultIds() {
        return resultIds;
    }

    public void setResultIds(String resultIds) {
        this.resultIds = resultIds;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getAddDate() {
        return addDate;
    }

    public void setAddDate(Timestamp addDate) {
        this.addDate = addDate;
    }

    
}
