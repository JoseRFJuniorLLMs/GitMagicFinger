/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans.asignaturas;

import entity.Profesor;
import entity.ProfesoresPorCurso;
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
public class ProfesorPorCurso implements ProfesorPorCursoLocal {
    @PersistenceContext(unitName = "MagicFinger-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }
    
    @Override
    public List<ProfesoresPorCurso> findByProfesor(Integer idProfesor){
        Query q= em.createNamedQuery("ProfesoresPorCurso.findByProfesorId").setParameter("profesorId", idProfesor);
        List<ProfesoresPorCurso> listaCursos = q.getResultList();
        return listaCursos;
    }
    
}
