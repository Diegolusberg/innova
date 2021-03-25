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
import org.innova.entity.Movimiento;

/**
 *
 * @author nestor
 */
@Stateless
public class MovimientoFacade extends AbstractFacade<Movimiento> implements MovimientoFacadeLocal {

    @PersistenceContext(unitName = "InnovaPersistenceUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MovimientoFacade() {
        super(Movimiento.class);
    }

    @Override
    public int lastSec(Integer id) {
        TypedQuery query = em.createNamedQuery("Movimiento.findByIdTipomov", Movimiento.class).setParameter("idTipomov", id);
        List<Movimiento> resultado = query.getResultList();
        if(resultado.size()>0){
            return resultado.get(0).getMovimientoPK().getSecuenciamov()+1;
        }else{
            return 1;
        }
    }

    @Override
    public List<Movimiento> findByAuto(int i, Integer idAuto) {
        TypedQuery query = em.createNamedQuery("Movimiento.findByIdAuto", Movimiento.class).setParameter("idAuto", idAuto).setParameter("idTipomov", i);
        List<Movimiento> resultado = query.getResultList();
        return resultado;
    }
    
}
