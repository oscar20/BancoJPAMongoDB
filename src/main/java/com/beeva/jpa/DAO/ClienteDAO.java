package com.beeva.jpa.DAO;

import java.util.List;

import com.beeva.jpa.models.Cliente;

public abstract class ClienteDAO {
	
	//Metodos abstractos que seran implementados en la clase de implementacion
	public abstract Cliente saveCliente(Cliente cliente);
	public abstract List<Cliente> getCliente(int id);
}
