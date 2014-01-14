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
import sessionBeans.GruposFacadeLocal;

/**
 *
 * @author lol
 */
@Named(value = "gruposMB")
@RequestScoped
public class GruposMB {
    @EJB
    private GruposFacadeLocal gruposFacade;
    @EJB
    private AlumnosDelCursoFacadeLocal alumnosDelCursoFacade;

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
        ListGrupos = new ArrayList<>();
        profesor = profesorLogin.getProfesor();
        if (profesor != null) {
            List<Grupos> GruposAux = gruposFacade.findAll();
            for (Grupos grupos : GruposAux) {
                if(grupos.getCurso().equals(profesorLogin.getCurso())){
                    ListGrupos.add(grupos);
                }
            }
            ListaGrupoData = new gruposDataModel(ListGrupos);
        }
        if(grupoSeleccionado!=null){
            System.out.println("voy a recargar la volva");
            listarIntegrantes();
            }
    }
    
    public void listarIntegrantes(){
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
        
    }
    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Grupo Seleccionado", grupoSeleccionado.toString());
        listarIntegrantes();
        
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    public void actualizar(){
         
    }
    public void onTransfer(TransferEvent event) {  

        for (Iterator<AlumnosDelCurso> it = asignar.getTarget().iterator(); it.hasNext();) {
            
            String alumno = it.next()+"";
            System.out.println("Alumno Integrante: "+alumno);
            List<AlumnosDelCurso> Alumno = alumnosDelCursoFacade.BuscarPorIdAlumno(Integer.parseInt(alumno));
            for (AlumnosDelCurso alumnosDelCurso : Alumno) {
                if(alumnosDelCurso.getGruIdGrupo()==null && alumnosDelCurso.getCurso().equals(profesorLogin.getCurso())){
                alumnosDelCurso.setGruIdGrupo(grupoSeleccionado);
                alumnosDelCursoFacade.edit(alumnosDelCurso);
                System.out.println("GRupo: "+alumnosDelCurso.getGruIdGrupo());
            }
            }
            
        }
        for (Iterator<AlumnosDelCurso> it = asignar.getSource().iterator(); it.hasNext();) {
           String alumno = it.next()+"";
            System.out.println("Alumno Disponible: "+alumno);
           List<AlumnosDelCurso> Alumno = alumnosDelCursoFacade.BuscarPorIdAlumno(Integer.parseInt(alumno));
            for (AlumnosDelCurso alumnosDelCurso : Alumno) {
                if(alumnosDelCurso.getGruIdGrupo()!=null && alumnosDelCurso.getCurso().equals(profesorLogin.getCurso())){
                alumnosDelCurso.setGruIdGrupo(null);
                alumnosDelCursoFacade.edit(alumnosDelCurso);
                System.out.println("GRupo: "+alumnosDelCurso.getGruIdGrupo());
            }
            }
        }
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
