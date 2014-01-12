/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package manageBeans;

import entity.Semestre;
import java.util.Date;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import sessionBeans.SemestreFacadeLocal;
/**
 *
 * @author Nacho
 */
@Named(value = "funcionesMB")
@RequestScoped
public class funcionesMB {
    @EJB
    private SemestreFacadeLocal semestreFacade;

    /**
     * Creates a new instance of funcionesMB
     */
    public funcionesMB() {
    }
    public Semestre buscaSemestre(Date fecha){
        List<Semestre> nuevo = semestreFacade.findAll();
        for (Semestre semestre : nuevo) {
            if(semestre.getFechaInicio().after(fecha) && semestre.getFechaTermino().before(fecha) ){
                return semestre;
            }
        }
       return null;
    }
    
    public void validarNombre(FacesContext arg0, UIComponent arg1, Object texto) throws ValidatorException {
        Pattern p = Pattern.compile("[0-9]+");
        Pattern pa = Pattern.compile("\\s+");
        Matcher t = p.matcher((String)texto);
        Matcher s = pa.matcher((String)texto);
        if (((String)texto).length() == 0 || s.matches()) throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validación", "El campo no puede ser vacío"));
        if(t.matches()) throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validación", "El campo no puede estar compuesto solo de números"));    
    }
    
    public void validarRut(FacesContext arg0, UIComponent arg1, Object rut) throws ValidatorException {
        Pattern r = Pattern.compile("\\b\\d{1,8}\\-[K|k|0-9]{1}");
        Matcher m = r.matcher((String)rut);
        if(!m.matches()) throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validación", "El formato del rut no es correcto"));
        else{
            boolean validacion = false;
            String Rut= (String)rut;
            Rut =  Rut.toUpperCase();
            Rut = Rut.replace(".", "");  
            Rut = Rut.replace("-", "");  
            int rutAux = Integer.parseInt(Rut.substring(0, Rut.length() - 1));  
            char dv = Rut.charAt(Rut.length() - 1);  
            int mm = 0, s = 1;  
            for (; rutAux != 0; rutAux /= 10) 
            s = (s + rutAux % 10 * (9 - mm++ % 6)) % 11;  
            if (dv == (char) (s != 0 ? s + 47 : 75)) validacion = true;  
            if(!validacion) throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validación", "Rut inválido"));
        }
    }    
    public void validarNumero(FacesContext arg0, UIComponent arg1, Object texto) throws ValidatorException {
        Pattern p = Pattern.compile("[0-9]+");
        Matcher n = p.matcher((String)texto);
        if (!n.matches()) throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validación", "Debe ingresar un número entero positivo"));    
    }
    
    public void validarCorreo(FacesContext arg0, UIComponent arg1, Object texto) throws ValidatorException {
        Pattern p = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher n = p.matcher((String)texto);
        if (!n.matches()) throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validación", "Debe ingresar un e-mail válido"));    
    }  
}
