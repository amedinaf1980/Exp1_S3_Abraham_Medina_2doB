package com.bankeurope.banco.cuentas.tipocuenta;
/*
 * Cuenta de ahorro que aplica un interés del 2% sobre el saldo en cada depósito. Hereda de CuentaBancaria.
 */

import java.util.Scanner;

import com.bankeurope.banco.cuentas.CuentaBancaria;
import com.bankeurope.banco.utilidades.Grafico;
import com.bankeurope.banco.utilidades.Pausa;

public class CuentaAhorro extends CuentaBancaria {
	private double interes;
	
	public CuentaAhorro(int numeroCuenta) {
		super(numeroCuenta);
	}

	public CuentaAhorro(int numeroCuenta, int saldo) {
		super(numeroCuenta, saldo);
	}

	@Override
	public boolean depositar(int monto, Scanner scanner) {
		if (monto > 0) {
		saldo += monto;
		calcularInteres();
		return true;
	}
	Grafico.formatoError("Error: El valor ingresado no es válido.");
	Pausa.pausa(scanner);
	return false;
}

	@Override
	public boolean girar(int monto, Scanner scanner) {
		if (monto <= 0) {
			Grafico.formatoError("Error: El valor ingresado no es válido.");
			Pausa.pausa(scanner);
			return false;
		}
		if (saldo >= monto) {
			saldo -= monto;
			return true;
		} else {
			Grafico.formatoError("Error: Saldo insuficiente para realizar el giro!");
			Pausa.pausa(scanner);
			return false;
		}
	}
	
	// Calcular y aplicar interés del 2% sobre el saldo actual
	@Override
	public void calcularInteres() {
	    interes = saldo * 0.02;
	    saldo += (int) interes;
	}
}