package org.abm.mbean;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.innova.entity.Alquileres;
import org.innova.entity.Autos;
import org.innova.entity.Clientes;
import org.innova.entity.Movimiento;
import org.innova.entity.MovimientoPK;
import org.innova.session.AlquileresFacadeLocal;
import org.innova.session.AutosFacadeLocal;
import org.innova.session.ClientesFacadeLocal;
import org.innova.session.MovimientoFacadeLocal;
import org.innova.session.TipomovimientoFacadeLocal;

/**
 *
 * @author Diego Lusberg
 */
@Named(value = "alquilerMbean")
@SessionScoped
public class AlquilerMbean implements Serializable {

    @EJB
    private TipomovimientoFacadeLocal tipomovimientoFacade;

    @EJB
    private MovimientoFacadeLocal movimientoFacade;
    
    

    private Alquileres alquiler;
    private String fechaIni;
    private String fechaFin;
    private Integer cantidadDias;
    private Integer montoTotal;
    private Integer situacion;
    private Integer estadoReserva;
    private Autos autos;
    private Clientes clientes;
    private String fechaDevolucion;

    @EJB
    private AutosFacadeLocal autosFacade;

    @EJB
    private ClientesFacadeLocal clientesFacade;

    @EJB
    AlquileresFacadeLocal afl;
    
    

    public AlquilerMbean() {
    }

    public List<Alquileres> getAlquilereses() {
        return afl.findAllOrder();
    }

