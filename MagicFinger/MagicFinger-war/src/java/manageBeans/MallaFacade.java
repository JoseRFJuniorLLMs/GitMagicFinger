/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package manageBeans;

import entity.Malla;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author chevo
 */
@Stateless
public class MallaFacade extends AbstractFacade<Malla> {
    @PersistenceContext(unitName = "MagicFinger-warPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MallaFacade() {
        super(Malla.class);
    }
    
}
