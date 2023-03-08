/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instalance.UserManagement.entities;

import java.sql.Timestamp;
import java.util.Objects;

/**
 *
 * @author belkn
 */
public class User {
    private int idUser;
    private String username, password, email,role;
    private int isVerified =0;
    private int isBanned =0;
    private Timestamp createdAt = new Timestamp(System.currentTimeMillis());
    private Timestamp updatedAt = new Timestamp(System.currentTimeMillis());
    public User() {
    }

    public User(int id) {
        this.idUser = id;
    }

    public User(String username, String password, String email, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public User(int id, String username, String password, String email, String role) {
        this.idUser = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    } 
    
    

    public int getId() {
        return idUser;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public void setId(int id) {
        this.idUser = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(int isVerified) {
        this.isVerified = isVerified;
    }

    public int getIsBanned() {
        return isBanned;
    }

    public void setIsBanned(int isBanned) {
        this.isBanned = isBanned;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + this.idUser;
        hash = 11 * hash + Objects.hashCode(this.username);
        hash = 11 * hash + Objects.hashCode(this.password);
        hash = 11 * hash + Objects.hashCode(this.email);
        hash = 11 * hash + Objects.hashCode(this.role);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.idUser != other.idUser) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + idUser + ", username=" + username + ", password=" + password + ", email=" + email + ", role=" + role + '}';
    }

    
    
    
    

    
   
}
    
    
    
    
    

