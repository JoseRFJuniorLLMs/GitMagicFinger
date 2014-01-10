/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.Carrera;
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
public class CarreraFacade extends AbstractFacade<Carrera> implements CarreraFacadeLocal {
    @PersistenceContext(unitName = "MagicFinger-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CarreraFacade() {
        super(Carrera.class);
    }
    public List BuscarPorIdUniversidad(int idUniversidad){
        Query q = em.createNamedQuery("Carrera.findByUniversidad").setParameter("idUniversidad", idUniversidad);
        List listado = q.getResultList();
        return listado;
    }
}
