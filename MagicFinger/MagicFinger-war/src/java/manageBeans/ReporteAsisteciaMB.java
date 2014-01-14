/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package manageBeans;

import entity.Alumno;
import entity.AlumnosDelCurso;
import entity.Asistencia;
import entity.BloqueClase;
import entity.Curso;
import entity.Profesor;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.primefaces.model.chart.CartesianChartModel;  
import org.primefaces.model.chart.ChartSeries;  
import org.primefaces.model.chart.LineChartSeries;  
import sessionBeans.asignaturas.asistenciaSBLocal;

/**
 *
 * @author chevo
 */
@Named(value = "reporteAsisteciaMB")
@RequestScoped
public class ReporteAsisteciaMB {    
    @EJB
    private asistenciaSBLocal asistenciaSB;
    @Inject
    LoginSessionMB profesorLogin;
    private Alumno alumnoSeleccionado;
    private Curso curso;
    private Profesor profesor;
    private List<Asistencia> asistenciaCurso;
    private int cantidadBLoques;
    
    private List<Curso> listaProfesoresAlumnoSeleccionado;
    private CartesianChartModel semestreModel;  
        
    @PostConstruct
    public void init() {
        curso = profesorLogin.getCurso();
        profesor = profesorLogin.getProfesor();
        cantidadBLoques = bloquesTotales();
        
        crearReporteSemestreModel();  
    }
    
    
    public CartesianChartModel getSemestreModel() {
        return semestreModel;
    }

    public void setSemestreModel(CartesianChartModel semestreModel) {
        this.semestreModel = semestreModel;
    }
    
    private void crearReporteSemestreModel() {  
        semestreModel = new CartesianChartModel();  
        List<ChartSeries> listaChartSeries =  new LinkedList<ChartSeries>();
        
        for (BloqueClase bloqueLista : curso.getBloqueClaseList()) {
            ChartSeries asistenciaSemestralTotal = new ChartSeries();  
            asistenciaSemestralTotal.setLabel(bloqueLista.toString());  
            listaChartSeries.add(asistenciaSemestralTotal);
        }
     
        Calendar start = Calendar.getInstance();
        start.setTime(curso.getSemestre().getFechaInicio());
        Calendar end = Calendar.getInstance();
        end.setTime(curso.getSemestre().getFechaTermino());
        SimpleDateFormat dt1 = new SimpleDateFormat("dd/MM");
        
        for (Date date = start.getTime(); !start.after(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
            int i=0;
            for (BloqueClase bloqueLista : curso.getBloqueClaseList()) {
                if(bloqueLista.getDiaSemana().equals(funcionesGenerales.getDiaFecha(date)) ){
                    List<Asistencia> listaTemp = asistenciaSB.cuentaAsistenciaBloqueAndFecha(bloqueLista, date);
                    listaChartSeries.get(i).set( dt1.format(date) , listaTemp.size());
                }
                i++;
            }
        }
        int i =0;
        for (BloqueClase bloqueLista : curso.getBloqueClaseList()) {
           semestreModel.addSeries(listaChartSeries.get(i));
           i++;
        }
    }
    public String calculaAsistencia (Curso curso){
        List<BloqueClase> listaBloque = curso.getBloqueClaseList();
        if(curso.getBloqueClaseList().size()==0) return "SIN BLOQUES";
        List<Asistencia> asistenciaTemp = asistenciaSB.cuentaAsistencia(alumnoSeleccionado, curso);
        int cantidadBloquesReales = bloquesTotales(curso)-asistenciaSB.cuentaAsistenciaSuspendida(alumnoSeleccionado, curso).size();
        curso.getSemestre().getFechaInicio().getDay();
        return (asistenciaTemp.size()*100/cantidadBloquesReales) + " %";
   }
    public String cuentaAsistencia(Alumno alumno){
        if(cantidadBLoques==0)return "SIN BLOQUES";
        asistenciaCurso = asistenciaSB.cuentaAsistencia(alumno, curso);
        int cantidadBloquesReales = cantidadBLoques-asistenciaSB.cuentaAsistenciaSuspendida(alumno, curso).size();
        curso.getSemestre().getFechaInicio().getDay();
        
        return (asistenciaCurso.size()*100/cantidadBloquesReales) + " %";
    }
    public int bloquesTotales(Curso curso){
        int resultado = 0;
        Calendar start = Calendar.getInstance();
        start.setTime(curso.getSemestre().getFechaInicio());
        Calendar end = Calendar.getInstance();
        end.setTime(curso.getSemestre().getFechaTermino());

        for (Date date = start.getTime(); !start.after(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
            for (BloqueClase bloqueLista : curso.getBloqueClaseList()) {
                if(bloqueLista.getDiaSemana().equals(funcionesGenerales.getDiaFecha(date)) ){
                    resultado++;
                }
            }
        }
        return resultado;
    }
    public int bloquesTotales(){
        int resultado = 0;
        Calendar start = Calendar.getInstance();
        start.setTime(curso.getSemestre().getFechaInicio());
        Calendar end = Calendar.getInstance();
        end.setTime(curso.getSemestre().getFechaTermino());

        for (Date date = start.getTime(); !start.after(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
            for (BloqueClase bloqueLista : curso.getBloqueClaseList()) {
                if(bloqueLista.getDiaSemana().equals(funcionesGenerales.getDiaFecha(date)) ){
                    resultado++;
                }
            }
        }
        return resultado;
    }
    public List<Curso> ListadoRamos(){
        List<Curso> listado = new LinkedList<>();
        for (AlumnosDelCurso alumCurso : alumnoSeleccionado.getAlumnosDelCursoList() ) {
            listado.add(alumCurso.getCurso());
        }
        return listado;
    }
    public ReporteAsisteciaMB() {
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Alumno getAlumnoSeleccionado() {
        return alumnoSeleccionado;
    }

    public void setAlumnoSeleccionado(Alumno alumnoSeleccionado) {
        this.alumnoSeleccionado = alumnoSeleccionado;
    }

    public List<Curso> getListaProfesoresAlumnoSeleccionado() {
        
        if(alumnoSeleccionado==null)return null;
        listaProfesoresAlumnoSeleccionado = new LinkedList<>();
        
        for (AlumnosDelCurso alumCurso : alumnoSeleccionado.getAlumnosDelCursoList() ) {
            if(curso != alumCurso.getCurso())
                listaProfesoresAlumnoSeleccionado.add(alumCurso.getCurso());
        }
        return listaProfesoresAlumnoSeleccionado;
    }

    public void setListaProfesoresAlumnoSeleccionado(List<Curso> listaProfesoresAlumnoSeleccionado) {
        this.listaProfesoresAlumnoSeleccionado = listaProfesoresAlumnoSeleccionado;
    }
    
}
