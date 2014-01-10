/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.Asignatura;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author chevo
 */
@Local
public interface AsignaturaFacadeLocal {

    void create(Asignatura asignatura);

    void edit(Asignatura asignatura);

    void remove(Asignatura asignatura);

    Asignatura find(Object id);

    List<Asignatura> findAll();

    List<Asignatura> findRange(int[] range);

    int count();
    
    List BuscarPorIdUniversidad(int idUniversidad);
}
