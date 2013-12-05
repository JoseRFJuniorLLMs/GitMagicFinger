/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans.asignaturas;

import entity.Profesor;
import entity.ProfesoresPorCurso;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author chevo
 */
@Local
public interface ProfesorPorCursoLocal {
    
    List<ProfesoresPorCurso> findByProfesor(Integer idProfesor);
}
