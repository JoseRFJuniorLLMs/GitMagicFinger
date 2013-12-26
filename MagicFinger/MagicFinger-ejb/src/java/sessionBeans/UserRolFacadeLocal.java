/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.UserRol;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author chevo
 */
@Local
public interface UserRolFacadeLocal {

    void create(UserRol userRol);

    void edit(UserRol userRol);

    void remove(UserRol userRol);

    UserRol find(Object id);

    List<UserRol> findAll();

    List<UserRol> findRange(int[] range);

    int count();
    
}
