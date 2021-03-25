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
@Table(name = "cuotas_autos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CuotasAutos.findAll", query = "SELECT c FROM CuotasAutos c")
    , @NamedQuery(name = "CuotasAutos.findByIdAuto", query = "SELECT c FROM CuotasAutos c WHERE c.cuotasAutosPK.idAuto = :idAuto ORDER BY c.cuotasAutosPK.nroCuota ASC")
    , @NamedQuery(name = "CuotasAutos.findByValorCuota", query = "SELECT c FROM CuotasAutos c WHERE c.valorCuota = :valorCuota")
    , @NamedQuery(name = "CuotasAutos.findByVencimientoCuota", query = "SELECT c FROM CuotasAutos c WHERE c.vencimientoCuota = :vencimientoCuota")
    , @NamedQuery(name = "CuotasAutos.findByNroCuota", query = "SELECT c FROM CuotasAutos c WHERE c.cuotasAutosPK.nroCuota = :nroCuota")
    , @NamedQuery(name = "CuotasAutos.findByEstado", query = "SELECT c FROM CuotasAutos c WHERE c.estado = :estado")
    , @NamedQuery(name = "CuotasAutos.findByFechapago", query = "SELECT c FROM CuotasAutos c WHERE c.fechapago = :fechapago")})
public class CuotasAutos implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CuotasAutosPK cuotasAutosPK;
    @Column(name = "valor_cuota")
    private Integer valorCuota;
    @Column(name = "vencimiento_cuota")
    @Temporal(TemporalType.DATE)
    private Date vencimientoCuota;
    @Column(name = "estado")
    private Character estado;
    @Column(name = "fechapago")
    @Temporal(TemporalType.DATE)
    private Date fechapago;
    @JoinColumn(name = "id_auto", referencedColumnName = "id_auto", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Autos autos;

    public CuotasAutos() {
    }

    public CuotasAutos(CuotasAutosPK cuotasAutosPK) {
        this.cuotasAutosPK = cuotasAutosPK;
    }

    public CuotasAutos(int idAuto, int nroCuota) {
        this.cuotasAutosPK = new CuotasAutosPK(idAuto, nroCuota);
    }

    public CuotasAutosPK getCuotasAutosPK() {
        return cuotasAutosPK;
    }

    public void setCuotasAutosPK(CuotasAutosPK cuotasAutosPK) {
        this.cuotasAutosPK = cuotasAutosPK;
    }

    public Integer getValorCuota() {
        return valorCuota;
    }

    public void setValorCuota(Integer valorCuota) {
        this.valorCuota = valorCuota;
    }

    public Date getVencimientoCuota() {
        return vencimientoCuota;
    }

    public void setVencimientoCuota(Date vencimientoCuota) {
        this.vencimientoCuota = vencimientoCuota;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public Date getFechapago() {
        return fechapago;
    }

    public void setFechapago(Date fechapago) {
        this.fechapago = fechapago;
    }

    public Autos getAutos() {
        return autos;
    }

    public void setAutos(Autos autos) {
        this.autos = autos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cuotasAutosPK != null ? cuotasAutosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CuotasAutos)) {
            return false;
        }
        CuotasAutos other = (CuotasAutos) object;
        if ((this.cuotasAutosPK == null && other.cuotasAutosPK != null) || (this.cuotasAutosPK != null && !this.cuotasAutosPK.equals(other.cuotasAutosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.innova.entity.CuotasAutos[ cuotasAutosPK=" + cuotasAutosPK + " ]";
    }
    
}
