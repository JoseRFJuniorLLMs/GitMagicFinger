/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.Userrol;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author chevo
 */
@Stateless
public class UserrolFacade extends AbstractFacade<Userrol> implements UserrolFacadeLocal {
    @PersistenceContext(unitName = "MagicFinger-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserrolFacade() {
        super(Userrol.class);
    }
    
}
