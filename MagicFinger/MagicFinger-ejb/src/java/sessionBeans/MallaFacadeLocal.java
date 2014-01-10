/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.Malla;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author chevo
 */
@Local
public interface MallaFacadeLocal {

    void create(Malla malla);

    void edit(Malla malla);

    void remove(Malla malla);

    Malla find(Object id);

    List<Malla> findAll();

    List<Malla> findRange(int[] range);

    int count();
    
    List BuscarPorIdUniversidad(int idUniversidad);
}
