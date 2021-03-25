/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.innova.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Diego Lusberg
 */
@Entity
@Table(name = "alquileres")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Alquileres.findAll", query = "SELECT a FROM Alquileres a ORDER BY a.idAlquiler")
    , @NamedQuery(name = "Alquileres.findByIdAlquiler", query = "SELECT a FROM Alquileres a WHERE a.idAlquiler = :idAlquiler")
    , @NamedQuery(name = "Alquileres.findByFechaIni", query = "SELECT a FROM Alquileres a WHERE a.fechaIni = :fechaIni")
    , @NamedQuery(name = "Alquileres.findByFechaFin", query = "SELECT a FROM Alquileres a WHERE a.fechaFin = :fechaFin")
    , @NamedQuery(name = "Alquileres.findByCantidadDias", query = "SELECT a FROM Alquileres a WHERE a.cantidadDias = :cantidadDias")
    , @NamedQuery(name = "Alquileres.findByMontoTotal", query = "SELECT a FROM Alquileres a WHERE a.montoTotal = :montoTotal")
    , @NamedQuery(name = "Alquileres.findBySituacion", query = "SELECT a FROM Alquileres a WHERE a.situacion = :situacion")
    , @NamedQuery(name = "Alquileres.findByEstadoReserva", query = "SELECT a FROM Alquileres a WHERE a.estadoReserva = :estadoReserva")
    , @NamedQuery(name = "Alquileres.findByFechaDevolucion", query = "SELECT a FROM Alquileres a WHERE a.fechaDevolucion = :fechaDevolucion")})
public class Alquileres implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_alquiler")
    private Integer idAlquiler;
    @Column(name = "fecha_ini")
    @Temporal(TemporalType.DATE)
    private Date fechaIni;
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Column(name = "cantidad_dias")
    private Integer cantidadDias;
    @Column(name = "monto_total")
    private Integer montoTotal;
    @Column(name = "situacion")
    private Integer situacion;
    @Column(name = "estado_reserva")
    private Integer estadoReserva;
    @Column(name = "fecha_devolucion")
    @Temporal(TemporalType.DATE)
    private Date fechaDevolucion;
    @JoinColumn(name = "id_auto", referencedColumnName = "id_auto")
    @ManyToOne
    private Autos idAuto;
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    @ManyToOne
    private Clientes idCliente;

    public Alquileres() {
        this.idAuto = new Autos();
        this.idCliente = new Clientes();
    }

    public Alquileres(Integer idAlquiler) {
        this.idAlquiler = idAlquiler;
    }

    public Integer getIdAlquiler() {
        return idAlquiler;
    }

    public void setIdAlquiler(Integer idAlquiler) {
        this.idAlquiler = idAlquiler;
    }

    public Date getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(Date fechaIni) {
        this.fechaIni = fechaIni;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
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

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public Autos getIdAuto() {
        return idAuto;
    }

    public void setIdAuto(Autos idAuto) {
        this.idAuto = idAuto;
    }

    public Clientes getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Clientes idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAlquiler != null ? idAlquiler.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Alquileres)) {
            return false;
        }
        Alquileres other = (Alquileres) object;
        if ((this.idAlquiler == null && other.idAlquiler != null) || (this.idAlquiler != null && !this.idAlquiler.equals(other.idAlquiler))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.innova.entity.Alquileres[ idAlquiler=" + idAlquiler + " ]";
    }
    
}
