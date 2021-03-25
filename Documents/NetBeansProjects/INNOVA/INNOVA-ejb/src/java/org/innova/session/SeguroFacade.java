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
import org.innova.entity.Seguro;
import org.innova.entity.SeguroPK;

/**
 *
 * @author nestor
 */
@Stateless
public class SeguroFacade extends AbstractFacade<Seguro> implements SeguroFacadeLocal {

    @PersistenceContext(unitName = "InnovaPersistenceUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SeguroFacade() {
        super(Seguro.class);
    }

    @Override
    public List<Seguro> findAllOrdered() {
        TypedQuery<Seguro> query = em.createNamedQuery("Seguro.findAll",Seguro.class);
        List<Seguro> resultado = query.getResultList();
        return resultado;
    }

    @Override
    public SeguroPK getSeguroPK(Integer idAuto) {
        TypedQuery<SeguroPK> query = em.createNamedQuery("Seguro.findByIdAuto", SeguroPK.class).setParameter("idAuto", idAuto);
        List<SeguroPK> resultado = query.getResultList();
        SeguroPK spk = new SeguroPK();
        spk.setIdAuto(idAuto);
        if(resultado.size()>0){
            spk.setIdSeguro(resultado.get(0).getIdSeguro() + 1);
        }else{
            spk.setIdSeguro(1);
        }
        return spk;
    }
    
}
