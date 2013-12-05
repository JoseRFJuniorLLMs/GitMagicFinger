/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.TipoAsignatura;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author chevo
 */
@Local
public interface TipoAsignaturaFacadeLocal {

    void create(TipoAsignatura tipoAsignatura);

    void edit(TipoAsignatura tipoAsignatura);

    void remove(TipoAsignatura tipoAsignatura);

    TipoAsignatura find(Object id);

    List<TipoAsignatura> findAll();

    List<TipoAsignatura> findRange(int[] range);

    int count();
    
}
