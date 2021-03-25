/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.innova.util.session;

import javax.ejb.Remote;
import org.innova.entity.Usuarios;

@Remote
public interface LoginSessionRemote {

    Usuarios validarUsuario(String name, String password);
}
