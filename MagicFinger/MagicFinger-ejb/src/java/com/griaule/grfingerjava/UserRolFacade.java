/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.griaule.grfingerjava;

import entity.UserRol;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author lol
 */
@Stateless
public class UserRolFacade extends AbstractFacade<UserRol> implements UserRolFacadeLocal {
    @PersistenceContext(unitName = "MagicFinger-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserRolFacade() {
        super(UserRol.class);
    }
    
}
