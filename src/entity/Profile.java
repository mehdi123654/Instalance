/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Louay
 */
public class Profile {
    private int idProfile;
    private String firstName;
    private String lastName;
    private String aboutMe;
    private int phoneNumber;
    
    public Profile(int idProfile) {
        this.idProfile = idProfile;
    }

    public Profile(int idProfile, String firstName, String lastName, String aboutMe, int phoneNumber) {
        this.idProfile = idProfile;
        this.firstName = firstName;
        this.lastName = lastName;
        this.aboutMe = aboutMe;
        this.phoneNumber = phoneNumber;
    }

    public Profile(String firstName, String lastName, String aboutMe, int phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.aboutMe = aboutMe;
        this.phoneNumber = phoneNumber;
    }

    public int getIdProfile() {
        return idProfile;
    }

    public void setIdProfile(int idProfile) {
        this.idProfile = idProfile;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Profile{" + "idProfile=" + idProfile + ", firstName=" + firstName + ", lastName=" + lastName + ", aboutMe=" + aboutMe + ", phoneNumber=" + phoneNumber + '}';
    }
    
    

    
    
    
}

