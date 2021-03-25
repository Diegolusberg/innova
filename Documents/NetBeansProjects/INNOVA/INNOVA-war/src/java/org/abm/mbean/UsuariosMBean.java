/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.abm.mbean;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.innova.entity.Usuarios;
import org.innova.session.UsuariosFacadeLocal;

/**
 *
 * @author nestor
 */
@Named(value = "usuariosMBean")
@SessionScoped
public class UsuariosMBean implements Serializable {

    @EJB
    private UsuariosFacadeLocal usuariosFacade;

    private Usuarios usuario;

    public UsuariosMBean() {
    }

    public List<Usuarios> getUsuarios() {
        return usuariosFacade.findAll();
    }

    public String actualizar() {
        try {
            usuariosFacade.actualizar(usuario);
            String mensaje = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "msg").getString("label.guardadoConExito");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", mensaje));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "LISTA_USUARIO";
    }

    public String irALaConsulta() {
        System.out.println("Volver a Consulta");
        return "LISTA_USUARIO";
    }

    public String editar(Usuarios usuario) {
        System.out.println("*** editar ***");
        this.usuario = usuario;
        return "DETALLE_USUARIO";
    }

    public String nuevo() {
        System.out.println("*** nuevo ***");
        this.usuario = new Usuarios();
        this.usuario.setIdUsuario(0);
        this.usuario.setNombre(" ");
        this.usuario.setApellido(" ");
        return "DETALLE_USUARIO";
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public void eliminar(Usuarios usuario) {
        System.out.println("***Eliminar***");
        usuariosFacade.remove(usuario);
        String mensaje = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "msg").getString("label.eliminadoConExito");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", mensaje));

    }

}
