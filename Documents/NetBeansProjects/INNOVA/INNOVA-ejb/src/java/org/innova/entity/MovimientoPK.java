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
public class MovimientoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_tipomov")
    private int idTipomov;
    @Basic(optional = false)
    @NotNull
    @Column(name = "secuenciamov")
    private int secuenciamov;

    public MovimientoPK() {
    }

    public MovimientoPK(int idTipomov, int secuenciamov) {
        this.idTipomov = idTipomov;
        this.secuenciamov = secuenciamov;
    }

    public int getIdTipomov() {
        return idTipomov;
    }

    public void setIdTipomov(int idTipomov) {
        this.idTipomov = idTipomov;
    }

    public int getSecuenciamov() {
        return secuenciamov;
    }

    public void setSecuenciamov(int secuenciamov) {
        this.secuenciamov = secuenciamov;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idTipomov;
        hash += (int) secuenciamov;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MovimientoPK)) {
            return false;
        }
        MovimientoPK other = (MovimientoPK) object;
        if (this.idTipomov != other.idTipomov) {
            return false;
        }
        if (this.secuenciamov != other.secuenciamov) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.innova.entity.MovimientoPK[ idTipomov=" + idTipomov + ", secuenciamov=" + secuenciamov + " ]";
    }
    
}
