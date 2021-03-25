/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.innova.session;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.innova.entity.Usuarios;

/**
 *
 * @author nestor
 */
@Stateless
public class UsuariosFacade extends AbstractFacade<Usuarios> implements UsuariosFacadeLocal {

    @PersistenceContext(unitName = "InnovaPersistenceUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuariosFacade() {
        super(Usuarios.class);
    }

    @Override
    public void actualizar(Usuarios usuario)  {
        if(usuario.getIdUsuario()==null){
//            System.out.println("Id Generado "+getId());
            create(usuario);
        }else{
            edit(usuario);
        }
    }

    
    public Integer getId() {
        TypedQuery query = em.createNamedQuery("Usuarios.findLastId", Usuarios.class);
        List<Usuarios> listUsuario = query.getResultList();
        Integer id = 1;
        if(listUsuario.size()>0){
            id = listUsuario.get(0).getIdUsuario()+1;
        }
        return id;
    }

    @Override
    public List<Usuarios> findAllOrder() {
        TypedQuery query = em.createNamedQuery("Usuarios.findAll", Usuarios.class);
        List<Usuarios> listUsuario = query.getResultList();
        return listUsuario;
    }

}
