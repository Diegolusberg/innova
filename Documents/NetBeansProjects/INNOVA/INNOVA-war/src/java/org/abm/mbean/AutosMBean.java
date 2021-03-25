/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.abm.mbean;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.innova.entity.Autos;
import org.innova.entity.CuotasAutos;
import org.innova.entity.CuotasAutosPK;
import org.innova.entity.Movimiento;
import org.innova.entity.MovimientoPK;
import org.innova.session.AutosFacadeLocal;
import org.innova.session.CuotasAutosFacadeLocal;
import org.innova.session.MovimientoFacadeLocal;
import org.innova.session.TipomovimientoFacadeLocal;

/**
 *
 * @author nestor
 */
@Named(value = "autoMBean")
@SessionScoped
public class AutosMBean implements Serializable{

    @EJB
    private TipomovimientoFacadeLocal tipomovimientoFacade;

    @EJB
    private MovimientoFacadeLocal movimientoFacade;

    @EJB
    private CuotasAutosFacadeLocal cuotasAutosFacade;

    @EJB
    private AutosFacadeLocal autosFacade;
    
    private Autos auto;
    private String fchCompra;
    private boolean mostrarFin;
    private Integer precio;
    private CuotasAutos cuotaAuto;

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public Autos getAuto() {
        return auto;
    }

    public void setAuto(Autos auto) {
        this.auto = auto;
    }

    public String getFchCompra() {
        DateFormat fchFormat = new SimpleDateFormat("yyyy-MM-dd",new Locale("es-ES"));
        fchCompra = fchFormat.format(Calendar.getInstance().getTime());
        if(auto.getFechaCompra()!=null){
            fchCompra = fchFormat.format(auto.getFechaCompra());
        }
        return fchCompra;
    }

    public void setFchCompra(String fchCompra) throws ParseException {
        if(!fchCompra.equals("")){
            this.fchCompra = fchCompra;
            Date fchCmpraAuto = new SimpleDateFormat("yyyy-MM-dd",new Locale("es-ES")).parse(this.fchCompra);
            this.auto.setFechaCompra(fchCmpraAuto);
        }
    }
    
    public AutosMBean() {
    }
    
    public List<Autos> getAutoses() {
        return autosFacade.findAllOrdered();
    }
    
