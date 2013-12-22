package manageBeans;

import entity.Alumno;
import entity.AlumnosDelCurso;
import entity.Asistencia;
import entity.BloqueClase;
import entity.Curso;
import entity.Profesor;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.event.SelectEvent;
import sessionBeans.AsistenciaFacadeLocal;
import sessionBeans.ProfesorFacadeLocal;
import sessionBeans.alumnos.alumnosLocal;
import sessionBeans.asignaturas.asistenciaSBLocal;

/**
 *
 * @author chevo
 */
@Named(value = "asistenciaCursoMB")
@RequestScoped
public class asistenciaCursoMB {

    @EJB
    private asistenciaSBLocal asistenciaSB;
    @EJB
    private AsistenciaFacadeLocal asistenciaFacade;
    @EJB
    private ProfesorFacadeLocal profesorFacade;
    @EJB
    private alumnosLocal alumnos;
    private String HuellaEnString;
    private Profesor profesor;
    private List<Curso> ListCurso;
    private Curso curso;
    private BloqueClase bloqueClase;
    private int valor;
    private Date fecha;
    private Map<String, String> bloques = new HashMap<>();
    private List<BloqueClase> bloqueClaseList;

    @PostConstruct
    public void init() {
        System.out.println("entro aki");
        
        if (fecha == null) {
            fecha = new Date();
        }
        profesor = profesorFacade.find(1);
        if (profesor != null) {
            ListCurso = profesor.getCursoList();
            if (!ListCurso.isEmpty()) {
                curso = ListCurso.get(0);
                bloqueClaseList = curso.getBloqueClaseList();

            }
        }
    }

    public void buscaPersona(ActionEvent actionEvent) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("comparando la huella " + HuellaEnString));
        Alumno encontrado = alumnos.CompareFingerPrint(HuellaEnString);
        if (encontrado != null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ALUMNO ENCONTRADO", encontrado.getNombre()));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "alumno no encontrado"));

        }
    }

    public void agregaPersona(ActionEvent actionEvent) {
        Alumno encontrado = alumnos.CompareFingerPrint(HuellaEnString, curso);
        if (bloqueClase == null) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Debe seleccionar fecha y bloque"));

            return;
        }
        if (encontrado != null) {
            AlumnosDelCurso tempAlumno = new AlumnosDelCurso(curso.getAsignatura().getIdAsignatura(), curso.getTipoAsignatura().getIdTipoAsignatura(), encontrado.getIdAlumno());
            if (asistenciaSB.alumnoAsiste(tempAlumno, bloqueClase, new Date()) == null) {
                Asistencia nueva = new Asistencia();
                nueva.setBloqueClaseId(bloqueClase);
                nueva.setEstado(1);
                nueva.setFecha(new Date());
                nueva.setAlumnosDelCurso(tempAlumno);
                asistenciaFacade.create(nueva);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Alumno Agregado", encontrado.getNombre() + " " + encontrado.getApellidop() + " " + encontrado.getApellidom()));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "ya ingresado", encontrado.getNombre() + " " + encontrado.getApellidop() + " " + encontrado.getApellidom()));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alumno no encontrado", "EL alumno no se encuentra matriculado en este curso"));
        }
    }

    public String estadoAsistencia(AlumnosDelCurso alumnoClase) {
        if (fecha == null) {
            fecha = new Date();
        }
        if (bloqueClase == null) {
            return " ";
        }

        Asistencia asis = asistenciaSB.alumnoAsiste(alumnoClase, bloqueClase, fecha);
        if (asis != null) {
            if (asis.getEstado() == 1) {
                return "Presente";
            } else {
                return "Justificado";
            }
        }
        return "Ausente";
    }

    public void handleDateSelect(SelectEvent event) {
        SimpleDateFormat format = new SimpleDateFormat("d/M/yyyy");
        fecha = (Date) event.getObject();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Fecha seleccionada", format.format(event.getObject())));
    }

    public void handleBloqueClaseChange() {
        for (BloqueClase bloq : curso.getBloqueClaseList()) {
            if (bloq.getIdBloque() == valor) {
                bloqueClase = bloq;
                FacesContext facesContext = FacesContext.getCurrentInstance();
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bloque seleccionado", bloqueClase.toString()));
                return;
            }
        }
        bloqueClase = null;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public List<Curso> getListCurso() {
        return ListCurso;
    }

    public void setListCurso(List<Curso> ListCurso) {
        this.ListCurso = ListCurso;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public BloqueClase getBloqueClase() {
        return bloqueClase;
    }

    public void setBloqueClase(BloqueClase bloqueClase) {
        this.bloqueClase = bloqueClase;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getHuellaEnString() {
        return HuellaEnString;
    }

    public void setHuellaEnString(String HuellaEnString) {
        this.HuellaEnString = HuellaEnString;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Map<String, String> getBloques() {
        return bloques;
    }

    public void setBloques(Map<String, String> bloques) {
        this.bloques = bloques;
    }

    public List<BloqueClase> getBloqueClaseList() {
        return bloqueClaseList;
    }

    public void setBloqueClaseList(List<BloqueClase> bloqueClaseList) {
        this.bloqueClaseList = bloqueClaseList;
    }
}
