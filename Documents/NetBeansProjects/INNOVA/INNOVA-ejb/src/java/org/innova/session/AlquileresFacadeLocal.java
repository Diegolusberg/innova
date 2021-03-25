/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.innova.session;

import java.util.List;
import javax.ejb.Local;
import org.innova.entity.Alquileres;

/**
 *
 * @author nestor
 */
@Local
public interface AlquileresFacadeLocal {

    Alquileres create(Alquileres alquileres);

    Alquileres edit(Alquileres alquileres);

    void remove(Alquileres alquileres);

    Alquileres find(Object id);

    List<Alquileres> findAll();

    List<Alquileres> findRange(int[] range);

    int count();
    
    void actualizar(Alquileres alquiler);
    
    Integer getId();
    
    void anular(Integer codigo) throws Exception;
    
    List<Alquileres> findAllOrder();
    
     //Integer calcularMonto(Integer );
}
