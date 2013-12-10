/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans.alumnos;

import java.awt.image.DataBufferByte;
import com.griaule.grfingerjava.Template;
import entity.Alumno;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import entity.AlumnosDelCurso;
import entity.Curso;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Singleton;
import com.griaule.grfingerjava.*;

/**
 *
 * @author chevo
 */
@Singleton
public class alumnos implements alumnosLocal {

    @PersistenceContext(unitName = "MagicFinger-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    @Override
    public Alumno CompareFingerPrint(String StringHuella) {
        Query q = em.createNamedQuery("Alumno.findAll");
        List<Alumno> listAlumno = q.getResultList();
        byte[] templeByte = Base64.decode(StringHuella);
        for (int i = 0; i < listAlumno.size(); i++) {
            if (listAlumno.get(i).getHuella1() != null) {
                try {
                    byte templateBuffer[] = listAlumno.get(i).getHuella1();
                    Template referenceTemplate = new Template(templateBuffer);
                    int score2[] = new int[1];
                    score2[0] = -1;
                    int z = GrFingerJavaNative.GrVerify(templeByte, templateBuffer, score2, 0);
                    System.out.println("imprimiendo Z = " + z);
                    boolean coinciden = true;
                    if (coinciden) {
                        return listAlumno.get(i);
                    }
                } catch (Exception ex) {
                    System.out.println("_________________________________> exception error");
                }
            }
        }
        return null;
    }

    @Override
    public Alumno CompareFingerPrint(String StringHuella, Curso curso) {
        List<AlumnosDelCurso> temp = curso.getAlumnosDelCursoList();

        byte[] templeByte = Base64.decode(StringHuella);
        Template template = new Template(templeByte);

        for (AlumnosDelCurso alumnosDelCurso : temp) {
            if (alumnosDelCurso.getAlumno().getHuella1() != null) {
                try {
                    byte templateBuffer[] = alumnosDelCurso.getAlumno().getHuella1();
                    Template referenceTemplate = new Template(templateBuffer);
                    MatchingContext fingerprintSDK = new MatchingContext();
                    boolean coinciden = fingerprintSDK.verify(template, referenceTemplate);
                    if (coinciden) {
                        return alumnosDelCurso.getAlumno();
                    }
                } catch (Exception ex) {
                    System.out.println("_________________________________> exception error en comparar huella");
                }
            }
        }
        return null;
    }
}
