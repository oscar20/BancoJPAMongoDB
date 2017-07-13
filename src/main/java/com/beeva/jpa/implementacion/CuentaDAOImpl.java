package com.beeva.jpa.implementacion;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.beeva.jpa.DAO.CuentaDAO;
import com.beeva.jpa.models.Cliente;
import com.beeva.jpa.models.Cuenta;
import com.beeva.jpa.mongodb.RegistroMongo;

@Repository
public class CuentaDAOImpl extends CuentaDAO{
	RegistroMongo rg;
	
	@PersistenceContext
	EntityManager em;

	@Override
	@Transactional
	public Cuenta saveCuenta(Cuenta c) {
		try{
			em.persist(c);
			System.out.println("Transaccion de cuenta " + c.getIdcuenta() + " realizada con exito..!");
		}catch(Exception e){
			System.out.println("Transaccion guardar cuenta fallida :(");
		}
		rg  = new RegistroMongo();
		rg.insertMongo(c);
		return c;
		
	}

	@Override
	public List<Cuenta> getCuenta(Cliente c) {
		return em.createQuery("SELECT c FROM Cuenta c WHERE idcliente=:id_cliente").setParameter("id_cliente", c.getIdcliente()).getResultList();
		
	}
	
	@Transactional
	public void retiro(Calendar fecha, Cuenta c, double cantidad){
		
		List<Cuenta> list_actual_ahorro = em.createQuery("SELECT c FROM Cuenta c WHERE idcuenta=:v_cuenta")
													.setParameter("v_cuenta", c.getIdcuenta())
													.getResultList();
		double balan_actual = list_actual_ahorro.get(0).getBalance(); //Obtengo el balance actual de la base de datos
		int tipo =  list_actual_ahorro.get(0).getTipocuenta().getIdtipocuenta();
		if(tipo == 1){ //AHORRO
			if(balan_actual > 5000){
				Query query = em.createQuery("UPDATE Cuenta SET balance=:nuevo_balance WHERE idcuenta=:v_cuenta")
						  .setParameter("nuevo_balance", balan_actual - cantidad)
						  .setParameter("v_cuenta", c.getIdcuenta());
				int filas_afectadas = query.executeUpdate();
				if(filas_afectadas > 0 )
					System.out.println(c.getCliente().getNombre() + " " + c.getCliente().getApellido() + 
										" realizo un retiro por la cantidad de: " + cantidad);
				else
					System.out.println("No se realizo ninguna operacion");
			}else
				System.out.println("No puedes retirar de una cuenta de ahorro si tienes menos de 5000");
		
		}else{   //CHEQUES
			
			int dia = fecha.get(Calendar.DAY_OF_WEEK);
			if((dia !=7 && dia !=1)){
				List<Cuenta> list_actual_cheques = em.createQuery("SELECT c FROM Cuenta c WHERE idcuenta=:v_cuenta")
														.setParameter("v_cuenta", c.getIdcuenta())
														.getResultList();
				double balan_actual_cheques = list_actual_cheques.get(0).getBalance(); //Obtengo el balance actual de la base de datos
				
				Query query_cheques = em.createQuery("UPDATE Cuenta SET balance=:nuevo_balance WHERE idcuenta=:v_cuenta")
						  .setParameter("nuevo_balance", balan_actual_cheques - cantidad)
						  .setParameter("v_cuenta", c.getIdcuenta());
				int filas_afectadas2 = query_cheques.executeUpdate();
				if(filas_afectadas2 > 0 )
					System.out.println(c.getCliente().getNombre() + " " + c.getCliente().getApellido() + 
							" realizo un retiro por la cantidad de: " + cantidad + " existosamente!");
				else
					System.out.println("No se realizo ninguna operacion");
			}else
				System.out.println("No puedes retirar de una cuenta de cheques en fin de semana");
				
		}
			
	}
	
	@Transactional
	public void deposito(Cuenta c, double cantidad){
			List<Cuenta> balance_actual = em.createQuery("SELECT c FROM Cuenta c WHERE idcuenta=:v_cuenta").setParameter("v_cuenta", c.getIdcuenta()).getResultList();
			double balan_actual = balance_actual.get(0).getBalance();
			try{
				Query query = em.createQuery("UPDATE Cuenta SET balance=:nuevo_balance WHERE idcuenta=:v_cuenta")
						  .setParameter("nuevo_balance", balan_actual + cantidad)
						  .setParameter("v_cuenta", c.getIdcuenta());
				int filas_afectadas = query.executeUpdate();
				if(filas_afectadas > 0)
					System.out.println(c.getCliente().getNombre() + " " + c.getCliente().getApellido() + 
							" realizo un deposito por la cantidad de: " + cantidad + " exitosamente!");
			}catch(Exception e){
				System.out.println("Error al hacer el deposito..!");
			}
			
		
	}
	
}
