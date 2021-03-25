/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.innova.session;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.innova.entity.Alquileres;

/**
 *
 * @author nestor
 */
@Stateless
public class AlquileresFacade extends AbstractFacade<Alquileres> implements AlquileresFacadeLocal {

    @PersistenceContext(unitName = "InnovaPersistenceUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
 
    public AlquileresFacade() {
        super(Alquileres.class);
    }
    
    @Override
     public void actualizar(Alquileres alquiler)  {
        if(alquiler.getIdAlquiler()==null){
//            System.out.println("Id Generado "+getId());
             alquiler.setSituacion(0);
            System.out.print("Situ"+ alquiler.getSituacion());
            System.out.println("Crear "+alquiler.toString());
            create(alquiler);
            
            
        }else{
            edit(alquiler);
//            if(alquiler.getEstadoReserva()==0){
//                System.out.println("Devolver "+alquiler.toString());
//                alquiler.setEstadoReserva(2);
//                edit(alquiler);
//            }else{
//                System.out.println("Alquilar "+alquiler.toString());
//                alquiler.setEstadoReserva(0);
//                edit(alquiler);
//            }
            
        }
    }
       @Override
        public Integer getId() {
             TypedQuery<Alquileres> query = em.createNamedQuery("Alquileres.findLastId", Alquileres.class);
             List<Alquileres> resultado = query.getResultList();
        Integer id = 0;
        try {
            if(resultado.size()>0){
                id = resultado.get(0).getIdAlquiler();
                return id;
            }
        } catch (Exception e) {
            System.out.println("Error en AutosFacade Linea 56"+e.getMessage());
        }
        return id;
    }
    
    
    @Override
	public void anular(Integer numero) throws Exception {
		Alquileres alquiler = find(numero);
		if (alquiler != null) {
			alquiler.setSituacion(1);
			em.merge(alquiler);
		}
		
	}

//    @Override
//    public Integer calcularMonto() {
//             TypedQuery query = em.createNamedQuery("Alquileres.findCantidadDiasByAlquilerId", Alquileres.class).setParameter("idAlquiler", this);
//        List<Alquileres> listalqui = query.getResultList();
//        Integer dias = 0;
//        if(listalqui.size()>0){
//            dias = listalqui.get(0).getCantidadDias();
//        }
//        return dias;
//    }

    @Override
    public List<Alquileres> findAllOrder() {
       TypedQuery<Alquileres> query = em.createNamedQuery("Alquileres.findAll", Alquileres.class);
             List<Alquileres> resultado = query.getResultList();
             return resultado;
    }
  }