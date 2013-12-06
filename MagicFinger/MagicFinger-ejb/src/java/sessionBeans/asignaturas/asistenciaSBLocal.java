/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans.asignaturas;

import entity.Alumno;
import entity.AlumnosDelCurso;
import entity.Asignatura;
import entity.BloqueClase;
import entity.TipoAsignatura;
import java.util.Date;
import javax.ejb.Local;

/**
 *
 * @author chevo
 */
@Local
public interface asistenciaSBLocal {

    int alumnoAsiste(AlumnosDelCurso alumnosDelCurso, BloqueClase bloque, Date fecha);

}
