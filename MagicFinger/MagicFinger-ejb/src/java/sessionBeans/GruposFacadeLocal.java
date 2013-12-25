/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.Grupos;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author lol
 */
@Local
public interface GruposFacadeLocal {

    void create(Grupos grupos);

    void edit(Grupos grupos);

    void remove(Grupos grupos);

    Grupos find(Object id);

    List<Grupos> findAll();

    List<Grupos> findRange(int[] range);

    int count();
    
}
