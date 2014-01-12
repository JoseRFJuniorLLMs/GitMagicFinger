/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package manageBeans;

import com.sun.org.apache.xerces.internal.parsers.IntegratedParserConfiguration;
import entity.AlumnosDelCurso;
import entity.Grupos;
import entity.Profesor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;
import sessionBeans.AlumnosDelCursoFacadeLocal;

/**
 *
 * @author lol
 */
@Named(value = "gruposMB")
@RequestScoped
public class GruposMB {
    @EJB
    private AlumnosDelCursoFacadeLocal alumnosDelCursoFacade;

    /**
     * Creates a new instance of GruposMB
     */
    private gruposDataModel ListaGrupoData;
    private Grupos grupoSeleccionado;
    private Profesor profesor;
    private List<Grupos> ListGrupos;
    private List<AlumnosDelCurso> listaIntegrante;
     private DualListModel<AlumnosDelCurso> asignar;
     private DualListModel<String> asignarString;
     
    public GruposMB() {
      
    }
    @Inject
    LoginSessionMB profesorLogin;
    @Inject
    GrupoConversation conversation;
    @PostConstruct
    public void init() {
        grupoSeleccionado = conversation.getGruposelecionado();
        System.out.println("grupo selecionado actual" + grupoSeleccionado);
      //  grupoSeleccionado = profesorLogin.get;
        asignar = new DualListModel<>();
        
        profesor = profesorLogin.getProfesor();
        if (profesor != null) {
            ListGrupos = profesorLogin.getCurso().getGruposList();
            
            ListaGrupoData = new gruposDataModel(ListGrupos);
        }
        System.out.println("fin init");
    }
    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Grupo Seleccionado", grupoSeleccionado.toString());
        
        conversation.setGruposelecionado(grupoSeleccionado);
        System.out.println("se ha seteado el grupo: " + grupoSeleccionado);
        List<AlumnosDelCurso> aux = alumnosDelCursoFacade.BuscarPorIdUniversidad(profesorLogin.getIdUniversidad());
        List<AlumnosDelCurso> aux2 = new ArrayList<>();

        for (AlumnosDelCurso alumnosDelCurso : aux) {
            if(alumnosDelCurso.getCurso().equals(profesorLogin.getCurso())){
                aux2.add(alumnosDelCurso);
            }
        }
        List<AlumnosDelCurso> aux3 = new ArrayList<>();
        listaIntegrante = new ArrayList<>();
        for (AlumnosDelCurso alumnosDelCurso : aux2) {
            System.out.println("alu: "+alumnosDelCurso.getGruIdGrupo());
            if(alumnosDelCurso.getGruIdGrupo()==null){
                aux3.add(alumnosDelCurso);
            }else if(alumnosDelCurso.getGruIdGrupo().equals(grupoSeleccionado)){
                listaIntegrante.add(alumnosDelCurso);
            }
           
        }
        if(!listaIntegrante.isEmpty()){
            asignar = new DualListModel<>(aux3, listaIntegrante);
        }else{
            asignar = new DualListModel<>(aux3, new ArrayList<AlumnosDelCurso>());
        }
        
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    public void actualizar(){
         
    }
    public void onTransfer(TransferEvent event) {  
         DualListModel<AlumnosDelCurso> aux = new DualListModel<>();
        List<AlumnosDelCurso> targetAux = new ArrayList<>();
        List<AlumnosDelCurso> sourceAux = new ArrayList<>();
        for (Iterator<AlumnosDelCurso> it = asignar.getTarget().iterator(); it.hasNext();) {
            
            String alumno = it.next()+"";
            System.out.println("Alumno Integrante: "+alumno);
            AlumnosDelCurso Alumno = (AlumnosDelCurso) alumnosDelCursoFacade.BuscarPorIdAlumno(Integer.parseInt(alumno));
            if(Alumno.getGruIdGrupo()==null){
                Alumno.setGruIdGrupo(grupoSeleccionado);
                alumnosDelCursoFacade.edit(Alumno);
                System.out.println("GRupo: "+Alumno.getGruIdGrupo());
            }
            targetAux.add(Alumno);
        }
        for (Iterator<AlumnosDelCurso> it = asignar.getSource().iterator(); it.hasNext();) {
           String alumno = it.next()+"";
            System.out.println("Alumno Disponible: "+alumno);
            AlumnosDelCurso Alumno = (AlumnosDelCurso) alumnosDelCursoFacade.BuscarPorIdAlumno(Integer.parseInt(alumno));
            if(Alumno.getGruIdGrupo()!=null){
                Alumno.setGruIdGrupo(null);
                alumnosDelCursoFacade.edit(Alumno);
                System.out.println("GRupo: "+Alumno.getGruIdGrupo());
            }
            sourceAux.add(Alumno);
        }
        aux.setSource(sourceAux);
        aux.setTarget(targetAux);
        asignar = aux;
        FacesMessage msg = new FacesMessage();  
        msg.setSeverity(FacesMessage.SEVERITY_INFO);  
        msg.setSummary("Actualizado");  
          
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }  
    

    public void setListaGrupoData(gruposDataModel ListaGrupoData) {
        this.ListaGrupoData = ListaGrupoData;
    }

    public Grupos getGrupoSeleccionado() {
        return grupoSeleccionado;
    }

    public void setGrupoSeleccionado(Grupos grupoSeleccionado) {
        this.grupoSeleccionado = grupoSeleccionado;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public List<Grupos> getListGrupos() {
        return ListGrupos;
    }

    public void setListGrupos(List<Grupos> ListGrupos) {
        this.ListGrupos = ListGrupos;
    }

    public LoginSessionMB getProfesorLogin() {
        return profesorLogin;
    }

    public void setProfesorLogin(LoginSessionMB profesorLogin) {
        this.profesorLogin = profesorLogin;
    }

    public List<AlumnosDelCurso> getListaIntegrante() {
        return listaIntegrante;
    }

    public void setListaIntegrante(List<AlumnosDelCurso> listaIntegrante) {
        this.listaIntegrante = listaIntegrante;
    }

    public DualListModel<AlumnosDelCurso> getAsignar() {
        return asignar;
    }

    public void setAsignar(DualListModel<AlumnosDelCurso> asignar) {
        this.asignar = asignar;
    }

    public DualListModel<String> getAsignarString() {
        return asignarString;
    }

    public void setAsignarString(DualListModel<String> asignarString) {
        this.asignarString = asignarString;
    }

    public gruposDataModel getListaGrupoData() {
        return ListaGrupoData;
    }


    
}
