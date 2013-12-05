/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package manageBeans;

import entity.Alumno;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import sessionBeans.AlumnoFacadeLocal;

/**
 *
 * @author chevo
 */
@Named(value = "alumnoMB")
@ApplicationScoped
public class AlumnoMB {
    @EJB
    private AlumnoFacadeLocal alumnoFacade;
    
    private String rut;
    private String nombre;  
    private String apellidoP;
    private String apellidoM;
    private Integer telefono;
    private String correo;

    public void savePerson(ActionEvent actionEvent) {  
        Alumno nuevo = new Alumno();
        nuevo.setRut(rut);
        nuevo.setNombre(nombre);
        nuevo.setApellidop(apellidoP);
        nuevo.setApellidom(apellidoM);
        nuevo.setTelefono(telefono);
        nuevo.setCorreo(correo);
        alumnoFacade.create(nuevo); 
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se ha registrado a " + nombre + " " + apellidoP + " " + apellidoM));  
    } 

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoP() {
        return apellidoP;
    }

    public void setApellidoP(String apellidoP) {
        this.apellidoP = apellidoP;
    }

    public String getApellidoM() {
        return apellidoM;
    }

    public void setApellidoM(String apellidoM) {
        this.apellidoM = apellidoM;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
