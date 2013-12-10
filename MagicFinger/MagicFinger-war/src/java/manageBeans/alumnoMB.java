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
    private String huellaEnString1;
    private String huellaEnString2;
    private int telefono;
    private String correo;

    public void savePerson(ActionEvent actionEvent) {

        if (huellaEnString1.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Huella 1 vacía"));
        } else {
            if (huellaEnString2.isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Huella 2 vacía"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("necesito agregar a" + nombre + " " + apellidoP));

                Alumno nuevo = new Alumno();
                nuevo.setNombre(nombre);
                nuevo.setRut(rut);
                nuevo.setApellidop(apellidoP);
                nuevo.setApellidom(apellidoM);
                nuevo.setTelefono(telefono);
                nuevo.setCorreo(correo);
                byte[] templeByte1 = Base64.decode(huellaEnString1);
                nuevo.setHuella1(templeByte1);

                byte[] templeByte2 = Base64.decode(huellaEnString2);
                nuevo.setHuella2(templeByte2);
                alumnoFacade.create(nuevo);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se haagregar " + nombre + " " + apellidoP));
            }
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

    public String getHuellaEnString1() {
        return huellaEnString1;
    }

    public void setHuellaEnString1(String huellaEnString1) {
        this.huellaEnString1 = huellaEnString1;
    }

    public String getHuellaEnString2() {
        return huellaEnString2;
    }

    public void setHuellaEnString2(String huellaEnString2) {
        this.huellaEnString2 = huellaEnString2;
    }
}
