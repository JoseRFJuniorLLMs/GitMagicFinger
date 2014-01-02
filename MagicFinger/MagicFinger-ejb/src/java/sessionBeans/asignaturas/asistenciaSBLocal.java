/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans.asignaturas;

import entity.AlumnosDelCurso;
import entity.Asistencia;
import entity.BloqueClase;
import java.util.Date;
import javax.ejb.Local;

@Local
public interface asistenciaSBLocal {
    
    Asistencia alumnoAsiste(AlumnosDelCurso alumnosDelCurso, BloqueClase bloque, Date fecha);
}
