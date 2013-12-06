/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans.alumnos;

import com.griaule.grfingerjava.MatchingContext;
import com.griaule.grfingerjava.Template;
import entity.Alumno;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

/**
 *
 * @author chevo
 */
@Stateless
public class alumnos implements alumnosLocal {
    @PersistenceContext(unitName = "MagicFinger-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }
    
    @Override
    public Alumno CompareFingerPrint(String StringHuella) {
            Query q= em.createNamedQuery("Alumno.findAll");
            List<Alumno> listAlumno = q.getResultList();
            
            byte[] templeByte = Base64.decode(StringHuella);
            for(int i=0;i<listAlumno.size();i++){
                if(listAlumno.get(i).getHuella1()!=null){
                   try {
                       System.out.println("___________________comparando a " + listAlumno.get(i).getNombre() );
                       System.out.println("___________________huella " +StringHuella);
                        Template template= new Template(templeByte);
                        byte templateBuffer[] = listAlumno.get(i).getHuella1();
                        Template referenceTemplate = new Template(templateBuffer);
                        MatchingContext fingerprintSDK = new MatchingContext();
                        boolean coinciden = fingerprintSDK.verify(template,referenceTemplate);
                        if(coinciden){
                           return listAlumno.get(i);
                        }
                        
                    } catch (Exception ex) {}
                }
            }
           //CrearAlumno(StringHuella);
        return null;
    
    }
}
