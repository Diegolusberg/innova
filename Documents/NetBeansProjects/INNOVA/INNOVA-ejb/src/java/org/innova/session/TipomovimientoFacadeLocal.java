/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.innova.session;

import java.util.List;
import javax.ejb.Local;
import org.innova.entity.Tipomovimiento;

/**
 *
 * @author nestor
 */
@Local
public interface TipomovimientoFacadeLocal {

    Tipomovimiento create(Tipomovimiento tipomovimiento);

    Tipomovimiento edit(Tipomovimiento tipomovimiento);

    void remove(Tipomovimiento tipomovimiento);

    Tipomovimiento find(Object id);

    List<Tipomovimiento> findAll();

    List<Tipomovimiento> findRange(int[] range);

    int count();

    public void actualizar(Tipomovimiento tipomovimiento);
    
}
