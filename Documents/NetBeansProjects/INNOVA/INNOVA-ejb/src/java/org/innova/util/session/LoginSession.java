/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.innova.util.session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.innova.entity.Usuarios;

@Stateless
public class LoginSession implements LoginSessionRemote {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Usuarios validarUsuario(String name, String password) {
        Usuarios usuario = null;
        try {
            String jpql = "SELECT o FROM Usuarios o "
                    + "WHERE o.nombre='" + name + "' AND "
                    + "o.clave='" + password + "'";
            usuario = (Usuarios) entityManager.createQuery(jpql,
                    Usuarios.class).getSingleResult();
        } catch (NoResultException e) {
        }
        return usuario;
    }
}
