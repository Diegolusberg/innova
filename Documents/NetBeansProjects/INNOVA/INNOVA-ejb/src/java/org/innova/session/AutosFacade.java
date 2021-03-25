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
import org.innova.entity.Autos;

/**
 *
 * @author nestor
 */
@Stateless
public class AutosFacade extends AbstractFacade<Autos> implements AutosFacadeLocal {

    @PersistenceContext(unitName = "InnovaPersistenceUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AutosFacade() {
        super(Autos.class);
    }
    
    @Override
    public Autos actualizar(Autos auto)  {
        if(auto.getIdAuto()==null){
//            System.out.println("Id Generado "+getId());
            auto = create(auto);
            
        }else{
            auto = edit(auto);
        }
        return auto;
    }

    @Override
    public Integer getId() {
        TypedQuery<Autos> query = em.createNamedQuery("Autos.findLastId", Autos.class);
        List<Autos> resultado = query.getResultList();
        Integer id = 0;
        try {
            if(resultado.size()>0){
                id = resultado.get(0).getIdAuto();
                return id;
            }
        } catch (Exception e) {
            System.out.println("Error en AutosFacade Linea 56"+e.getMessage());
        }
        return id;
    }

    @Override
    public List<Autos> findAllOrdered() {
        TypedQuery<Autos> query = em.createNamedQuery("Autos.findAll",Autos.class);
        List<Autos> resultado = query.getResultList();
        return resultado;
    }

    @Override
    public List<Autos> findById(Integer id) {
        TypedQuery<Autos> query = em.createNamedQuery("Autos.findByIdAuto",Autos.class).setParameter("idAuto", id);
        List<Autos> resultado = query.getResultList();
        return resultado;
    }

    @Override
    public List<Autos> findAllOrdDisp() {
        TypedQuery<Autos> query = em.createNamedQuery("Autos.findAllDisp",Autos.class);
        List<Autos> resultado = query.getResultList();
        return resultado;
    }
    
}
