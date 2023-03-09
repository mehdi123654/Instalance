/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.Session;

/**
 *
 * @author Louay
 */
public class AuthResponseDTO {
    private int idUser;
    private String username;  
    private String email;
    private String role;   
    private String password;
    private Boolean isBanned;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getIsBanned() {
        return isBanned;
    }

    public void setIsBanned(Boolean isBanned) {
        this.isBanned = isBanned;
    }

    @Override
    public String toString() {
        return "AuthResponseDTO{" + "idUser=" + idUser + ", username=" + username + ", email=" + email + ", role=" + role + ", password=" + password + ", isBanned=" + isBanned + '}';
    }
    
    
}
