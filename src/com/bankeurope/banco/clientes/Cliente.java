package com.bankeurope.banco.clientes;

import java.util.Scanner;

import com.bankeurope.banco.cuentas.CuentaBancaria;
import com.bankeurope.banco.cuentas.tipocuenta.CuentaAhorro;
import com.bankeurope.banco.cuentas.tipocuenta.CuentaCorriente;
import com.bankeurope.banco.cuentas.tipocuenta.CuentaDigital;
import com.bankeurope.banco.utilidades.Grafico;
import com.bankeurope.banco.utilidades.Pausa;

public class Cliente implements InfoCliente{
	private String rut;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String domicilio;
	private String comuna;
	private String telefono;
	private String tipoCuenta;
	private int numeroCuenta;
	private CuentaBancaria cuenta;

	public Cliente(String rut, String nombre, String apellidoPaterno, String apellidoMaterno, String domicilio,
			String comuna, String telefono, String tipoCuenta, int numeroCuenta) {
		this.rut = rut;
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.domicilio = domicilio;
		this.comuna = comuna;
		this.telefono = telefono;
		this.tipoCuenta = tipoCuenta;
		this.numeroCuenta = numeroCuenta;
		
		switch (tipoCuenta.toLowerCase()) {
		case "corriente":
			this.cuenta = new CuentaCorriente(numeroCuenta, 0);
			break;
		case "digital":
			this.cuenta = new CuentaDigital(numeroCuenta, 0);
			break;
		case "ahorro":
		default:
			this.cuenta = new CuentaAhorro(numeroCuenta, 0);
			break;
		}
	}

	public CuentaBancaria getCuenta() {
		return cuenta;
	}

	public boolean registrarCliente(Scanner scanner) {
		Grafico.formatoExito("CLIENTE REGISTRADO CORRECTAMENTE");
		System.out.println(""
				      + "Rut         : " + this.rut
				    + "\nNombre      : " + nombre + " " + apellidoPaterno + " " + apellidoMaterno
					+ "\nDomicilio   : " + domicilio + ", " + comuna
					+ "\nTeléfono    : (+56 9)" + telefono
					+ "\nTipo Cuenta : " + tipoCuenta
					+ "\nN° Cuenta   : " + cuenta.getNumeroCuenta()
					+ "\nSaldo       : " + Grafico.FORMATO_DINERO.format(cuenta.consultarSaldo()));
		Pausa.pausa(scanner);
			return true;
	}
	
	@Override
	public void mostrarInformacionCliente(Scanner scanner) {
		Grafico.formatoTitulo("******** INFORMACION DEL CLIENTE ********");
		System.out.println(""
			      + "Rut         : " + this.rut
			    + "\nNombre      : " + nombre + " " + apellidoPaterno + " " + apellidoMaterno
				+ "\nDomicilio   : " + domicilio + ", " + comuna
				+ "\nTeléfono    : (+56 9)" + telefono
				+ "\nTipo Cuenta : " + tipoCuenta
				+ "\nN° Cuenta   : " + cuenta.getNumeroCuenta()
				+ "\nSaldo       : " + Grafico.FORMATO_DINERO.format(cuenta.consultarSaldo()));
		Pausa.pausa(scanner);
	}

	public String getRut() {
		return this.rut;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public String getComuna() {
		return comuna;
	}

	public String getTelefono() {
		return telefono;
	}

	public int getNumeroCuenta() {
		return numeroCuenta;
	}

	public String getTipoCuenta() {
		return tipoCuenta;
	}
}