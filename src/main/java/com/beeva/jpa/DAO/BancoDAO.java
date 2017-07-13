package com.beeva.jpa.DAO;

import com.beeva.jpa.models.Banco;

public abstract class BancoDAO {
	
	public abstract Banco saveBanco(Banco b);
	public abstract void getBanco();
}
