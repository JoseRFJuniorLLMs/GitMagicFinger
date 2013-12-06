/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.AlumnosDelCurso;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author chevo
 */
@Local
public interface AlumnosDelCursoFacadeLocal {

    void create(AlumnosDelCurso alumnosDelCurso);

    void edit(AlumnosDelCurso alumnosDelCurso);

    void remove(AlumnosDelCurso alumnosDelCurso);

    AlumnosDelCurso find(Object id);

    List<AlumnosDelCurso> findAll();

    List<AlumnosDelCurso> findRange(int[] range);

    int count();
    
}
