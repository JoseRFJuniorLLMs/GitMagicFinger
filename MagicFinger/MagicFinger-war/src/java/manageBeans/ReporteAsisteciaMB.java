/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package manageBeans;

import entity.Alumno;
import entity.Asistencia;
import entity.BloqueClase;
import entity.Curso;
import entity.Profesor;
import java.util.Calendar;
import java.util.Date;
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
    
    private Curso curso;
    private Profesor profesor;
    private List<Asistencia> asistenciaCurso;
    private int cantidadBLoques;
    
    private CartesianChartModel categoryModel;  
  
    private CartesianChartModel linearModel;  
    
        
    @PostConstruct
    public void init() {
        curso = profesorLogin.getCurso();
        profesor = profesorLogin.getProfesor();
        cantidadBLoques = bloquesTotales();
        
        createCategoryModel();  
        createLinearModel(); 
    }
    
    public CartesianChartModel getCategoryModel() {  
        return categoryModel;  
    }  
  
    public CartesianChartModel getLinearModel() {  
        return linearModel;  
    }  
  
    private void createCategoryModel() {  
        categoryModel = new CartesianChartModel();  
  
        ChartSeries boys = new ChartSeries();  
        boys.setLabel("Boys");  
  
        boys.set("2004", 120);  
        boys.set("2005", 100);  
        boys.set("2006", 44);  
        boys.set("2007", 150);  
        boys.set("2008", 25);  
  
        ChartSeries girls = new ChartSeries();  
        girls.setLabel("Girls");  
  
        girls.set("2004", 52);  
        girls.set("2005", 60);  
        girls.set("2006", 110);  
        girls.set("2007", 135);  
        girls.set("2008", 120);  
  
        categoryModel.addSeries(boys);  
        categoryModel.addSeries(girls);  
    }  
  
    private void createLinearModel() {  
        linearModel = new CartesianChartModel();  
  
        LineChartSeries series1 = new LineChartSeries();  
        series1.setLabel("Series 1");  
  
        series1.set(1, 2);  
        series1.set(2, 1);  
        series1.set(3, 3);  
        series1.set(4, 6);  
        series1.set(5, 8);  
  
        LineChartSeries series2 = new LineChartSeries();  
        series2.setLabel("Series 2");  
        series2.setMarkerStyle("diamond");  
  
        series2.set(1, 6);  
        series2.set(2, 3);  
        series2.set(3, 2);  
        series2.set(4, 7);  
        series2.set(5, 9);  
  
        linearModel.addSeries(series1);  
        linearModel.addSeries(series2);  
    }  
    
    
    public String cuentaAsistencia(Alumno alumno){
        if(cantidadBLoques==0)return "SIN BLOQUES";
        asistenciaCurso = asistenciaSB.cuentaAsistencia(alumno, curso);
        int cantidadBloquesReales = cantidadBLoques-asistenciaSB.cuentaAsistenciaSuspendida(alumno, curso).size();
        curso.getSemestre().getFechaInicio().getDay();
        
        return (asistenciaCurso.size()*100/cantidadBloquesReales) + " %";
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
    
}
