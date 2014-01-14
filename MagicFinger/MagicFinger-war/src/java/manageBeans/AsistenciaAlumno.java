/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package manageBeans;

import entity.AlumnosDelCurso;
import entity.Asistencia;
import entity.BloqueClase;
import entity.Curso;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import sessionBeans.asignaturas.asistenciaSBLocal;

/**
 *
 * @author chevo
 */
@Named(value = "asistenciaAlumno")
@Dependent
public class AsistenciaAlumno {
    @EJB
    private asistenciaSBLocal asistenciaSB;
    
    /**
     * Creates a new instance of AsistenciaAlumno
     */
    public AsistenciaAlumno() {
    }
    
    public String calculaAsistencia(AlumnosDelCurso alumnoCurso){
        if(alumnoCurso.getCurso().getBloqueClaseList().size()==0) return "CURSO SIN BLOQUES";
        List<Asistencia> asistenciaTemp = asistenciaSB.cuentaAsistencia(alumnoCurso.getAlumno(), alumnoCurso.getCurso());
        int cantidadBloquesReales = bloquesTotales(alumnoCurso.getCurso())-asistenciaSB.cuentaAsistenciaSuspendida(alumnoCurso.getAlumno(), alumnoCurso.getCurso()).size();
        alumnoCurso.getCurso().getSemestre().getFechaInicio().getDay();
        return (asistenciaTemp.size()*100/cantidadBloquesReales) + " %";
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
}