    public String actualizar() {
        try {
            if(this.auto.getFormaCompra().equals("FINANCIADO")){
                CuotasAutos ca = new CuotasAutos();
                CuotasAutosPK capk;
                this.auto.setPrecio(this.auto.getEntrega()+(this.auto.getCantCuotas()*this.auto.getValCuota()));
                this.auto = autosFacade.actualizar(this.auto);
                Integer idGenerado= autosFacade.getId();
                System.out.println("Id Generado: "+idGenerado);
                if (this.auto.getIdAuto() == 0) {
                    this.auto.setIdAuto(idGenerado);
                }
                Calendar fch = Calendar.getInstance();
                fch.setTime(this.auto.getFechaCompra());
                Integer mes = fch.get(Calendar.MONTH);
                Integer anho = fch.get(Calendar.YEAR);
                if(this.auto.getEntrega()>0){
                    MovimientoPK mpk = new MovimientoPK();
                    mpk.setIdTipomov(2);
                    System.out.println("secuencia = " + mpk.getSecuenciamov());
                    mpk.setSecuenciamov(movimientoFacade.lastSec(2));
                    System.out.println("secuencia = " + mpk.getSecuenciamov());
                    Movimiento m = new Movimiento();
                    m.setMovimientoPK(mpk);
                    m.setFechamov(new Date());
                    m.setIdAuto(this.auto.getIdAuto());
                    m.setMonto(this.auto.getEntrega());
                    m.setSituacion(1);
                    m.setTipomovimiento(tipomovimientoFacade.find(2));
                    movimientoFacade.create(m);
                }

                for(int i=0;i<this.auto.getCantCuotas();i++){
                    capk=new CuotasAutosPK(this.auto.getIdAuto(), i+1);
                    ca.setCuotasAutosPK(capk);
                    ca.setEstado('P');
                    ca.setValorCuota(this.auto.getValCuota());
                    Calendar fchVencCal = Calendar.getInstance();
                    Date fchVenc = new Date();
                    if(mes==12){
                        mes=1;
                        anho+=1;
                    }else{
                        mes += 1;
                    }
                    fchVencCal.set(anho, mes, 10);
                    fchVenc = fchVencCal.getTime();
                    ca.setVencimientoCuota(fchVenc);
                    cuotasAutosFacade.create(ca);
                }

            }else{

                this.auto.setCantCuotas(0);
                this.auto.setEntrega(0);
                this.auto.setValCuota(0);
                this.auto.toString();
                this.auto = autosFacade.actualizar(this.auto);
                Integer idGenerado= autosFacade.getId();
                System.out.println("Id Generado: "+idGenerado);
                this.auto.setIdAuto(idGenerado);
                try {
                    if(this.auto.getPrecio()>0){
                        List<Movimiento> movlist = movimientoFacade.findByAuto(1,this.auto.getIdAuto());
                        Movimiento m = new Movimiento();
                        if(movlist.size()>0){
                            m = movlist.get(0);
                            m.setMonto(this.auto.getPrecio());
                            movimientoFacade.edit(m);
                        }else{
                            m.setMovimientoPK(new MovimientoPK(1, movimientoFacade.lastSec(1)));
                            m.setIdAuto(this.auto.getIdAuto());
                            m.setFechamov(new Date());
                            m.setMonto(this.auto.getPrecio());
                            m.setSituacion(1);
                            movimientoFacade.create(m);
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Error en AutosMBean Linea 176: "+e.getMessage());
                }

                try {
                    CuotasAutos cuota= new CuotasAutos();
                    List<CuotasAutos> cuotaList = cuotasAutosFacade.findById(this.auto.getIdAuto());
                    if(cuotaList.size()>0){
                        for(int i = 0;i<cuotaList.size();i++){
                            cuota= cuotaList.get(i);
                            cuotasAutosFacade.remove(cuota);
                        }
                    }

                } catch (Exception e) {
                    System.out.println("Error AutosMBean Linea 120: "+e.getMessage());
                }
            }
            String mensaje = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "msg").getString("label.guardadoConExito");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", mensaje));
        } catch (Exception e) {
            System.out.println("Error al Actualizar Auto: "+e.getLocalizedMessage());
        }
        return "LISTA_AUTO";
    }

	public String irALaConsulta() {
            System.out.println("Volver a Consulta");
		return "LISTA_AUTO";
	}

	public String editar(Autos auto) {
		System.out.println("*** editar ***");
		this.auto = auto;
                this.mostrarFin = this.auto.getFormaCompra().equals("FINANCIADO");
		return "DETALLE_AUTO";
	}

	public String nuevo() {
		System.out.println("*** nuevo ***");
		this.auto = new Autos(0);
                this.auto.setFormaCompra("CONTADO");
		return "DETALLE_AUTO";
	}
        
        public void mostrarFinan(){
            System.out.println("forma de Compra: "+this.auto.getFormaCompra());
            this.mostrarFin = this.auto.getFormaCompra().equals("FINANCIADO");
            //return this.mostrarFin;
        }

    public boolean isMostrarFin() {
        return mostrarFin;
    }

    public void setMostrarFin(boolean mostrarFin) {
        this.mostrarFin = mostrarFin;
    }

    public Date getMaxDate() {
        Calendar currentDate = Calendar.getInstance();
        return currentDate.getTime();
    }
    
    public String irACuotasAutos(Autos idauto) {
        System.out.println("CuotasAuto");
        this.auto = idauto;
        return "LISTA_CUOTA";
    }

    public List<CuotasAutos> getCuotasAutoses() {
        return cuotasAutosFacade.findById(this.auto.getIdAuto());
    }

    public void pagarCuota(CuotasAutos cuota) {
        System.out.println("*** Pagar Cuota ***");
        try {
            cuota.setEstado('C');
            cuota.setFechapago(new Date());
            cuotasAutosFacade.pagarCuota(cuota);
            Movimiento m = new Movimiento();
            m.setMovimientoPK(new MovimientoPK(3, movimientoFacade.lastSec(3)));
            m.setIdAuto(this.auto.getIdAuto());
            m.setFechamov(new Date());
            m.setMonto(this.auto.getValCuota());
            m.setSituacion(1);
            m.setNroCuota(cuota.getCuotasAutosPK().getNroCuota());
            movimientoFacade.create(m);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        
    
}
