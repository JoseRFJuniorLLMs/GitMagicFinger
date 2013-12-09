/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans.usuarios;

import entity.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author lol
 */
@Stateless
public class Usuarios implements UsuariosLocal {
    @PersistenceContext(unitName = "MagicFinger-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    @Override
    public User getRol(String nombre){
       Query q = em.createNamedQuery("User.findByUsuario").setParameter("usuario", nombre);
        List<User> listUsuario = q.getResultList();
        return listUsuario.get(0);
    }

}
