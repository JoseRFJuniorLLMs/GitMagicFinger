/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans.asignaturas;

import entity.Alumno;
import entity.AlumnosDelCurso;
import entity.Asistencia;
import entity.BloqueClase;
import entity.Curso;
import java.util.Date;
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
public class asistenciaSB implements asistenciaSBLocal {

    @PersistenceContext(unitName = "MagicFinger-ejbPU")
    private EntityManager em;

    @Override
    public Asistencia alumnoAsiste(AlumnosDelCurso AlumnosDelCurso, BloqueClase bloque, Date fecha) {
        Query q = em.createNamedQuery("Asistencia.findAsistencia").setParameter("AlumnosDelCurso", AlumnosDelCurso).setParameter("bloqueClaseId", bloque.getIdBloque()).setParameter("fecha", fecha);
        List<Asistencia> listado = q.getResultList();
        if (!listado.isEmpty()) {
            return listado.get(0);
        }
        return null;
    }
    @Override
    public List<Asistencia> cuentaAsistencia(Alumno alumno, Curso curso){
        Query q = em.createNamedQuery("Asistencia.CountAsistencia").setParameter("Alumno", alumno).setParameter("Curso", curso);
        return q.getResultList();
        
    }
    public List<Asistencia> cuentaAsistenciaSuspendida(Alumno alumno, Curso curso){
        Query q = em.createNamedQuery("Asistencia.CountAsistenciaNoValida").setParameter("Alumno", alumno).setParameter("Curso", curso);
        return q.getResultList();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public void persist(Object object) {
        em.persist(object);
    }
}
