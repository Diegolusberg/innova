/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.innova.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author nestor
 */
@Entity
@Table(name = "autos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Autos.findAll", query = "SELECT a FROM Autos a ORDER BY a.idAuto")
    , @NamedQuery(name = "Autos.findByIdAuto", query = "SELECT a FROM Autos a WHERE a.idAuto = :idAuto")
    , @NamedQuery(name = "Autos.findAllDisp", query = "SELECT a FROM Autos a where not EXISTS(Select al From Alquileres al Where a.idAuto=al.idAuto.idAuto and al.estadoReserva<>2) ORDER BY a.idAuto")
    , @NamedQuery(name = "Autos.findLastId", query = "SELECT a FROM Autos a ORDER BY a.idAuto DESC")
    , @NamedQuery(name = "Autos.findByChapa", query = "SELECT a FROM Autos a WHERE a.chapa = :chapa")
    , @NamedQuery(name = "Autos.findByMarca", query = "SELECT a FROM Autos a WHERE a.marca = :marca")
    , @NamedQuery(name = "Autos.findByModelo", query = "SELECT a FROM Autos a WHERE a.modelo = :modelo")
    , @NamedQuery(name = "Autos.findByAnho", query = "SELECT a FROM Autos a WHERE a.anho = :anho")
    , @NamedQuery(name = "Autos.findByDetalles", query = "SELECT a FROM Autos a WHERE a.detalles = :detalles")
    , @NamedQuery(name = "Autos.findByValorDiaria", query = "SELECT a FROM Autos a WHERE a.valorDiaria = :valorDiaria")
    , @NamedQuery(name = "Autos.findByEstado", query = "SELECT a FROM Autos a WHERE a.estado = :estado")
    , @NamedQuery(name = "Autos.findByValorAdiccional", query = "SELECT a FROM Autos a WHERE a.valorAdiccional = :valorAdiccional")
    , @NamedQuery(name = "Autos.findByFechaCompra", query = "SELECT a FROM Autos a WHERE a.fechaCompra = :fechaCompra")
    , @NamedQuery(name = "Autos.findByFormaCompra", query = "SELECT a FROM Autos a WHERE a.formaCompra = :formaCompra")
    , @NamedQuery(name = "Autos.findByPrecio", query = "SELECT a FROM Autos a WHERE a.precio = :precio")
    , @NamedQuery(name = "Autos.findByEntrega", query = "SELECT a FROM Autos a WHERE a.entrega = :entrega")
    , @NamedQuery(name = "Autos.findByCantCuotas", query = "SELECT a FROM Autos a WHERE a.cantCuotas = :cantCuotas")
    , @NamedQuery(name = "Autos.findByValCuota", query = "SELECT a FROM Autos a WHERE a.valCuota = :valCuota")})
public class Autos implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "autos")
    private List<Seguro> seguroList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_auto")
    private Integer idAuto;
    @Size(max = 10)
    @Column(name = "chapa")
    private String chapa;
    @Size(max = 20)
    @Column(name = "marca")
    private String marca;
    @Size(max = 20)
    @Column(name = "modelo")
    private String modelo;
    @Column(name = "anho")
    private Integer anho;
    @Size(max = 60)
    @Column(name = "detalles")
    private String detalles;
    @Column(name = "valor_diaria")
    private Integer valorDiaria;
    @Size(max = 20)
    @Column(name = "estado")
    private String estado;
    @Column(name = "valor_adiccional")
    private Integer valorAdiccional;
    @Column(name = "fecha_compra")
    @Temporal(TemporalType.DATE)
    private Date fechaCompra;
    @Size(max = 10)
    @Column(name = "forma_compra")
    private String formaCompra;
    @Column(name = "precio")
    private Integer precio;
    @Column(name = "entrega")
    private Integer entrega;
    @Column(name = "cant_cuotas")
    private Integer cantCuotas;
    @Column(name = "val_cuota")
    private Integer valCuota;

    public Autos() {
    }

    public Autos(Integer idAuto) {
        this.idAuto = idAuto;
    }

    public Integer getIdAuto() {
        return idAuto;
    }

    public void setIdAuto(Integer idAuto) {
        this.idAuto = idAuto;
    }

    public String getChapa() {
        return chapa;
    }

    public void setChapa(String chapa) {
        this.chapa = chapa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getAnho() {
        return anho;
    }

    public void setAnho(Integer anho) {
        this.anho = anho;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public Integer getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(Integer valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getValorAdiccional() {
        return valorAdiccional;
    }

    public void setValorAdiccional(Integer valorAdiccional) {
        this.valorAdiccional = valorAdiccional;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public String getFormaCompra() {
        return formaCompra;
    }

    public void setFormaCompra(String formaCompra) {
        this.formaCompra = formaCompra;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public Integer getEntrega() {
        return entrega;
    }

    public void setEntrega(Integer entrega) {
        this.entrega = entrega;
    }

    public Integer getCantCuotas() {
        return cantCuotas;
    }

    public void setCantCuotas(Integer cantCuotas) {
        this.cantCuotas = cantCuotas;
    }

    public Integer getValCuota() {
        return valCuota;
    }

    public void setValCuota(Integer valCuota) {
        this.valCuota = valCuota;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAuto != null ? idAuto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Autos)) {
            return false;
        }
        Autos other = (Autos) object;
        if ((this.idAuto == null && other.idAuto != null) || (this.idAuto != null && !this.idAuto.equals(other.idAuto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.innova.entity.Autos[ idAuto=" + idAuto + " ]";
    }

    @XmlTransient
    public List<Seguro> getSeguroList() {
        return seguroList;
    }

    public void setSeguroList(List<Seguro> seguroList) {
        this.seguroList = seguroList;
    }
    
}
