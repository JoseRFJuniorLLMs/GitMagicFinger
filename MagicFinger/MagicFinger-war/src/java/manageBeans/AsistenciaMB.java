package manageBeans;


import entity.Alumno;
import entity.Asignatura;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import sessionBeans.asignaturas.AsignaturasLocal;


@Named(value = "asistenciaMB")
@RequestScoped
public class AsistenciaMB {
    @EJB
    private AsignaturasLocal asignature;
    private List<Asignatura> ListAsignaturas;
    private String NombreAsignatura;
    private int IdAsignatura;
 //   private List<AsignaturaAlumno> alumnosPorAsignatura;
    private String huellaEnString;
    private Date fecha;
    
    public List<String> completeAsignature(String query) {
   //     ListAsignaturas = asignature.ListAsignature();
        List<String> datos = new ArrayList();
        for (Asignatura asignatura : ListAsignaturas) {
            datos.add(asignatura.getNombre());
        }
        return datos;  
    } 
    public void actulizarAlumnos(){
   //     ListAsignaturas = asignature.ListAsignature();
        for (Asignatura asignatura : ListAsignaturas) {
            if( asignatura.getNombre().compareTo(NombreAsignatura)==0 ){
     //           alumnosPorAsignatura = asignature.ListAlumnosPorAsignatura(asignatura);
       //         System.out.println(alumnosPorAsignatura.get(0).getAlumnoId().getNombre());
                return; 
            }
        }
    }
    public void capturoHuella(ActionEvent actionEvent){
        FacesContext context = FacesContext.getCurrentInstance();
        System.out.println("Hello " + huellaEnString);
        //Alumno nuevo = this.asignature.CompareFingerPrint(huellaEnString);
       // if(nuevo!=null){
         //   context.addMessage(null, new FacesMessage("Successful", "Ha ingresado: " + nuevo.getNombre()+" "+nuevo.getApellidop()));
        //}
        //else{
          //  context.addMessage(null, new FacesMessage("Successful", "Usuario no encontrado"));
       //}
    }
    
    public String getNombreAsignatura() {
        return NombreAsignatura;
    }

    public void setNombreAsignatura(String NombreAsignatura) {
        this.NombreAsignatura = NombreAsignatura;
    }
/*
    public List<AsignaturaAlumno> getAlumnosPorAsignatura() {
        return alumnosPorAsignatura;
    }

    public void setAlumnosPorAsignatura(List<AsignaturaAlumno> alumnosPorAsignatura) {
        this.alumnosPorAsignatura = alumnosPorAsignatura;
    }
*/
    public String getHuellaEnString() {
        return huellaEnString;
    }

    public void setHuellaEnString(String huellaEnString) {
        this.huellaEnString = huellaEnString;
    }
    
}
