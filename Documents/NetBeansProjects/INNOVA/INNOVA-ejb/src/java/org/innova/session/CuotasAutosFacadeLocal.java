/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.innova.session;

import java.util.List;
import javax.ejb.Local;
import org.innova.entity.CuotasAutos;

/**
 *
 * @author nestor
 */
@Local
public interface CuotasAutosFacadeLocal {

    CuotasAutos create(CuotasAutos cuotasAutos);

    CuotasAutos edit(CuotasAutos cuotasAutos);

    void remove(CuotasAutos cuotasAutos);

    CuotasAutos find(Object id);

    List<CuotasAutos> findAll();

    List<CuotasAutos> findRange(int[] range);

    int count();
    
    List<CuotasAutos> findById(Integer id);
    
    public void pagarCuota(CuotasAutos cuotaAuto);
    
}
