package com.bankeurope.banco;

import com.bankeurope.banco.managers.BancoManager;
import com.bankeurope.banco.clientes.Cliente;
import com.bankeurope.banco.utilidades.Grafico;
import com.bankeurope.banco.utilidades.Pausa;
import com.bankeurope.banco.utilidades.ValidarIngresoDatos;

import java.util.Scanner;

public class Menu {

	private Scanner scanner;
	private BancoManager bancoManager;
	private Cliente clienteActual;

	public Menu() {
		scanner = new Scanner(System.in);
		bancoManager = BancoManager.getInstancia();
		clienteActual = null;
	}

	public void mostrarMenu() {
		int opcion;
		do {
			Grafico.formatoTitulo("******** SISTEMA DE GESTION BANK EUROPE ********");
			System.out.println("1 . Registrar cliente");
			System.out.println("2 . Buscar cliente");
			System.out.println("3 . Ver datos del Cliente");
			System.out.println("4 . Depositar");
			System.out.println("5 . Girar");
			System.out.println("6 . Consultar Saldo");
			System.out.println("7 . Salir");
			Grafico.formatoIngresoDatos("\nIngrese la opción deseada: ");

			try {
				opcion = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				opcion = 0;
			}

			switch (opcion) {
			case 1 -> registrarCliente();
			case 2 -> buscarCliente();
			case 3 -> verDatosCliente();
			case 4 -> depositarDinero();
			case 5 -> girarDinero();
			case 6 -> consultarSaldo();
			case 7 -> Grafico.formatoSaludo("GRACIAS POR VISITAR BANK EUROPE !!!");
			default -> Grafico.formatoError("Error: La opción ingresada no es válida!");
			}
		} while (opcion != 7);
	}

	private void registrarCliente() {
		String rut;
		String telefono;
		String tipoCuenta;
		
		while (true) {
			Grafico.formatoTitulo("******** REGISTRAR CLIENTE ********");
			Grafico.formatoIngresoDatos("Ingrese Rut (ej: 12.345.678-9) o 's' para salir: ");
			rut = scanner.nextLine();

			if (rut.equalsIgnoreCase("s")) {
				Grafico.formatoError("REGISTRO CANCELADO");
				Pausa.pausa(scanner);
				return;	
			}
			
			if (!ValidarIngresoDatos.validarRut(rut)) {
				Grafico.formatoError("Error: Formato de Rut no válido (ej: 12.345.678-9)");
				continue;
			}

			if (bancoManager.buscarCliente(rut) != null) {
				Grafico.formatoError("Error: El rut ingresado, ya se encuentra en nuestros registros!");
				continue;
			} else {
				break;
			}
		}
		
		while (true) {
			System.out.println("\n1 . Cuenta de Ahorro");
			System.out.println(  "2 . Cuenta Corriente");
			System.out.println(  "3 . Cuenta Digital");
			Grafico.formatoIngresoDatos("\nIngrese el número del tipo de cuenta: ");
			tipoCuenta = scanner.nextLine();

			switch (tipoCuenta) {
			case "1":
				tipoCuenta = "Ahorro";
				break;
			case "2":
				tipoCuenta = "Corriente";
				break;
			case "3":
				tipoCuenta = "Digital";
				break;

			default:
				Grafico.formatoError("Error: Seleccione una opción válida");
				continue;
			}
			break;
		}
		
		Grafico.formatoIngresoDatos("\nIngrese el nombre: ");
		String nombre = scanner.nextLine();
		Grafico.formatoIngresoDatos("\nIngrese el apellido paterno: ");
		String apellidoPaterno = scanner.nextLine();
		Grafico.formatoIngresoDatos("\nIngrese el apellido materno: ");
		String apellidoMaterno = scanner.nextLine();
		Grafico.formatoIngresoDatos("\nIngrese el domicilio: ");
		String domicilio = scanner.nextLine();
		Grafico.formatoIngresoDatos("\nIngrese la comuna: ");
		String comuna = scanner.nextLine();
		
		while (true) {
		Grafico.formatoIngresoDatos("\nIngrese número telefónico (ej: 12345678)");
		System.out.print("(+56 9)");
		telefono = scanner.nextLine();
		
		if (!ValidarIngresoDatos.validarTelefono(telefono)) {
			Grafico.formatoError("Error: Ingrese los 8 digitos del número telefónico!");
			continue;
		}
		break;
		}
		
		int numeroCuenta = Integer.parseInt(rut.replaceAll("[^0-9]", ""));
		try {
			Cliente nuevoCliente = new Cliente(rut, nombre, apellidoPaterno, apellidoMaterno, domicilio, comuna,
					telefono, tipoCuenta, numeroCuenta);
			if (nuevoCliente.registrarCliente(scanner) && bancoManager.agregarCliente(nuevoCliente)) {
				clienteActual = nuevoCliente;
			}
		} catch (IllegalArgumentException e) {
			Grafico.formatoError("Error: " + e.getMessage());
		}
	}

