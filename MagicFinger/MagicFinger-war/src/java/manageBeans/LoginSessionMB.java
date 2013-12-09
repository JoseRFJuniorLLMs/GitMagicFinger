/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package manageBeans;

import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import sessionBeans.usuarios.UsuariosLocal;

/**
 *
 * @author lol
 */
@Named(value = "loginSessionMB")
@SessionScoped
public class LoginSessionMB implements Serializable {
    @EJB
    private UsuariosLocal usuarios;

    /**
     * Creates a new instance of LoginSessionMB
     */
    
    public LoginSessionMB() {
    }
    
    public void login(String user, String password) throws IOException{
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        try {
            if (request.getRemoteUser() == null) {
                request.login(user, password);  
                redireccionar(context.getExternalContext().getUserPrincipal().getName());

            }
            else {
                redireccionar(context.getExternalContext().getUserPrincipal().getName());
               // System.out.println(request.getContextPath()+"------------------------------------"+context.getExternalContext().getUserPrincipal());
            }
        }
        catch (ServletException e) {
            System.out.println("error(loginMB-login): "+e.getMessage());
        }
    }
    private boolean error = false;


  public void logout() throws IOException {
    FacesContext context = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession) context.  
    getExternalContext().getSession(false);
    session.invalidate();
    ExternalContext context2 = FacesContext.getCurrentInstance().getExternalContext();
    context2.redirect(context2.getRequestContextPath() + "/faces/login.xhtml");
   }

  public boolean isError() {
   return error;
  }

  public void setError(boolean error) {
   this.error = error;
  }

    private void redireccionar( String nombre) throws IOException {
        
        ExternalContext context2 = FacesContext.getCurrentInstance().getExternalContext();
        String rol = usuarios.getRol(nombre);
        switch (rol) {
            case "Administrador":
                context2.redirect(context2.getRequestContextPath() + "/faces/administrador/index.xhtml");
                break;
            case "Profesor":
                context2.redirect(context2.getRequestContextPath() + "/faces/profesor/index.xhtml");
                break;
            case "Alumno":
                context2.redirect(context2.getRequestContextPath() + "/faces/alumno/index.xhtml");
                break;
            case "Secretaria":
                context2.redirect(context2.getRequestContextPath() + "/faces/secretaria/index.xhtml");
                break;
        }
        
    }

}
