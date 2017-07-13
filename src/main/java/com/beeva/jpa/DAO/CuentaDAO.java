package com.beeva.jpa.DAO;

import java.util.Calendar;
import java.util.List;

import com.beeva.jpa.models.Cliente;
import com.beeva.jpa.models.Cuenta;

public abstract class CuentaDAO {
	
	public abstract Cuenta saveCuenta(Cuenta c);
	public abstract List<Cuenta> getCuenta(Cliente c);
	public abstract void retiro(Calendar fecha,Cuenta tipo,double cantidad);
	public abstract void deposito(Cuenta c, double cantidad);

}
