/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.Semestre;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author chevo
 */
@Local
public interface SemestreFacadeLocal {

    void create(Semestre semestre);

    void edit(Semestre semestre);

    void remove(Semestre semestre);

    Semestre find(Object id);

    List<Semestre> findAll();

    List<Semestre> findRange(int[] range);

    int count();
    
}
