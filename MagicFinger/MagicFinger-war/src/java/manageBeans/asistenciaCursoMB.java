package manageBeans;

import entity.Alumno;
import entity.AlumnosDelCurso;
import entity.Asistencia;
import entity.BloqueClase;
import entity.Curso;
import entity.Profesor;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import org.primefaces.event.SelectEvent;
import sessionBeans.AsistenciaFacadeLocal;
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
    private alumnosLocal alumnos;
    
    @Inject 
    private TomaAsistenciaConversation conversation;
    
    private String HuellaEnString;
    private Profesor profesor;
    private List<Curso> ListCurso;
    private Curso curso;
    private BloqueClase bloqueClase;
    private int valor;
    private Date fecha;
    private List<BloqueClase> bloqueClaseList;
    private List<BloqueClase> bloqueClasesAllList;
    private boolean restriccionAsistencia;
    
    @PostConstruct
    public void init() {
        restriccionAsistencia = true;
           
        fecha = conversation.getFecha();
        profesor = conversation.getProfesor();
        curso = conversation.getCurso();
        bloqueClasesAllList = curso.getBloqueClaseList();
        
        bloqueClaseList = conversation.getBloqueClaseList();
        if(bloqueClaseList ==null){
            bloqueClaseList = bloqueDetectadoPorFecha();
            conversation.setBloqueClaseList(bloqueClaseList);
            BloqueClase temp = bloqueDetectadoPorFechaYHora();
            if(temp!=null){
                bloqueClase = temp;
                valor = bloqueClase.getIdBloque();
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
        BloqueClase bloqueClaseActual = bloqueDetectadoPorFechaYHora();
        Alumno encontrado = alumnos.CompareFingerPrint(HuellaEnString, curso);
        if (bloqueClaseActual == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "No se puede marcar asistencia, fuera del bloque de clases"));
            return;
        }
        
        if (encontrado != null) {
            AlumnosDelCurso tempAlumno = new AlumnosDelCurso(curso.getAsignatura().getIdAsignatura(), curso.getTipoAsignatura().getIdTipoAsignatura(), encontrado.getIdAlumno());
            if (asistenciaSB.alumnoAsiste(tempAlumno, bloqueClaseActual, new Date()) == null) {
                Asistencia nueva = new Asistencia();
                //estado
                Date fecha[] = funcionesGenerales.buscaBloque(bloqueClaseActual.getBloque());
                fecha[1] = funcionesGenerales.sumaMinutosFecha(fecha[0], curso.getTermino());
                Date hoy = new Date();
                System.out.println( "( "+bloqueClase.getBloque()+ " ) fecha inicio:" + fecha[0].toString() + "fecha fin:" + fecha[1].toString()+" respuesta: " +(hoy.after(fecha[0]) && hoy.before(fecha[1])) );
                if( hoy.after(fecha[0]) && hoy.before(fecha[1]) )
                    nueva.setEstado(1);
                else
                    nueva.setEstado(3);
                //datos
                nueva.setBloqueClaseId(bloqueClaseActual);
                nueva.setFecha(hoy);
                nueva.setAlumnosDelCurso(tempAlumno);
                asistenciaFacade.create(nueva);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Alumno Presente", encontrado.getNombre() + " " + encontrado.getApellidop() + " " + encontrado.getApellidom()));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "ya ingresado", encontrado.getNombre() + " " + encontrado.getApellidop() + " " + encontrado.getApellidom()));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alumno no encontrado", "EL alumno no se encuentra matriculado en este curso"));
        }
    }
    private BloqueClase bloqueDetectadoPorFechaYHora(){
        Date hoy= new Date();
        for (BloqueClase bloqueClase1 : bloqueClaseList) {
            Date[] fechaBloque = funcionesGenerales.buscaBloque(bloqueClase1.getBloque());
            if(hoy.after(fechaBloque[0]) && hoy.before(fechaBloque[1])){
                System.out.println("encontre el bloque");
                return bloqueClase1;
            }
        }
        return null;
    }
    private List<BloqueClase> bloqueDetectadoPorFecha() {
        List<BloqueClase> bloqueDetectado = new LinkedList<>();
        for (BloqueClase tempBloque : bloqueClasesAllList) {
            if(tempBloque.getDiaSemana().equals( funcionesGenerales.getDiaFecha(fecha)  )){
                bloqueDetectado.add(tempBloque);
            }
        }
        if(bloqueDetectado.size()>0){
            bloqueClase = bloqueDetectado.get(0);
            valor = bloqueDetectado.get(0).getIdBloque();
        }
        else{
            bloqueClase=null;
            valor = -1;
        }
        return bloqueDetectado;
    }
    
    public String estadoAsistencia(AlumnosDelCurso alumnoClase) {
        if (fecha == null) {
            return "no-fecha";
        }
        if (bloqueClase == null ) {
            return "-";
        }
        Asistencia asis = asistenciaSB.alumnoAsiste(alumnoClase, bloqueClase, fecha);
        if(asis==null){
            return "Ausente";
        }
        switch(asis.getEstado()){
            case 1:
                System.out.println("busco estado en 1");
                return "Presente";
            case 2:
                System.out.println("busco estado en 2");
                return "Justificado";
            case 3:
                return "Atrasado";
            default:
                return "Ausente";
        }
        
    }

    public void handleDateSelect(SelectEvent event) {
        SimpleDateFormat format = new SimpleDateFormat("d/M/yyyy");
        conversation.setFecha((Date) event.getObject());
        fecha = (Date) event.getObject();
        System.out.println("fecha seteada:"  + fecha  );
        bloqueClaseList = bloqueDetectadoPorFecha();
        conversation.setBloqueClaseList(bloqueClaseList);
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Fecha seleccionada ", funcionesGenerales.getDiaFecha(fecha)+" "+ format.format(event.getObject())));
    }
    
    public void handleBloqueClaseChange() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
                
        if (valor != -1) {
           
            for (BloqueClase bloq : curso.getBloqueClaseList()) {
                if (bloq.getIdBloque() == valor) {
                    bloqueClase = bloq;
                    facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bloque seleccionado", bloqueClase.toString()));
                    return;
                }
            }
        }
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Bloque", "Seleccione un bloque" ));
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

    public List<BloqueClase> getBloqueClaseList() {
        return bloqueClaseList;
    }

    public void setBloqueClaseList(List<BloqueClase> bloqueClaseList) {
        this.bloqueClaseList = bloqueClaseList;
    }

    
}
