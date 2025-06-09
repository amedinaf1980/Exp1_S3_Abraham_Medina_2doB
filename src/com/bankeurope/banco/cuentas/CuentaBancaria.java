package com.bankeurope.banco.cuentas;
/*
 * Clase abstracta que representa una cuenta bancaria.
 * Define atributos como número de cuenta y saldo
 * y métodos abstractos para operaciones como depositar, girar y calcular intereses.
 */
import java.util.Scanner;

public abstract class CuentaBancaria {
	protected int numeroCuenta;
	protected int saldo;

	// Sobrecarga de métodos
	public CuentaBancaria(int numeroCuenta) {
	    this.numeroCuenta = numeroCuenta;
	    this.saldo = 0;
	}
	
	public CuentaBancaria() {
		super();
	}

	// Constructor que inicializa la cuenta con un número de cuenta y saldo inicial
	public CuentaBancaria(int numeroCuenta, int saldo) {
		this.numeroCuenta = numeroCuenta;
		this.saldo = saldo;
	}

	public void mostrarInformacionCuenta() {
		System.out.println("N° Cuenta: " + this.numeroCuenta);
		System.out.println("Saldo    : " + this.saldo);
	}

	// Depositar dinero en la cuenta actual
	public abstract boolean depositar(int monto, Scanner scanner);

	// Girar dinero en la cuenta actual
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