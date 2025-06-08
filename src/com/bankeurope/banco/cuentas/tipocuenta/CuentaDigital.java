package com.bankeurope.banco.cuentas.tipocuenta;

import java.util.Scanner;

import com.bankeurope.banco.cuentas.CuentaBancaria;
import com.bankeurope.banco.utilidades.Grafico;
import com.bankeurope.banco.utilidades.Pausa;

public class CuentaDigital extends CuentaBancaria {
	private double comision;
	
	public CuentaDigital(int numeroCuenta, int saldo) {
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
		calcularInteres();
		if (monto <= 0) {
			Grafico.formatoError("Error: El valor ingresado no es válido.");
			Pausa.pausa(scanner);
			return false;
		}
		if (saldo >= monto + comision) {
			saldo -= (monto + comision);
			return true;
		} else {
			Grafico.formatoError("Error: Saldo insuficiente para realizar el giro!");
			Pausa.pausa(scanner);
			return false;
		}
	}
	
	//**************** COMISION DEL 0.5% POR CADA GIRO
	@Override
	public void calcularInteres() {
	    comision = saldo * 0.005;
	    saldo -= (int) comision;
	}
}