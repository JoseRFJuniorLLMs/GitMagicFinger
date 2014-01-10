/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.Asignatura;
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
public class AsignaturaFacade extends AbstractFacade<Asignatura> implements AsignaturaFacadeLocal {
    @PersistenceContext(unitName = "MagicFinger-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AsignaturaFacade() {
        super(Asignatura.class);
    }
    public List BuscarPorIdUniversidad(int idUniversidad){
        Query q = em.createNamedQuery("Asignatura.findByUniversidad").setParameter("idUniversidad", idUniversidad);
        List listado = q.getResultList();
        return listado;
    }
}
