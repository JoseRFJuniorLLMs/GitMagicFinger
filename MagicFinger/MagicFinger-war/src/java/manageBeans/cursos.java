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
    String pass;
    /**
     * Creates a new instance of cursos
     */
    public cursos() {
        
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }


}
