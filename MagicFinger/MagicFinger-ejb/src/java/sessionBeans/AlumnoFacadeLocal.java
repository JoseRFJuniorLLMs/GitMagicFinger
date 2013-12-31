/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.Alumno;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author chevo
 */
@Local
public interface AlumnoFacadeLocal {

    void create(Alumno alumno);

    void edit(Alumno alumno);

    void remove(Alumno alumno);

    Alumno find(Object id);

    List<Alumno> findAll();

    List<Alumno> findRange(int[] range);

    int count();
    
}
