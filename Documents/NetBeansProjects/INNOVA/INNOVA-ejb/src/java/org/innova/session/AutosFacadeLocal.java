/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.innova.session;

import java.util.List;
import javax.ejb.Local;
import org.innova.entity.Autos;

/**
 *
 * @author nestor
 */
@Local
public interface AutosFacadeLocal {

    Autos create(Autos autos);

    Autos edit(Autos autos);

    void remove(Autos autos);

    Autos find(Object id);

    List<Autos> findAll();
    
    List<Autos> findById(Integer id);
    
    List<Autos> findAllOrdered();
    
    List<Autos> findAllOrdDisp();

    List<Autos> findRange(int[] range);

    int count();
    
    public Autos actualizar(Autos auto);
    
    Integer getId();
    
}
