/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.innova.session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.innova.entity.Tipomovimiento;

/**
 *
 * @author nestor
 */
@Stateless
public class TipomovimientoFacade extends AbstractFacade<Tipomovimiento> implements TipomovimientoFacadeLocal {

    @PersistenceContext(unitName = "InnovaPersistenceUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipomovimientoFacade() {
        super(Tipomovimiento.class);
    }

    @Override
    public void actualizar(Tipomovimiento tipomovimiento) {
        if(tipomovimiento.getIdTipomovimiento()==null){
            create(tipomovimiento);
        }else{
            edit(tipomovimiento);
        }
    }
    
}
