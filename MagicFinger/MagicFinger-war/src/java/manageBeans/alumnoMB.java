/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package manageBeans;

import com.sun.jersey.core.util.Base64;
import entity.Alumno;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import sessionBeans.AlumnoFacadeLocal;

/**
 *
 * @author chevo
 */
@Named(value = "alumnoMB")
@RequestScoped
public class alumnoMB {
    @EJB
    private AlumnoFacadeLocal alumnoFacade;
    
    
    private String rut;
    private String nombre;
    private String apellidoP;
    private String apellidoM;
    private String huella1;
    private String huella2;
    private String huella1Vista;
    private String huella2Vista;
    private int telefono;
    private String correo;
    
    public void savePerson(ActionEvent actionEvent) {
        if(huella1!=null){
            if(huella2!=null){
                Alumno nuevo = new Alumno();
                nuevo.setNombre(nombre);
                nuevo.setRut(rut);
                nuevo.setApellidop(apellidoP);
                nuevo.setApellidom(apellidoM);
                nuevo.setTelefono(telefono);
                nuevo.setCorreo(correo);

//                byte[] templeByte1 = Base64.decode(huella1);
//                nuevo.setHuella1(templeByte1);
//
//                byte[] templeByte2 = Base64.decode(huella1);
//                nuevo.setHuella2(templeByte2);

            //    alumnoFacade.create(nuevo);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("agregar " + nombre + " " + apellidoP));
            }
            else{
                  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("huella 2 no ingresada"));
            }
        }
        else{
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("huella 1 no ingresada"));
        }
    }   
    
    public void registrarHuella(String nuevaHuella){
        if(huella1!=null){
            huella2 = nuevaHuella;
            huella2Vista = "Registrada";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se ha marcado una huella"));
        }
        else{
            huella1 = nuevaHuella;
            huella2Vista = "Registrada";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se ha marcado una huellaa"));
        }
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

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
}
