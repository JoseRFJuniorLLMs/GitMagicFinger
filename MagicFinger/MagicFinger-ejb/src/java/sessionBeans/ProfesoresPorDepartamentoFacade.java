/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.ProfesoresPorDepartamento;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    
}
