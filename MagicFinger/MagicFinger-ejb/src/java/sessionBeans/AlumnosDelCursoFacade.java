/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.AlumnosDelCurso;
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
public class AlumnosDelCursoFacade extends AbstractFacade<AlumnosDelCurso> implements AlumnosDelCursoFacadeLocal {
    @PersistenceContext(unitName = "MagicFinger-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AlumnosDelCursoFacade() {
        super(AlumnosDelCurso.class);
    }
    @Override
    public List BuscarPorIdUniversidad(int idUniversidad){
        Query q = em.createNamedQuery("AlumnoDelCurso.findByUniversidad").setParameter("idUniversidad", idUniversidad);
        List listado = q.getResultList();
        return listado;
    }
    @Override
    public AlumnosDelCurso BuscarPorIdAlumno(int idAlumno){
        
        Query q = em.createNamedQuery("AlumnosDelCurso.findByAluIdAlumno").setParameter("aluIdAlumno", idAlumno);
        AlumnosDelCurso listado = (AlumnosDelCurso) q.getResultList().get(0);
        return listado;
    }
    
    
}
