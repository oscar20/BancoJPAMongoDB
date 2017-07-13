package com.beeva.jpa.implementacion;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.beeva.jpa.DAO.TipoCuentaDAO;
import com.beeva.jpa.models.Cuenta;
import com.beeva.jpa.models.Tipocuenta;

@Repository
public class TipoCuentaDAOImpl extends TipoCuentaDAO{
	@PersistenceContext
	EntityManager em;
	
	@Override
	public int getTipoCuenta(Cuenta c) {
		int id_tipo_cuenta = c.getTipocuenta().getIdtipocuenta();
		return id_tipo_cuenta;
	}

	@Override
	@Transactional
	public Tipocuenta saveTipoCuenta(Tipocuenta tc) {
		try{
			em.persist(tc);
			System.out.println("Transaccion de tipo cuenta " + tc.getNombre() + " realizada con exito..!");
		}catch(Exception e){
		System.out.println("Transaccion guardar cuenta fallida :(");
	}
	return tc;
	}

}
