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
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DualListModel;

/**
 *
 * @author lol
 */
@Named(value = "gruposMB")
@RequestScoped
public class GruposMB {

    /**
     * Creates a new instance of GruposMB
     */
    private gruposDataModel ListaGrupoData;
    private Grupos grupoSeleccionado;
    private Profesor profesor;
    private List<Grupos> ListGrupos;
    private List<AlumnosDelCurso> listaIntegrante;
     private DualListModel<AlumnosDelCurso> asignar;
    public GruposMB() {
      
    }
    @Inject
    LoginSessionMB profesorLogin;
    @PostConstruct
    public void init() {
      //  grupoSeleccionado = profesorLogin.get;
        asignar = new DualListModel<>();
        List<Grupos> lista = new ArrayList<>();
        profesor = profesorLogin.getProfesor();
        if (profesor != null) {
            ListGrupos = profesorLogin.getCurso().getGruposList();
            String nombre2 = ListGrupos.get(0).getNombre();
            String  nombre;
            lista.add(ListGrupos.get(0));
            for (int i = 1; i < ListGrupos.size(); i++) {
            nombre = ListGrupos.get(i).getNombre();
                if(!nombre2.equals(nombre)){
                    lista.add(ListGrupos.get(i));
                    nombre2 = nombre;
                }
            }
            ListaGrupoData = new gruposDataModel(lista);
        }
    }
    public void envioDatos(){
        redireccionar("/faces/profesor/grupos.xhtml");
        
    }
    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Grupo Seleccionado", grupoSeleccionado.toString());
        List<AlumnosDelCurso> aux = grupoSeleccionado.getCurso().getAlumnosDelCursoList();
        List<AlumnosDelCurso> aux2 = new ArrayList<>();
        listaIntegrante = new ArrayList<>();
        for (Grupos lista : ListGrupos) {
            if((lista.getNombre()).equals(grupoSeleccionado.getNombre())){
                listaIntegrante.add(lista.getAlumnosDelCurso() );
            }
        }
        
        for (AlumnosDelCurso alumnosDelCurso : aux) {
            for (Grupos listgrupos : alumnosDelCurso.getGruposList()) {
                
                if(listgrupos.getNombre() == null){
                    
                 aux2.add(alumnosDelCurso);
            
                }
            }
        }
        asignar = new DualListModel<>(aux2, listaIntegrante);
        FacesContext.getCurrentInstance().addMessage(null, msg);
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

    public gruposDataModel getListaGrupoData() {
        return ListaGrupoData;
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


    
}
