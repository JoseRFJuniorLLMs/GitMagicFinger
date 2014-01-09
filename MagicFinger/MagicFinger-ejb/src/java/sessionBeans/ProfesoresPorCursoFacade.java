/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.ProfesoresPorCurso;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author chevo
 */
@Stateless
public class ProfesoresPorCursoFacade extends AbstractFacade<ProfesoresPorCurso> implements ProfesoresPorCursoFacadeLocal {
    @PersistenceContext(unitName = "MagicFinger-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProfesoresPorCursoFacade() {
        super(ProfesoresPorCurso.class);
    }
    
}
