/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.innova.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author nestor
 */
@Entity
@Table(name = "tipomovimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipomovimiento.findAll", query = "SELECT t FROM Tipomovimiento t")
    , @NamedQuery(name = "Tipomovimiento.findByIdTipomovimiento", query = "SELECT t FROM Tipomovimiento t WHERE t.idTipomovimiento = :idTipomovimiento")
    , @NamedQuery(name = "Tipomovimiento.findByDescripcion", query = "SELECT t FROM Tipomovimiento t WHERE t.descripcion = :descripcion")})
public class Tipomovimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipomovimiento")
    private Integer idTipomovimiento;
    @Size(max = 40)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipomovimiento")
    private List<Movimiento> movimientoList;

    public Tipomovimiento() {
    }

    public Tipomovimiento(Integer idTipomovimiento) {
        this.idTipomovimiento = idTipomovimiento;
    }

    public Integer getIdTipomovimiento() {
        return idTipomovimiento;
    }

    public void setIdTipomovimiento(Integer idTipomovimiento) {
        this.idTipomovimiento = idTipomovimiento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<Movimiento> getMovimientoList() {
        return movimientoList;
    }

    public void setMovimientoList(List<Movimiento> movimientoList) {
        this.movimientoList = movimientoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipomovimiento != null ? idTipomovimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipomovimiento)) {
            return false;
        }
        Tipomovimiento other = (Tipomovimiento) object;
        if ((this.idTipomovimiento == null && other.idTipomovimiento != null) || (this.idTipomovimiento != null && !this.idTipomovimiento.equals(other.idTipomovimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.innova.entity.Tipomovimiento[ idTipomovimiento=" + idTipomovimiento + " ]";
    }
    
}
