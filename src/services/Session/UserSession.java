/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.Session;

/**
 *
 * @author Louay
 */
public class UserSession {
    public static UserSession CURRENT_USER;
    private AuthResponseDTO user_LoggedIn;

    private UserSession(AuthResponseDTO user_LoggedIn ) {
        this.user_LoggedIn = user_LoggedIn;
    }

    public static UserSession getSameInstance(AuthResponseDTO user_LoggedIn) {
        if(CURRENT_USER == null) {
            CURRENT_USER = new UserSession(user_LoggedIn);
        }
        return CURRENT_USER;
    }

    public AuthResponseDTO getUser_LoggedIn() {
        return user_LoggedIn;
    }

    public void Logout() {
        this.user_LoggedIn = null;
        CURRENT_USER = null;
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "_this_user='" + user_LoggedIn + '\'' +
                ", privileges="  +
                '}';
    }
}
