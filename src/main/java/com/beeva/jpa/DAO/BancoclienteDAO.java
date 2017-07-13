package com.beeva.jpa.DAO;

import com.beeva.jpa.models.Bancocliente;

public abstract class BancoclienteDAO {
	
	public abstract Bancocliente saveBancocliente(Bancocliente banco_cliente);
	public abstract void getBancocliente();
}