	private void buscarCliente() {
		String rut;
		
		while (true) {
		Grafico.formatoTitulo("******** BUSCAR CLIENTE ********");
		Grafico.formatoIngresoDatos("Ingrese rut (ej: 12.345.678-9) o 's' para salir: ");
		rut = scanner.nextLine();

		if (rut.equalsIgnoreCase("s")) {
			Grafico.formatoError("BÚSQUEDA CANCELADA");
			Pausa.pausa(scanner);
			return;	
		}
		
		if (!ValidarIngresoDatos.validarRut(rut)) {
			Grafico.formatoError("Error: Formato de Rut no válido (ej: 12.345.678-9)");
			continue;
		}
		
		Cliente cliente = bancoManager.buscarCliente(rut);

		if (cliente != null) {
			Grafico.lineaSeparacion();
			cliente.mostrarInformacionCliente(scanner);
			clienteActual = cliente;
			break;
		} else {
			Grafico.formatoError("Error: No se encontró un cliente con el rut ingresado!");
			Pausa.pausa(scanner);
		}
		}
	}

	private void verDatosCliente() {
		if (clienteActual == null) {
			Grafico.formatoError("Error: No hay cliente seleccionado o registrado!");
			Pausa.pausa(scanner);
			return;
		}
		System.out.println();
		clienteActual.mostrarInformacionCliente(scanner);
	}

	private void depositarDinero() {
		if (clienteActual == null) {
			Grafico.formatoError("Error: No hay cliente seleccionado o registrado!");
			Pausa.pausa(scanner);
			return;
		}
		while (true) {
			Grafico.formatoTitulo("******** DEPOSITO ********");
			Grafico.formatoIngresoDatos("Ingrese monto a depositar: ");
			try {
				int monto = Integer.parseInt(scanner.nextLine());
				if (monto <= 0) {
					Grafico.formatoError("Error: El monto debe ser mayor a cero!");
					continue;
				}
				if (clienteActual.getCuenta().depositar(monto, scanner)) {
					Grafico.formatoExito("DEPOSITO REALIZADO EXITOSAMENTE");
					System.out.println(Grafico.FORMATO_DINERO.format(monto));
					Pausa.pausa(scanner);
					break;
				}
			} catch (NumberFormatException e) {
				Grafico.formatoError("Error: El monto ingresado no es válido!");
			}
		}
	}
	
	private void girarDinero() {
		if (clienteActual == null) {
			Grafico.formatoError("Error: No hay cliente seleccionado o registrado!");
			Pausa.pausa(scanner);
			return;
		}
		
		while (true) {
			Grafico.formatoTitulo("******** GIRO ********");
			Grafico.formatoIngresoDatos("Ingrese monto a girar: ");
			try {
				int monto = Integer.parseInt(scanner.nextLine());
				if (monto <= 0) {
					Grafico.formatoError("Error: El monto debe ser mayor a cero!");
					continue;
				}
				if (clienteActual.getCuenta().girar(monto, scanner)) {
					Grafico.formatoExito("GIRO REALIZADO EXITOSAMENTE");
					System.out.println(Grafico.FORMATO_DINERO.format(monto));
					Pausa.pausa(scanner);
					break;
				} else {
					break;
				}
			} catch (NumberFormatException e) {
				Grafico.formatoError("Error: El monto ingresado no es válido!");
			}
		}
	}

	private void consultarSaldo() {
		if (clienteActual == null) {
			Grafico.formatoError("Error: No hay cliente seleccionado o registrado!");
			Pausa.pausa(scanner);
			return;
		}
		Grafico.formatoTitulo("******** CONSULTA DE SALDO ********");
		int saldo = clienteActual.getCuenta().consultarSaldo();
		System.out.println("Saldo actual: $" + String.format("%,d", saldo).replace(',', '.'));
		Pausa.pausa(scanner);
	}
}