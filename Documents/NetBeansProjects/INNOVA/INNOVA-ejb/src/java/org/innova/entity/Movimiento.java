/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.innova.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
 * @author nestor
 */
@Entity
@Table(name = "movimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Movimiento.findAll", query = "SELECT m FROM Movimiento m")
    , @NamedQuery(name = "Movimiento.findByIdTipomov", query = "SELECT m FROM Movimiento m WHERE m.movimientoPK.idTipomov = :idTipomov ORDER BY M.movimientoPK.secuenciamov DESC")
    , @NamedQuery(name = "Movimiento.findBySecuenciamov", query = "SELECT m FROM Movimiento m WHERE m.movimientoPK.secuenciamov = :secuenciamov")
    , @NamedQuery(name = "Movimiento.findByIdAuto", query = "SELECT m FROM Movimiento m WHERE m.idAuto = :idAuto and m.tipomovimiento.idTipomovimiento = :idTipomov")
    , @NamedQuery(name = "Movimiento.findByIdSeguro", query = "SELECT m FROM Movimiento m WHERE m.idSeguro = :idSeguro")
    , @NamedQuery(name = "Movimiento.findByNroCuota", query = "SELECT m FROM Movimiento m WHERE m.nroCuota = :nroCuota")
    , @NamedQuery(name = "Movimiento.findByMonto", query = "SELECT m FROM Movimiento m WHERE m.monto = :monto")
    , @NamedQuery(name = "Movimiento.findByFechamov", query = "SELECT m FROM Movimiento m WHERE m.fechamov = :fechamov")
    , @NamedQuery(name = "Movimiento.findByIdUsuario", query = "SELECT m FROM Movimiento m WHERE m.idUsuario = :idUsuario")
    , @NamedQuery(name = "Movimiento.findBySituacion", query = "SELECT m FROM Movimiento m WHERE m.situacion = :situacion")})
public class Movimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MovimientoPK movimientoPK;
    @Column(name = "id_auto")
    private Integer idAuto;
    @Column(name = "id_seguro")
    private Integer idSeguro;
    @Column(name = "nro_cuota")
    private Integer nroCuota;
    @Column(name = "monto")
    private Integer monto;
    @Column(name = "fechamov")
    @Temporal(TemporalType.DATE)
    private Date fechamov;
    @Column(name = "id_usuario")
    private Integer idUsuario;
    @Column(name = "situacion")
    private Integer situacion;
    @JoinColumn(name = "id_tipomov", referencedColumnName = "id_tipomovimiento", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Tipomovimiento tipomovimiento;

    public Movimiento() {
    }

    public Movimiento(MovimientoPK movimientoPK) {
        this.movimientoPK = movimientoPK;
    }

    public Movimiento(int idTipomov, int secuenciamov) {
        this.movimientoPK = new MovimientoPK(idTipomov, secuenciamov);
    }

    public MovimientoPK getMovimientoPK() {
        return movimientoPK;
    }

    public void setMovimientoPK(MovimientoPK movimientoPK) {
        this.movimientoPK = movimientoPK;
    }

    public Integer getIdAuto() {
        return idAuto;
    }

    public void setIdAuto(Integer idAuto) {
        this.idAuto = idAuto;
    }

    public Integer getIdSeguro() {
        return idSeguro;
    }

    public void setIdSeguro(Integer idSeguro) {
        this.idSeguro = idSeguro;
    }

    public Integer getNroCuota() {
        return nroCuota;
    }

    public void setNroCuota(Integer nroCuota) {
        this.nroCuota = nroCuota;
    }

    public Integer getMonto() {
        return monto;
    }

    public void setMonto(Integer monto) {
        this.monto = monto;
    }

    public Date getFechamov() {
        return fechamov;
    }

    public void setFechamov(Date fechamov) {
        this.fechamov = fechamov;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getSituacion() {
        return situacion;
    }

    public void setSituacion(Integer situacion) {
        this.situacion = situacion;
    }

    public Tipomovimiento getTipomovimiento() {
        return tipomovimiento;
    }

    public void setTipomovimiento(Tipomovimiento tipomovimiento) {
        this.tipomovimiento = tipomovimiento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (movimientoPK != null ? movimientoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Movimiento)) {
            return false;
        }
        Movimiento other = (Movimiento) object;
        if ((this.movimientoPK == null && other.movimientoPK != null) || (this.movimientoPK != null && !this.movimientoPK.equals(other.movimientoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.innova.entity.Movimiento[ movimientoPK=" + movimientoPK + " ]";
    }
    
}
