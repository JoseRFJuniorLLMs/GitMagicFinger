/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans.alumnos;

import entity.Alumno;
import entity.Curso;
import javax.ejb.Local;

/**
 *
 * @author chevo
 */
@Local
public interface alumnosLocal {
    
     Alumno CompareFingerPrint(String StringHuella);
     Alumno CompareFingerPrint(String StringHuella, Curso curso);
}
