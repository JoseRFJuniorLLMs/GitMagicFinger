/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package manageBeans;

import entity.BloqueClase;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author chevo
 */
@Stateless
public class BloqueClaseFacade extends AbstractFacade<BloqueClase> {
    @PersistenceContext(unitName = "MagicFinger-warPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BloqueClaseFacade() {
        super(BloqueClase.class);
    }
    
}
