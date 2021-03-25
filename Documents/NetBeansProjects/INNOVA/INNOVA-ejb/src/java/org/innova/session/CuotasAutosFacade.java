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
import org.innova.entity.CuotasAutos;

/**
 *
 * @author nestor
 */
@Stateless
public class CuotasAutosFacade extends AbstractFacade<CuotasAutos> implements CuotasAutosFacadeLocal {

    @PersistenceContext(unitName = "InnovaPersistenceUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CuotasAutosFacade() {
        super(CuotasAutos.class);
    }

    @Override
    public List<CuotasAutos> findById(Integer id) {
        TypedQuery query = em.createNamedQuery("CuotasAutos.findByIdAuto", CuotasAutos.class).setParameter("idAuto", id);
        List<CuotasAutos> resultado = query.getResultList();
        return resultado;
    }

    @Override
    public void pagarCuota(CuotasAutos cuotaAuto) {
        edit(cuotaAuto);
    }

    
    
}
