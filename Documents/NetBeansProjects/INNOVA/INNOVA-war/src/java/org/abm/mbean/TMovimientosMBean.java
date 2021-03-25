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
import org.innova.entity.Tipomovimiento;
import org.innova.entity.Usuarios;
import org.innova.session.TipomovimientoFacadeLocal;
import org.innova.session.UsuariosFacadeLocal;

/**
 *
 * @author nestor
 */
@Named(value = "tMovimientosMBean")
@SessionScoped
public class TMovimientosMBean implements Serializable {

    @EJB
    private TipomovimientoFacadeLocal tfl;

    private Tipomovimiento tipomovimiento;
    
    
    
    public TMovimientosMBean() {
    }
    
    public List<Tipomovimiento> getTipomovimientos(){
        return tfl.findAll();
    }
    
    public String actualizar() {
		try {
			tfl.actualizar(tipomovimiento);
                        String mensaje = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "msg").getString("label.guardadoConExito");
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", mensaje));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "LISTA_TMOVIMIENTO";
	}

	public String irALaConsulta() {
            System.out.println("Volver a Consulta");
		return "LISTA_TMOVIMIENTO";
	}

	public String editar(Tipomovimiento tipomovimiento) {
		System.out.println("*** editar ***");
		this.tipomovimiento = tipomovimiento;
		return "DETALLE_TMOVIMIENTO";
	}

	public String nuevo() {
		System.out.println("*** nuevo ***");
		this.tipomovimiento = new Tipomovimiento();
		this.tipomovimiento.setIdTipomovimiento(0);
		return "DETALLE_TMOVIMIENTO";
	}

    public Tipomovimiento getTipomovimiento() {
        return tipomovimiento;
    }

    public void setTipomovimiento(Tipomovimiento tipomovimiento) {
        this.tipomovimiento = tipomovimiento;
    }

    

	
}
