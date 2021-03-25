/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.abm.mbean;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.innova.entity.Autos;
import org.innova.entity.Movimiento;
import org.innova.entity.MovimientoPK;
import org.innova.entity.Seguro;
import org.innova.entity.SeguroPK;
import org.innova.session.AutosFacadeLocal;
import org.innova.session.MovimientoFacadeLocal;
import org.innova.session.SeguroFacadeLocal;
import org.innova.session.TipomovimientoFacadeLocal;

/**
 *
 * @author nestor
 */
@Named(value = "seguroMBean")
@SessionScoped
public class SeguroMBean implements Serializable {

    @EJB
    private TipomovimientoFacadeLocal tipomovimientoFacade;

    @EJB
    private MovimientoFacadeLocal movimientoFacade;

    @EJB
    private AutosFacadeLocal autosFacade;

    @EJB
    private SeguroFacadeLocal seguroFacade;
    
    
    
    private Seguro seguro;
    private Autos autos;
//    private String fchVenc;
//
//    public String getFchVenc() {
//        DateFormat fchFormat = new SimpleDateFormat("yyyy-MM-dd",new Locale("es", "ES"));
//        fchVenc = fchFormat.format(Calendar.getInstance().getTime());
//        if(seguro.getFchVenc()!=null){
//            fchVenc = fchFormat.format(seguro.getFchVenc());
//        }
//        return fchVenc;
//    }
//
//    public void setFchVenc(String fchVenc) throws ParseException {
//        if(!fchVenc.equals("")){
//            this.fchVenc = fchVenc;
//            Date fchVncSeguro = new SimpleDateFormat("yyyy-MM-dd",new Locale("es", "ES")).parse(this.fchVenc);
//            this.seguro.setFchVenc(fchVncSeguro);
//        }
//    }

    public Seguro getSeguro() {
        return seguro;
    }

    public Autos getAutos() {
        return autos;
    }

    public void setAutos(Autos autos) {
        this.autos = autos;
    }

    public void setSeguro(Seguro seguro) {
        this.seguro = seguro;
    }

    public List<Seguro> getSeguros(){
        return seguroFacade.findAllOrdered();
    }
    
    public List<Autos> getAutoses(){
        return autosFacade.findAllOrdered();
    }
    
    public String actualizar(){
        if(this.seguro.getSeguroPK().getIdSeguro()==0){
            SeguroPK spk = seguroFacade.getSeguroPK(this.seguro.getAutos().getIdAuto());
            seguro.setSeguroPK(spk);
            seguro.setEstadoSeguro(1);
            seguroFacade.create(seguro);
            MovimientoPK mpk = new MovimientoPK();
                mpk.setIdTipomov(4);
                mpk.setSecuenciamov(movimientoFacade.lastSec(4));
                Movimiento m = new Movimiento();
                m.setMovimientoPK(mpk);
                m.setFechamov(new Date());
                m.setIdAuto(this.seguro.getAutos().getIdAuto());
                m.setMonto(this.seguro.getValorSeguro());
                m.setSituacion(1);
                m.setTipomovimiento(tipomovimientoFacade.find(4));
                movimientoFacade.create(m);
		String mensaje = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "msg").getString("label.guardadoConExito");
            	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", mensaje));
        }else{
            seguroFacade.edit(seguro);
        }
        return "LISTA_SEGURO";
    }
    
    public String nuevo(){
        this.seguro = new Seguro(0,0);
        this.autos = new Autos();
        this.seguro.setAutos(autos);
        return "DETALLE_SEGURO";
    }
    
    public String editar(Seguro seguro){
        this.seguro = seguro;
        return "DETALLE_SEGURO";
    }
    
    public String irALaConsulta(){
        return "LISTA_SEGURO";
    }
    
    public SeguroMBean() {
    }
    
//    public void mensaje(){
//        String mensaje = FacesContext.getCurrentInstance().getApplication()
//					.getResourceBundle(FacesContext.getCurrentInstance(), "msg").getString("label.guardadoConExito");
//			FacesContext.getCurrentInstance().addMessage(null,
//					new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", mensaje));
//    }
    
}
