package com.bankeurope.banco.cuentas.tipocuenta;

import java.util.Scanner;

import com.bankeurope.banco.cuentas.CuentaBancaria;
import com.bankeurope.banco.utilidades.Grafico;
import com.bankeurope.banco.utilidades.Pausa;

public class CuentaCorriente extends CuentaBancaria {
	private int sobregiro = 500000;
	
	public CuentaCorriente(int numeroCuenta, int saldo) {
		super(numeroCuenta, saldo);
	}

	@Override
	public boolean depositar(int monto, Scanner scanner) {
		if (monto > 0) {
		saldo += monto;
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
		//************** PERMITIR SOBREGIRO HASTA $500.000
		if (saldo + sobregiro >= monto) {
			saldo -= monto;
			return true;
		} else {
			Grafico.formatoError("Error: Saldo y sobregiro insuficientes para realizar el giro!");
			Pausa.pausa(scanner);
			return false;
		}
	}
	@Override
	public void calcularInteres() {
	}
}
