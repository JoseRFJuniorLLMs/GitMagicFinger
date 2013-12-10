/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package manageBeans;

import entity.Alumno;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import sessionBeans.AlumnoFacadeLocal;
import sessionBeans.alumnos.alumnosLocal;

/**
 *
 * @author chevo
 */
@Named(value = "buscaAlumnoMB")
@RequestScoped
public class buscaAlumnoMB {

    @EJB
    private alumnosLocal alumnos;
    private String HuellaEnString;

    public void buscaPersona(ActionEvent actionEvent) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("comparando la huella " + HuellaEnString));
        Alumno encontrado = alumnos.CompareFingerPrint(HuellaEnString);
        if (encontrado != null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Alumno " + encontrado.getNombre()));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Alumno no encontrado"));
        }
    }

    public String getHuellaEnString() {
        return HuellaEnString;
    }

    public void setHuellaEnString(String HuellaEnString) {
        this.HuellaEnString = HuellaEnString;
    }
}
