package com.bankeurope.banco.managers;
/*
 * Clase singleton que gestiona los clientes del banco.
 * Permite agregar, buscar clientes y cargar datos iniciales de prueba.
 */
import com.bankeurope.banco.clientes.Cliente;
import java.util.HashMap;
import java.util.Map;

public class BancoManager {
	private Map<String, Cliente> clientes;

	private static BancoManager instancia;

	private BancoManager() {
		this.clientes = new HashMap<>();
	}

	public static BancoManager getInstancia() {
		if (instancia == null) {
			instancia = new BancoManager();
		}
		return instancia;
	}

	public boolean agregarCliente(Cliente cliente) {
		if (clientes.containsKey(cliente.getRut())) {
			return false;
		}
		clientes.put(cliente.getRut(), cliente);
		return true;
	}

	public Cliente buscarCliente(String rut) {
		return clientes.get(rut);
	}
	
	// ************* PUSE ALGUNOS DATOS INICIALES PARA HACER PRUEBAS *************
	public void cargarDatosIniciales() {
	    Cliente cliente = new Cliente("12.345.678-9", "Juan", "Pérez", "González", "Calle Falsa 123", "Santiago", "98765432", "Ahorro", 12345678);
	    agregarCliente(cliente);
	    cliente.getCuenta().setSaldo(500000);
	    cliente = new Cliente("21.765.432-1", "Ana", "Ramírez", "Torres", "Avenida Siempre Viva 742", "Providencia", "91234678", "Corriente", 217654321);
	    agregarCliente(cliente);
	    cliente.getCuenta().setSaldo(1550000);
	    cliente = new Cliente("22.725.833-4", "Pedro", "Fernández", "Soto", "Vicuña Mackena 1512", "Maipu", "98415421", "Digital", 227258334);
	    agregarCliente(cliente);
	    cliente.getCuenta().setSaldo(220000);
	    cliente = new Cliente("11.187.592-2", "María", "Muñoz", "Alvarez", "Avenida las Piedras 777", "La Florida", "94481102", "Ahorro", 111875922);
	    agregarCliente(cliente);
	    cliente.getCuenta().setSaldo(805000);
	    cliente = new Cliente("9.920.212-8", "Claudio", "Nuñez", "Sobarzo", "José Arrieta 220", "PPeñalolén", "95319488", "Corriente", 99202128);
	    agregarCliente(cliente);
	    cliente.getCuenta().setSaldo(380000);
	}
}