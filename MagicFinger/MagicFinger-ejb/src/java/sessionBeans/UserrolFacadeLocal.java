/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.Userrol;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author chevo
 */
@Local
public interface UserrolFacadeLocal {

    void create(Userrol userrol);

    void edit(Userrol userrol);

    void remove(Userrol userrol);

    Userrol find(Object id);

    List<Userrol> findAll();

    List<Userrol> findRange(int[] range);

    int count();
    
}
