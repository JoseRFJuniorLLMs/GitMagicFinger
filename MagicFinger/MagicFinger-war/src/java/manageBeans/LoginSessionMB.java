/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package manageBeans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author lol
 */
@Named(value = "loginSessionMB")
@SessionScoped
public class LoginSessionMB implements Serializable {

    /**
     * Creates a new instance of LoginSessionMB
     */
    
    public LoginSessionMB() {
    }
    
    public void login(String user, String password){
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        try {
            if (request.getRemoteUser() == null) {
                request.login(user, password);  
                
            }
            else {
                //redirectStartPage();
            }
        }
        catch (Exception e) {
            System.out.println("error(loginMB-login): "+e.getMessage());
        }
    }
}
