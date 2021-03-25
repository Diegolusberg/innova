/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.innova.session;

import java.util.List;
import javax.ejb.Local;
import org.innova.entity.Movimiento;

/**
 *
 * @author nestor
 */
@Local
public interface MovimientoFacadeLocal {

    Movimiento create(Movimiento movimiento);

    Movimiento edit(Movimiento movimiento);

    void remove(Movimiento movimiento);

    Movimiento find(Object id);

    List<Movimiento> findAll();

    List<Movimiento> findRange(int[] range);

    int count();
    
    int lastSec(Integer id);

    List<Movimiento> findByAuto(int i, Integer idAuto);
    
}
