package com.beeva.jpa.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipocuenta database table.
 * 
 */
@Entity
@NamedQuery(name="Tipocuenta.findAll", query="SELECT t FROM Tipocuenta t")
public class Tipocuenta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idtipocuenta;

	private String nombre;

	//bi-directional many-to-one association to Cuenta
	@OneToMany(mappedBy="tipocuenta")
	private List<Cuenta> cuentas;

	public Tipocuenta() {
	}

	public int getIdtipocuenta() {
		return this.idtipocuenta;
	}

	public void setIdtipocuenta(int idtipocuenta) {
		this.idtipocuenta = idtipocuenta;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Cuenta> getCuentas() {
		return this.cuentas;
	}

	public void setCuentas(List<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}

	public Cuenta addCuenta(Cuenta cuenta) {
		getCuentas().add(cuenta);
		cuenta.setTipocuenta(this);

		return cuenta;
	}

	public Cuenta removeCuenta(Cuenta cuenta) {
		getCuentas().remove(cuenta);
		cuenta.setTipocuenta(null);

		return cuenta;
	}

}