/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.AlumnosPorCurso;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author chevo
 */
@Local
public interface AlumnosPorCursoFacadeLocal {

    void create(AlumnosPorCurso alumnosPorCurso);

    void edit(AlumnosPorCurso alumnosPorCurso);

    void remove(AlumnosPorCurso alumnosPorCurso);

    AlumnosPorCurso find(Object id);

    List<AlumnosPorCurso> findAll();

    List<AlumnosPorCurso> findRange(int[] range);

    int count();
    
}
