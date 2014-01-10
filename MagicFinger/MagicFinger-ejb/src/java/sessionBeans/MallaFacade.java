/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.Malla;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author chevo
 */
@Stateless
public class MallaFacade extends AbstractFacade<Malla> implements MallaFacadeLocal {
    @PersistenceContext(unitName = "MagicFinger-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MallaFacade() {
        super(Malla.class);
    }
    public List BuscarPorIdUniversidad(int idUniversidad){
        Query q = em.createNamedQuery("Malla.findByUniversidad").setParameter("idUniversidad", idUniversidad);
        List listado = q.getResultList();
        return listado;
    }
}
