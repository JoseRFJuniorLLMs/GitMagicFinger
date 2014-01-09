/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.Facultad;
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
public class FacultadFacade extends AbstractFacade<Facultad> implements FacultadFacadeLocal {
    @PersistenceContext(unitName = "MagicFinger-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FacultadFacade() {
        super(Facultad.class);
    }

    @Override
    public List BuscarPorIdUniversidad(int idUniversidad) {
        Query q = em.createNamedQuery("Facultad.findByIdFacultad").setParameter("idUniversidad", idUniversidad);
        List listado = q.getResultList();
        return listado;
    }
    
}
