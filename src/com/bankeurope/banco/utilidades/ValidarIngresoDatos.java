package com.bankeurope.banco.utilidades;

import java.util.InputMismatchException;

public class ValidarIngresoDatos {
	public static boolean validarRut(String rut) {
		if (!rut.matches("\\d{1,2}\\.\\d{3}\\.\\d{3}[-][0-9kK]{1}")) {

			return false;
		}
		return true;
	}

	public static boolean validarTelefono(String telefono) {
	    try {
	        if (!telefono.matches("\\d{8}")) {
	            return false;
	        }
	        return true;
	    } catch (InputMismatchException e) {
	        return false;
	    }
	}
}