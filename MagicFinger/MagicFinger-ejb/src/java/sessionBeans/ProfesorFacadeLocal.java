/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.Profesor;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author chevo
 */
@Local
public interface ProfesorFacadeLocal {

    void create(Profesor profesor);

    void edit(Profesor profesor);

    void remove(Profesor profesor);

    Profesor find(Object id);

    List<Profesor> findAll();

    List<Profesor> findRange(int[] range);

    int count();
    
    List BuscarPorIdUniversidad(List idUniversidad);
}
