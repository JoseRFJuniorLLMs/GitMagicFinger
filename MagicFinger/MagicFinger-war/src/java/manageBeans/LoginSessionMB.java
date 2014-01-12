/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package manageBeans;

import entity.Alumno;
import entity.Curso;
import entity.Profesor;
import entity.User;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import sessionBeans.ProfesorFacadeLocal;
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
    @Inject
    LoginMB login;
    
    private Profesor profesor;
    private Alumno alumno;
    private Curso curso;
    private int IdUniversidad;
    private String usuario;     
    
    public LoginSessionMB() {
    }

    public int getIdUniversidad() {
        return IdUniversidad;
    }

    public void setIdUniversidad(int IdUniversidad) {
        this.IdUniversidad = IdUniversidad;
    }

    
    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public void login(String user, String password) throws IOException {
        usuario = user;
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        try {
            if (request.getRemoteUser() == null) {
                request.login(user, password);
                redireccionar(context.getExternalContext().getUserPrincipal().getName());
            } else {
                FacesContext context1 = FacesContext.getCurrentInstance();
                HttpSession session = (HttpSession) context1.
                getExternalContext().getSession(false);
                session.invalidate();
                request.login(user, password);
                redireccionar(context.getExternalContext().getUserPrincipal().getName());
                // System.out.println(request.getContextPath()+"------------------------------------"+context.getExternalContext().getUserPrincipal());
            }
        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de inicio", "Usuario o contraseña invalidos"));   
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

    private void redireccionar(String nombre) throws IOException {
        ExternalContext context2 = FacesContext.getCurrentInstance().getExternalContext();

        User usuario = usuarios.getRol(nombre);
        switch (usuario.getUserrolName().getName()) {
            case "Administrador":
                context2.redirect(context2.getRequestContextPath() + "/faces/administrador/index.xhtml");
                break;
            case "Alumno":
                alumno = usuario.getAluIdAlumno();
                System.out.println(alumno.getNombre());
                context2.redirect(context2.getRequestContextPath() + "/faces/alumno/index.xhtml");
                break;
            case "Profesor":
                profesor = usuario.getProIdProfesor();
                System.out.println(profesor.getNombre());
                context2.redirect(context2.getRequestContextPath() + "/faces/profesor/index.xhtml");
                break;
           
            case "Secretaria":
                context2.redirect(context2.getRequestContextPath() + "/faces/secretaria/index.xhtml");
                break;
        }

    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
}
