/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans.asignaturas;

import entity.Alumno;
import entity.AlumnosDelCurso;
import entity.Asistencia;
import entity.BloqueClase;
import entity.Curso;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

@Local
public interface asistenciaSBLocal {
    
    Asistencia alumnoAsiste(AlumnosDelCurso alumnosDelCurso, BloqueClase bloque, Date fecha);
    
    List<Asistencia> cuentaAsistencia(Alumno alumno, Curso curso);
    
    List<Asistencia> cuentaAsistenciaSuspendida(Alumno alumno, Curso curso);

    List<Asistencia> cuentaAsistenciaBloqueAndFecha(BloqueClase bloqueLista, Date date);
}
