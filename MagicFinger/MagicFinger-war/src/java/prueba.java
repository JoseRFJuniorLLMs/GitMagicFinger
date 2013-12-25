/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author chevo
 */
@Named(value = "prueba")
@SessionScoped
public class prueba implements Serializable {
    
    /**
     * Creates a new instance of prueba
     */
    public prueba() {
    }
}
