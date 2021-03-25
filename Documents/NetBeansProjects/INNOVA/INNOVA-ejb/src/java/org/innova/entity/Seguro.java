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
@Table(name = "seguro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Seguro.findAll", query = "SELECT s FROM Seguro s ORDER BY s.seguroPK.idAuto,s.seguroPK.idSeguro")
    , @NamedQuery(name = "Seguro.findByIdSeguro", query = "SELECT s FROM Seguro s WHERE s.seguroPK.idSeguro = :idSeguro")
    , @NamedQuery(name = "Seguro.findByIdAuto", query = "SELECT s.seguroPK FROM Seguro s WHERE s.seguroPK.idAuto = :idAuto ORDER BY s.seguroPK.idSeguro DESC")
    , @NamedQuery(name = "Seguro.findByValorSeguro", query = "SELECT s FROM Seguro s WHERE s.valorSeguro = :valorSeguro")
    , @NamedQuery(name = "Seguro.findByEstadoSeguro", query = "SELECT s FROM Seguro s WHERE s.estadoSeguro = :estadoSeguro")
    , @NamedQuery(name = "Seguro.findByFchVenc", query = "SELECT s FROM Seguro s WHERE s.fchVenc = :fchVenc")})
public class Seguro implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SeguroPK seguroPK;
    @Column(name = "valor_seguro")
    private Integer valorSeguro;
    @Column(name = "estado_seguro")
    private Integer estadoSeguro;
    @Column(name = "fch_venc")
    @Temporal(TemporalType.DATE)
    private Date fchVenc;
    @JoinColumn(name = "id_auto", referencedColumnName = "id_auto", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Autos autos;

    public Seguro() {
    }

    public Seguro(SeguroPK seguroPK) {
        this.seguroPK = seguroPK;
    }

    public Seguro(int idSeguro, int idAuto) {
        this.seguroPK = new SeguroPK(idSeguro, idAuto);
    }

    public SeguroPK getSeguroPK() {
        return seguroPK;
    }

    public void setSeguroPK(SeguroPK seguroPK) {
        this.seguroPK = seguroPK;
    }

    public Integer getValorSeguro() {
        return valorSeguro;
    }

    public void setValorSeguro(Integer valorSeguro) {
        this.valorSeguro = valorSeguro;
    }

    public Integer getEstadoSeguro() {
        return estadoSeguro;
    }

    public void setEstadoSeguro(Integer estadoSeguro) {
        this.estadoSeguro = estadoSeguro;
    }

    public Date getFchVenc() {
        return fchVenc;
    }

    public void setFchVenc(Date fchVenc) {
        this.fchVenc = fchVenc;
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
        hash += (seguroPK != null ? seguroPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Seguro)) {
            return false;
        }
        Seguro other = (Seguro) object;
        if ((this.seguroPK == null && other.seguroPK != null) || (this.seguroPK != null && !this.seguroPK.equals(other.seguroPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.innova.entity.Seguro[ seguroPK=" + seguroPK + " ]";
    }
    
}
