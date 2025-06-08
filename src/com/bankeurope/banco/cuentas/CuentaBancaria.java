package com.bankeurope.banco.cuentas;

import java.util.Scanner;

public abstract class CuentaBancaria {
	protected int numeroCuenta;
	protected int saldo;

	//********** SOBRECARGA
	public CuentaBancaria(int numeroCuenta) {
	    this.numeroCuenta = numeroCuenta;
	    this.saldo = 0;
	}
	
	public CuentaBancaria(int numeroCuenta, int saldo) {
		this.numeroCuenta = numeroCuenta;
		this.saldo = saldo;
	}

	public void mostrarInformacionCuenta() {
		System.out.println("N° Cuenta: " + this.numeroCuenta);
		System.out.println("Saldo    : " + this.saldo);
	}

	public abstract boolean depositar(int monto, Scanner scanner);

	public abstract boolean girar(int monto, Scanner scanner);

	public int getNumeroCuenta() {
		return numeroCuenta;
	}
	
	public int consultarSaldo() {
		return saldo;
	}
	
    public void setSaldo(int saldo) {
		this.saldo = saldo;
	}

	public abstract void calcularInteres();
}