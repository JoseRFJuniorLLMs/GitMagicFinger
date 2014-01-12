/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package manageBeans;

import entity.ProfesoresPorDepartamento;
import entity.Universidad;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Init;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import sessionBeans.UniversidadFacadeLocal;

/**
 *
 * @author chevo
 */
@Named(value = "postLoginMB")
@RequestScoped
public class postLoginMB {
    @EJB
    private UniversidadFacadeLocal universidadFacade;
    private List<Universidad> listUniversidad;
    @Inject
    private LoginSessionMB login;
    private String nombreUniversidad;
    
    public postLoginMB() {
    }
    
    @PostConstruct
    public void init(){
        Integer nueva = login.getIdUniversidad();
        if(login.getProfesor()!=null){
            listUniversidad = new LinkedList<>();
            List<ProfesoresPorDepartamento> var = login.getProfesor().getProfesoresPorDepartamentoList();
            for (ProfesoresPorDepartamento profesoresPorDepartamento : var) {
                if(!listUniversidad.contains( profesoresPorDepartamento.getDepartamento().getFacIdFacultad().getUniIdUniversidad()))
                    listUniversidad.add( profesoresPorDepartamento.getDepartamento().getFacIdFacultad().getUniIdUniversidad() );
            }
        }
        else{
            listUniversidad = universidadFacade.findAll();
        }
        if(nueva != null && nueva!=-1){
            System.out.println("aki" + nueva);
            nombreUniversidad = universidadFacade.find(nueva).getNombre();
            System.out.println("aki2");
        }   
        System.out.println("aki fin");
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
    
    public void redirec(Integer universidad){
        login.setIdUniversidad(universidad);
        if(login.getProfesor()!=null){
            redireccionar("/faces/profesor/index2.xhtml");        
        }
        else{
            redireccionar("/faces/administrador/index3.xhtml");
        }        
    }
    public List<Universidad> getListUniversidad() {
        return listUniversidad;
    }

    public void setListUniversidad(List<Universidad> listUniversidad) {
        this.listUniversidad = listUniversidad;
    }

    public String getNombreUniversidad() {
        return nombreUniversidad;
    }

    public void setNombreUniversidad(String nombreUniversidad) {
        this.nombreUniversidad = nombreUniversidad;
    }
    
    
}
