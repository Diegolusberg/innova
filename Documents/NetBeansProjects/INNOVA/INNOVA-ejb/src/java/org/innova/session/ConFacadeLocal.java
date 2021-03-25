/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.innova.session;

import java.sql.Connection;
import javax.ejb.Local;

/**
 *
 * @author nestor
 */
@Local
public interface ConFacadeLocal {

    Connection con();
    
}
