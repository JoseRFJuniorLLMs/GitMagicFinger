package manageBeans;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import entity.Curso;
import entity.Profesor;
import entity.ProfesoresPorCurso;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.SlideEndEvent;
import sessionBeans.CursoFacadeLocal;
import sessionBeans.ProfesorFacadeLocal;

/**
 *
 * @author Nacho
 */
@Named(value = "asignaturaMB")
@RequestScoped
public class asignaturaMB {
    @EJB
    private CursoFacadeLocal cursoFacade;
    @EJB
    private ProfesorFacadeLocal profesorFacade;
    
    private Profesor profesor;
    private List<Curso> ListCurso = new LinkedList<>();
    private asignaturaDataModel ListaCursoData;
    private Curso cursoSeleccionado;

    @Inject 
    private TomaAsistenciaConversation conversation;
    @Inject
    LoginSessionMB profesorLogin;
    @PostConstruct
    public void init() {
        cursoSeleccionado = profesorLogin.getCurso();
        profesor = profesorLogin.getProfesor();
        if (profesor != null) {
            profesor.getProfesoresPorCursoList();
            for (ProfesoresPorCurso curso : profesor.getProfesoresPorCursoList()) {
               ListCurso.add(curso.getCurso());
            }
            ListaCursoData = new asignaturaDataModel(ListCurso);
        }
    }

    public ProfesorFacadeLocal getProfesorFacade() {
        return profesorFacade;
    }
    public void redireccionar(String pagina){
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
       try {
           externalContext.redirect(externalContext.getRequestContextPath() + pagina);
       }
       catch (IOException e) {
           System.out.println(e.getMessage());
       }
    }
    public void envioDatosEstadisticas(){
        redireccionar("/faces/profesor/reporteEstadisticas.xhtml");
    }
    
    public void envioDatosReportes(){
        redireccionar("/faces/profesor/reporteAsistencia.xhtml");
    }
    
    public void envioDatos(){
        conversation.beginConversation();
        System.out.println("El de asignatura imprime " + cursoSeleccionado.getTipoAsignatura().getNombre());
        conversation.setCurso(cursoSeleccionado);
        conversation.setProfesor(profesor);
        redireccionar("/faces/profesor/registrarAsistencia.xhtml?cid=".concat(this.conversation.getConversation().getId()));
        
    }
    public void onRowSelect(SelectEvent event) {
        profesorLogin.setCurso(cursoSeleccionado);
        FacesMessage msg = new FacesMessage("Curso Seleccionado", cursoSeleccionado.toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", " Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void actualizarDatos() {
        FacesMessage msg = new FacesMessage("Normas de curso actualizadas", "Porcentaje aprobación: " + cursoSeleccionado.getPorcentajeAprobacion() + "\n Atrasados desde: "+ cursoSeleccionado.getTermino() );
        FacesContext.getCurrentInstance().addMessage(null, msg);
        cursoFacade.edit(cursoSeleccionado);
    }

    public void setProfesorFacade(ProfesorFacadeLocal profesorFacade) {
        this.profesorFacade = profesorFacade;
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

    public Curso getCursoSeleccionado() {
        return cursoSeleccionado;
    }

    public void setCursoSeleccionado(Curso cursoSeleccionado) {
        this.cursoSeleccionado = cursoSeleccionado;
    }

    public asignaturaDataModel getListaCursoData() {
        return ListaCursoData;
    }

    public void setListaCursoData(asignaturaDataModel ListaCursoData) {
        this.ListaCursoData = ListaCursoData;
    }
}
