package com.beeva.jpa.implementacion;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.beeva.jpa.DAO.BancoDAO;
import com.beeva.jpa.models.Banco;

@Repository
public class BancoDAOImpl extends BancoDAO{
	@PersistenceContext
	EntityManager em;
	
	@Override
	@Transactional
	public Banco saveBanco(Banco b) {
		try{
			em.persist(b);
			System.out.println("Transaccion de Banco " + b.getNombre() + " realizada con exito..!!");
		}catch(Exception e){
			System.out.println("No se realizo la transaccion de Banco :(");
		}
		return b;
	}

	@Override
	public void getBanco() {
		// TODO Auto-generated method stub
		
	}

}
