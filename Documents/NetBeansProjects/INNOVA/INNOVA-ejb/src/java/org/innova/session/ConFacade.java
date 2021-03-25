/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.innova.session;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.ejb.Stateless;

/**
 *
 * @author nestor
 */
@Stateless
public class ConFacade implements ConFacadeLocal {

    @Override
    public Connection con() {
        Connection con;
        try {
          Class.forName("org.postgresql.Driver");
          con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/innova","postgres","nestor210505");
          System.out.println("Conexion establecida");
         // JOptionPane.showMessageDialog(null, "Conexion establecida");
          return con;
        } catch (ClassNotFoundException | SQLException e) {
         System.out.println("Error de conexion");
         return null;
         //JOptionPane.showMessageDialog(null, "Error de conexion"+e);
        }
        
    }

    
    
}
