package manageBeans;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author MiiLy
 */
@Named(value = "cursos")
@RequestScoped
public class cursos {

    /**
     * Creates a new instance of cursos
     */
    public cursos() {
    }
}
