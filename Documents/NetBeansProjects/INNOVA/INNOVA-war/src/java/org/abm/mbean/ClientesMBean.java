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
import org.innova.entity.Clientes;
import org.innova.session.ClientesFacadeLocal;

/**
 *
 * @author nestor
 */
@Named(value = "clientesMBean")
@SessionScoped
public class ClientesMBean implements Serializable {

    @EJB
    private ClientesFacadeLocal clientesFacade;

    private Clientes cliente;
    
    public ClientesMBean() {
    }
    
    public List<Clientes> getClientes(){
        return clientesFacade.findAll();
    }
    
    public String actualizar() {
		try {
			clientesFacade.actualizar(cliente);
            String mensaje = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "msg").getString("label.guardadoConExito");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", mensaje));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "LISTA_CLIENTE";
	}

	public String irALaConsulta() {
            System.out.println("Volver a Consulta");
		return "LISTA_CLIENTE";
	}

	public String editar(Clientes cliente) {
		System.out.println("*** editar ***");
		this.cliente = cliente;
		return "DETALLE_CLIENTE";
	}

	public String nuevo() {
		System.out.println("*** nuevo ***");
		this.cliente = new Clientes();
		this.cliente.setIdCliente(0);
		return "DETALLE_CLIENTE";
	}

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

        
    
}
