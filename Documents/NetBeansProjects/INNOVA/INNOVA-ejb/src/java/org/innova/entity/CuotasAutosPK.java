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
public class CuotasAutosPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_auto")
    private int idAuto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nro_cuota")
    private int nroCuota;

    public CuotasAutosPK() {
    }

    public CuotasAutosPK(int idAuto, int nroCuota) {
        this.idAuto = idAuto;
        this.nroCuota = nroCuota;
    }

    public int getIdAuto() {
        return idAuto;
    }

    public void setIdAuto(int idAuto) {
        this.idAuto = idAuto;
    }

    public int getNroCuota() {
        return nroCuota;
    }

    public void setNroCuota(int nroCuota) {
        this.nroCuota = nroCuota;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idAuto;
        hash += (int) nroCuota;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CuotasAutosPK)) {
            return false;
        }
        CuotasAutosPK other = (CuotasAutosPK) object;
        if (this.idAuto != other.idAuto) {
            return false;
        }
        if (this.nroCuota != other.nroCuota) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.innova.entity.CuotasAutosPK[ idAuto=" + idAuto + ", nroCuota=" + nroCuota + " ]";
    }
    
}
