/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.ProfesoresPorDepartamento;
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
public class ProfesoresPorDepartamentoFacade extends AbstractFacade<ProfesoresPorDepartamento> implements ProfesoresPorDepartamentoFacadeLocal {
    @PersistenceContext(unitName = "MagicFinger-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProfesoresPorDepartamentoFacade() {
        super(ProfesoresPorDepartamento.class);
    }
    @Override
    public List BuscarPorIdUniversidad(int idUniversidad){
        Query q = em.createNamedQuery("ProfesoresPorDepartamento.findByUniversidad").setParameter("idUniversidad", idUniversidad);
        List listado = q.getResultList();
        return listado;
    }
}
