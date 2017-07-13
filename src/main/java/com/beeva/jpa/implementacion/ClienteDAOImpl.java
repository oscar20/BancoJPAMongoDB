package com.beeva.jpa.implementacion;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.beeva.jpa.DAO.ClienteDAO;
import com.beeva.jpa.models.Cliente;

@Repository
public class ClienteDAOImpl extends ClienteDAO {
	
	@PersistenceContext
	EntityManager em;

	@Override
	@Transactional
	public Cliente saveCliente(Cliente cliente) {
		try{
			
			em.persist(cliente); //Voy a darle persistencia a mi objeto Cliente
			System.out.println("Transaccion de cliente " + cliente.getNombre()+ " " + cliente.getApellido() + " realizada con exito..!");
		}catch(Exception e){
			System.out.println("Transaccion de Cliente fallida");
		}
		
		return cliente;
	}

	@Override
	public List<Cliente> getCliente(int id) {
		return em.createQuery("SELECT c FROM Cliente c WHERE idcliente=:myid").setParameter("myid",id).getResultList();
		
	}

}
