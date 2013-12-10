/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans.asignaturas;

import com.griaule.grfingerjava.MatchingContext;
import com.griaule.grfingerjava.Template;
import entity.Alumno;
import entity.Asignatura;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
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
public class Asignaturas implements AsignaturasLocal {

    @PersistenceContext(unitName = "MagicFinger-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }
}

/*
 @Override
 public List<Asignatura> ListAsignature() {
 Query q= em.createNamedQuery("Asignatura.findAll");
 List<Asignatura> ListAsignatura = q.getResultList();
 return ListAsignatura;
 }
    
 @Override
 public List<AsignaturaAlumno> ListAlumnosPorAsignatura(Asignatura asignaturaId) {
 Query q= em.createNamedQuery("AsignaturaAlumno.findByAsignaturaId").setParameter("asignaturaId", asignaturaId);
 List<AsignaturaAlumno> ListaAsistencia = q.getResultList();
 return ListaAsistencia;
 }
    
 @Override
 public void CrearAlumno(String StringHuella) {
 Alumno nuevo =  new Alumno();
 nuevo.setNombre("Sebastian");
 nuevo.setApellidop("Araya");
 byte[] templeByte = Base64.decode(StringHuella);
 nuevo.setHuella(templeByte);
 em.persist(nuevo);
 }
    
 @Override
 public Alumno CompareFingerPrint(String StringHuella) {
 Query q= em.createNamedQuery("Alumno.findAll");
 List<Alumno> listAlumno = q.getResultList();
            
 byte[] templeByte = Base64.decode(StringHuella);
 for(int i=0;i<listAlumno.size();i++){
 if(listAlumno.get(i).getHuella()!=null){
 try {
 Template template= new Template(templeByte);
 byte templateBuffer[] = listAlumno.get(i).getHuella();
 Template referenceTemplate = new Template(templateBuffer);
 MatchingContext fingerprintSDK = new MatchingContext();
 boolean coinciden = fingerprintSDK.verify(template,referenceTemplate);
 if(coinciden){
 System.out.println("----------> "+listAlumno.get(i));
 return listAlumno.get(i);
 }
                        
 } catch (Exception ex) {}
 }
 }System.out.println("---------->for<");
 //CrearAlumno(StringHuella);
 return null;
    
 }
 */
//    @Override
//    @TransactionAttribute(TransactionAttributeType.REQUIRED)
//    public void marcarAsistencia(Asignatura asignatura, Alumno alumno){
//        Query q = em.createNamedQuery("AsignaturaAlumno.findByIdAlumAndIdAsig").setParameter("idAsignaturaAlumno", alumno).setParameter("asignaturaId", asignatura);
//        List<AsignaturaAlumno> listAlumno = q.getResultList();
//        if(listAlumno!=null){
//            Asistencia nuevo =  new Asistencia();
//            java.util.Date fecha = new Date();
//            nuevo.setFecha(fecha);
//            AsignaturaAlumno asignaturaAlumno = listAlumno.get(0);
//            nuevo.setAsignaturaAlumnoId(asignaturaAlumno);
//            nuevo.setAsistencia(1);// es presente
//            em.persist(nuevo);
//        }
//    }
//    @Override
//    @TransactionAttribute(TransactionAttributeType.REQUIRED)
//    public Alumno CompareFingerPrint(String StringHuella, Asignatura asignature) {
//            Query q= em.createNamedQuery("AsignaturaAlumno.findByAsignaturaId").setParameter("asignaturaId", asignature);;
//            List<AsignaturaAlumno> listAlumno = q.getResultList();
//            byte[] templeByte = Base64.decode(StringHuella);
//            Template template= new Template(templeByte);
//            for(int i=0;i<listAlumno.size();i++){
//                if(listAlumno.get(i).getAlumnoId().getHuella()!=null){
//                    byte templateBuffer[] = listAlumno.get(i).getAlumnoId().getHuella();
//                    Template referenceTemplate = new Template(templateBuffer);
//                    try {
//                        MatchingContext fingerprintSDK = new MatchingContext();
//                        boolean coinciden = fingerprintSDK.verify(template,referenceTemplate);
//                        if(coinciden){
//                           marcarAsistencia(asignature,listAlumno.get(i).getAlumnoId());
//                           return listAlumno.get(i).getAlumnoId();
//                        }
//                    } catch (GrFingerJavaException ex) {}
//                }
//            }
//        return null;
//    }
//    
//    
//}
