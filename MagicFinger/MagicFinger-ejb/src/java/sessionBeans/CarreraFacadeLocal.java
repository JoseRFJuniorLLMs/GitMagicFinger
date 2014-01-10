/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.Carrera;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author chevo
 */
@Local
public interface CarreraFacadeLocal {

    void create(Carrera carrera);

    void edit(Carrera carrera);

    void remove(Carrera carrera);

    Carrera find(Object id);

    List<Carrera> findAll();

    List<Carrera> findRange(int[] range);

    int count();
    
    List BuscarPorIdUniversidad(int idUniversidad);
}
