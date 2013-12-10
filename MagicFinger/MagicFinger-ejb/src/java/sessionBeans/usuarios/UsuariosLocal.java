/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans.usuarios;

import entity.User;
import javax.ejb.Local;

/**
 *
 * @author lol
 */
@Local
public interface UsuariosLocal {

    public User getRol(String nombre);
}
