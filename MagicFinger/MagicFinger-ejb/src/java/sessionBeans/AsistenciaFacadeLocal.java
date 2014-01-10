/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.Asistencia;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author chevo
 */
@Local
public interface AsistenciaFacadeLocal {

    void create(Asistencia asistencia);

    void edit(Asistencia asistencia);

    void remove(Asistencia asistencia);

    Asistencia find(Object id);

    List<Asistencia> findAll();

    List<Asistencia> findRange(int[] range);

    int count();
    
    List BuscarPorIdUniversidad(int idUniversidad);
}
