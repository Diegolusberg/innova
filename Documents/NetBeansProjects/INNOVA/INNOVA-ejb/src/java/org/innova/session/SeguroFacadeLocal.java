/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.innova.session;

import java.util.List;
import javax.ejb.Local;
import org.innova.entity.Seguro;
import org.innova.entity.SeguroPK;

/**
 *
 * @author nestor
 */
@Local
public interface SeguroFacadeLocal {

    Seguro create(Seguro seguro);

    Seguro edit(Seguro seguro);

    void remove(Seguro seguro);

    Seguro find(Object id);

    List<Seguro> findAll();

    List<Seguro> findRange(int[] range);

    int count();

    public List<Seguro> findAllOrdered();

    public SeguroPK getSeguroPK(Integer idAuto);
    
}
