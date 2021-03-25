/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.innova.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author nestor
 */
@Embeddable
public class SeguroPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_seguro")
    private int idSeguro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_auto")
    private int idAuto;

    public SeguroPK() {
    }

    public SeguroPK(int idSeguro, int idAuto) {
        this.idSeguro = idSeguro;
        this.idAuto = idAuto;
    }

    public int getIdSeguro() {
        return idSeguro;
    }

    public void setIdSeguro(int idSeguro) {
        this.idSeguro = idSeguro;
    }

    public int getIdAuto() {
        return idAuto;
    }

    public void setIdAuto(int idAuto) {
        this.idAuto = idAuto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idSeguro;
        hash += (int) idAuto;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SeguroPK)) {
            return false;
        }
        SeguroPK other = (SeguroPK) object;
        if (this.idSeguro != other.idSeguro) {
            return false;
        }
        if (this.idAuto != other.idAuto) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.innova.entity.SeguroPK[ idSeguro=" + idSeguro + ", idAuto=" + idAuto + " ]";
    }
    
}
