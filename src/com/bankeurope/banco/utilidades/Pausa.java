package com.bankeurope.banco.utilidades;

import java.util.Scanner;

public class Pausa {

    public static void pausa(Scanner scanner) {
        Grafico.lineaSeparacion();
        Grafico.formatoIngresoDatos("Presione Enter para continuar...");
        scanner.nextLine();
    }
}