package com.bankeurope.banco;

import com.bankeurope.banco.managers.BancoManager;
import com.bankeurope.banco.utilidades.Grafico;

public class Main {

	public static void main(String[] args) {

		Grafico.formatoSaludo("        !!! BIENVENID@ A BANK EUROPE !!!        ");

        // BancoManager.getInstancia().cargarDatosIniciales(); //DESCOMENTAR PARA PRUEBAS
        
		Menu menu = new Menu();
		menu.mostrarMenu();
	}
}