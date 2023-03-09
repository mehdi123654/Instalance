/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author emnaa
 */
public class SearchHistory {
    private int searchID, userID,resultCount;
    private List<Integer> offerIds;
    private Timestamp searchDate;
    private String search;

    public SearchHistory(int searchID, int userID, int resultCount, List<Integer> offerIds, Timestamp searchDate, String search) {
        this.searchID = searchID;
        this.userID = userID;
        this.resultCount = resultCount;
        this.offerIds = offerIds;
        this.searchDate = searchDate;
        this.search = search;
    }

    public SearchHistory(int userID, int resultCount, List<Integer> offerIds, Timestamp searchDate, String search) {
        this.userID = userID;
        this.resultCount = resultCount;
        this.offerIds = offerIds;
        this.searchDate = searchDate;
        this.search = search;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    

    public int getSearchID() {
        return searchID;
    }

    public int getUserID() {
        return userID;
    }

    public int getResultCount() {
        return resultCount;
    }

    public List<Integer> getOfferIds() {
        return offerIds;
    }

    public Timestamp getSearchDate() {
        return searchDate;
    }

    public void setSearchID(int searchID) {
        this.searchID = searchID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }

    public void setOfferIds(List<Integer> offerIds) {
        this.offerIds = offerIds;
    }

    public void setSearchDate(Timestamp searchDate) {
        this.searchDate = searchDate;
    }
    
}
