/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.innova.session;

import java.util.List;
import javax.ejb.Local;
import org.innova.entity.Usuarios;

/**
 *
 * @author nestor
 */
@Local
public interface UsuariosFacadeLocal {

    Usuarios create(Usuarios usuarios);

    Usuarios edit(Usuarios usuarios);

    void remove(Usuarios usuarios);

    Usuarios find(Object id);

    List<Usuarios> findAll();

    List<Usuarios> findRange(int[] range);

    int count();

    public void actualizar(Usuarios usuario);
    
    List<Usuarios> findAllOrder();
    
}
