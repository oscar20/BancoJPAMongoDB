package com.beeva.jpa.implementacion;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.beeva.jpa.DAO.BancoclienteDAO;
import com.beeva.jpa.models.Bancocliente;

@Repository
public class BancoclienteDAOImpl extends BancoclienteDAO{
	@PersistenceContext
	EntityManager em;
	
	@Override
	@Transactional
	public Bancocliente saveBancocliente(Bancocliente banco_cliente) {
		try{
			em.persist(banco_cliente);
			System.out.println("Transaccion de BancoCliente realizada con exito..!");
		}catch(Exception e){
			System.out.println("Transaccion de BancoCliente fallida");
		}
		return banco_cliente;
	}

	@Override
	public void getBancocliente() {
		
	}
	
	

}
