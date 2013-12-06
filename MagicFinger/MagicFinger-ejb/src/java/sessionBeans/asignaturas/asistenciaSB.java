/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans.asignaturas;

import entity.Alumno;
import entity.AlumnosDelCurso;
import entity.Asignatura;
import entity.BloqueClase;
import entity.TipoAsignatura;
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
    public int alumnoAsiste(AlumnosDelCurso AlumnosDelCurso, BloqueClase bloque, Date fecha ) {
        Query q= em.createNamedQuery("Asistencia.findAsistencia").setParameter("AlumnosDelCurso", AlumnosDelCurso ).setParameter("bloqueClaseId", bloque.getIdBloque()).setParameter("fecha", fecha);
        List<asistenciaSB> listado = q.getResultList();
        return listado.size();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public void persist(Object object) {
        em.persist(object);
    }
    

}
