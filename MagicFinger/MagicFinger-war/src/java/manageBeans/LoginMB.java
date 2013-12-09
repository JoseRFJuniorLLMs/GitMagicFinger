/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package manageBeans;

import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author lol
 */
@Named(value = "loginMB")
@RequestScoped
public class LoginMB {
    @Inject LoginSessionMB session;
    /**
     * Creates a new instance of LoginMB
     */
    private String user;
    private String password;
    
    public LoginMB() {
    }
    
    public void login() throws IOException{
        session.login(user, password);
    }
    
    public void logout() throws IOException{
        session.logout();
    }
    
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
