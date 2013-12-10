/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.BloqueClase;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author chevo
 */
@Local
public interface BloqueClaseFacadeLocal {

    void create(BloqueClase bloqueClase);

    void edit(BloqueClase bloqueClase);

    void remove(BloqueClase bloqueClase);

    BloqueClase find(Object id);

    List<BloqueClase> findAll();

    List<BloqueClase> findRange(int[] range);

    int count();
}