    public List<Alquileres> listarAlquileres() {
        System.out.println("*** listarAlquileres ***");
        try {
            return afl.findAllOrder();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Autos> getAutoses() {
        return autosFacade.findAllOrdDisp();
    }

    public List<Clientes> getClienteses() {
        return clientesFacade.findAll();
    }

    // Actions del Caso de Uso
    // Metodos que retornan String y se mapean en el archivo faces-config.xml
    public String actualizar() {
        System.out.println("***actualizar ***");
        try {
            if(alquiler.getIdAlquiler()==null){
                genMontoEstimado();
            }else{
                if(alquiler.getEstadoReserva()==0){
                    System.out.println("Devolver "+alquiler.toString());
                    genMontoEstimado();
                    alquiler.setEstadoReserva(2);
                    MovimientoPK mpk = new MovimientoPK();
                    mpk.setIdTipomov(5);
                    mpk.setSecuenciamov(movimientoFacade.lastSec(5));
                    Movimiento m = new Movimiento();
                    m.setMovimientoPK(mpk);
                    m.setFechamov(new Date());
                    m.setIdAuto(this.alquiler.getIdAuto().getIdAuto());
                    m.setMonto(this.alquiler.getMontoTotal());
                    m.setSituacion(1);
                    m.setTipomovimiento(tipomovimientoFacade.find(5));
                    movimientoFacade.create(m);
                    //edit(alquiler);
                }else{
                    System.out.println("Alquilar "+alquiler.toString());
                    alquiler.setEstadoReserva(0);
                    //edit(alquiler);
                }
            }
                afl.actualizar(alquiler);
                this.autos = autosFacade.find(alquiler.getIdAuto().getIdAuto());
            
            switch(alquiler.getEstadoReserva()){
                case 0:
                    this.autos.setEstado("ALQUILADO");
                    break;
                case 1:
                    this.autos.setEstado("RESERVADO");
                    break;
                case 2:
                    this.autos.setEstado("DISPONIBLE");
                    break;
            }
            autosFacade.actualizar(this.autos);
			String mensaje = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "msg").getString("label.guardadoConExito");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", mensaje));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ConsultarAlquiler";
    }

    public String anular(Alquileres alquilerAnular) {
        System.out.println("*** anular ***");
        try {
            System.out.println(alquilerAnular.getIdAlquiler());
            afl.anular(alquilerAnular.getIdAlquiler());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ConsultarAlquiler";
    }

    public String irALaConsulta() {
        return "ConsultarAlquiler";
    }

    public String editar(Alquileres alquilerEditar) {
        System.out.println("*** editar ***");
        this.alquiler = alquilerEditar;
        System.out.println("Obj" + alquiler.toString());
//        this.autos = this.alquiler.getIdAuto();
//        this.clientes = this.alquiler.getIdCliente();
//        try {
//            //this.alquiler = afl.edit(alquilerEditar);
//        } catch (Exception e) {
//            System.out.println("Error al recuperar lista de libros prestados");
//            System.out.println(e);
//        }
        return "DETALLE_ALQUILERES";
    }

    public String nuevo() {
        System.out.println("*** nuevo ***");
        this.alquiler = new Alquileres();
        this.alquiler.setIdAlquiler(null);
        this.autos = new Autos();
        this.clientes = new Clientes();
        this.alquiler.setIdAuto(autos);
        this.alquiler.setIdCliente(clientes);
        return "DETALLE_ALQUILERES";
    }

    /*
	public String agregar() {
		System.out.println("*** agregar ***");
		try {
			prestamoLibro.setLibro(lsfr.buscarPorCodigo(this.prestamoLibro.getLibro().getCodigo()));
			prestamoLibro.setEstado(0);
			prestamoLibroList.add(prestamoLibro);
			prestamoLibro = new PrestamoLibro();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "DETALLE_PRESTAMO";
	}
     */
    public Alquileres getAlquiler() {
        if (this.alquiler == null) {
            this.alquiler = new Alquileres();
        }
        return alquiler;
    }

    public void setAlquiler(Alquileres alquiler) {
        this.alquiler = alquiler;
    }

//Hacer metodo calcularmonto total
    public String getFechaIni() {
        DateFormat fchFormat = new SimpleDateFormat("yyyy-MM-dd");
        fechaIni = fchFormat.format(Calendar.getInstance().getTime());
        if (alquiler.getFechaIni() != null) {
            fechaIni = fchFormat.format(alquiler.getFechaIni());
        }
        return fechaIni;
    }

    public void setFechaIni(String fechaIni) throws ParseException {
        if (!fechaIni.equals("")) {
            this.fechaIni = fechaIni;
            Date fchIni = new SimpleDateFormat("yyyy-MM-dd").parse(this.fechaIni);
            this.alquiler.setFechaIni(fchIni);
        }
    }

    public String getFechaFin() {
        DateFormat fchFormat = new SimpleDateFormat("yyyy-MM-dd");
        fechaFin = fchFormat.format(Calendar.getInstance().getTime());
        if (alquiler.getFechaFin() != null) {
            fechaFin = fchFormat.format(alquiler.getFechaFin());
        }
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) throws ParseException {
        if (!fechaFin.equals("")) {
            this.fechaFin = fechaFin;
            Date fchFin = new SimpleDateFormat("yyyy-MM-dd").parse(this.fechaFin);
            this.alquiler.setFechaFin(fchFin);
        }
    }

    public Integer getCantidadDias() {
        return cantidadDias;
    }

    public void setCantidadDias(Integer cantidadDias) {
        this.cantidadDias = cantidadDias;
    }

    public Integer getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(Integer montoTotal) {
        this.montoTotal = montoTotal;
    }

    public Integer getSituacion() {
        return situacion;
    }

    public void setSituacion(Integer situacion) {
        this.situacion = situacion;
    }

    public Integer getEstadoReserva() {
        return estadoReserva;
    }

    public void setEstadoReserva(Integer estadoReserva) {
        this.estadoReserva = estadoReserva;
    }

    public Autos getAutos() {
        if (this.autos == null) {
            this.autos = new Autos();
        }
        return autos;
    }

    public void setAutos(Autos autos) {
        this.autos = autos;
    }

    public Clientes getClientes() {
        if (this.clientes == null) {
            this.clientes = new Clientes();
        }
        return clientes;
    }

    public void setClientes(Clientes clientes) {
        this.clientes = clientes;
    }

    public String getFechaDevolucion() {
        DateFormat fchFormat = new SimpleDateFormat("yyyy-MM-dd");
        fechaDevolucion = fchFormat.format(Calendar.getInstance().getTime());
        if (alquiler.getFechaDevolucion() != null) {
            fechaDevolucion = fchFormat.format(alquiler.getFechaDevolucion());
        }
        return fechaDevolucion;
    }

    public void setFechaDevolucion(String fechaDevolucion) throws ParseException {
        if (!fechaDevolucion.equals("")) {
            this.fechaDevolucion = fechaDevolucion;
            Date fchDev = new SimpleDateFormat("yyyy-MM-dd").parse(this.fechaDevolucion);
            this.alquiler.setFechaDevolucion(fchDev);
        }

    }

    public Integer cantdias(Date fechaIni, Date fechaFin) {

        int dias = (int) ((fechaFin.getTime() - fechaIni.getTime()) / 86400000);
        return dias;
    }

    public void genMontoEstimado() {
        System.out.println("genMontoEstimado");
        Autos a = autosFacade.findById(this.alquiler.getIdAuto().getIdAuto()).get(0);
        System.out.println("Autos: " + a.toString());
        DateFormat fchFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("Fecha INI :" + fchFormat.format(this.alquiler.getFechaIni()) + " Fecha FIN: " + fchFormat.format(this.alquiler.getFechaFin()));
        Integer dias = cantdias(this.alquiler.getFechaIni(), this.alquiler.getFechaFin());
        this.alquiler.setCantidadDias(dias);
        System.out.println("Dias: " + dias);
        if (this.alquiler.getFechaDevolucion() == null) {
            this.alquiler.setMontoTotal(a.getValorDiaria() * dias);
        } else {
            Integer c= this.alquiler.getFechaDevolucion().compareTo(this.alquiler.getFechaFin());
            System.out.println("Hola  "+c);
            if(c>=0){
                Integer valorAdicional = genMontoAdicional(this.alquiler.getFechaDevolucion(), this.alquiler.getFechaFin());
                this.alquiler.setMontoTotal(valorAdicional + a.getValorDiaria() * dias);
             }else{
                this.alquiler.setMontoTotal(this.alquiler.getMontoTotal());
                
            }
        }
    }

    public Integer genMontoAdicional(Date fechaDevolucion, Date fechaFin) {
        
        Autos a = autosFacade.findById(this.alquiler.getIdAuto().getIdAuto()).get(0);
        Integer dias = (int) ((fechaDevolucion.getTime() - fechaFin.getTime()) / 86400000);
        System.out.println("Dias= " + dias);
         return a.getValorDiaria() * dias + (a.getValorAdiccional() * dias);
    }

}
