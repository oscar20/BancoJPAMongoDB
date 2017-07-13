package com.beeva.jpa.DAO;

import com.beeva.jpa.models.Cuenta;
import com.beeva.jpa.models.Tipocuenta;


public abstract class TipoCuentaDAO {
	
	public abstract int getTipoCuenta(Cuenta c);
	public abstract Tipocuenta saveTipoCuenta(Tipocuenta tc);

}
