/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.innova.session;

import java.util.List;
import javax.ejb.Local;
import org.innova.entity.Clientes;

/**
 *
 * @author nestor
 */
@Local
public interface ClientesFacadeLocal {

    Clientes create(Clientes clientes);

    Clientes edit(Clientes clientes);

    void remove(Clientes clientes);

    Clientes find(Object id);

    List<Clientes> findAll();

    List<Clientes> findRange(int[] range);

    int count();

    public void actualizar(Clientes cliente);
    
}
