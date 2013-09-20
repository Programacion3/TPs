package sistEcuLin.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;

import javax.swing.text.Utilities;

import sistEcuLin.utilitarios.ManejoArchivos;

public class Main {

	public static void main(String[] args) throws IOException {

		// DECLARACIONES DE VARIABLES Y OBJETOS
		// //////////////////////////////////////////////////////////
		SEL sistema = null;
		List<String> contenidoArchivoEntrada;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// LECTURA DE LA RUTA CARGADA DESDE LA CONSOLA
		// //////////////////////////////////////////////////////////
		System.out.println("\nIngrese el nombre del archivo '.in'\n");
		String nombreArchivo = br.readLine();

		// LECTURA DEL ARCHIVO Y VALIDACION DE FORMATO SOBRE EL MISMO
		// //////////////////////////////////////////////////////////
		contenidoArchivoEntrada = ManejoArchivos.leerArchivo(nombreArchivo);
		if (contenidoArchivoEntrada.isEmpty()
				|| contenidoArchivoEntrada.size() == 1) {
			System.out.println("\nArchivo '.in' invalido o vacio\n");
			System.exit(1);
		}

		sistema = new SEL(nombreArchivo);
		// PRUEBA DE RENDIMIENTO//
		long tiempoI = System.currentTimeMillis();
		/////////////////////////
		sistema.resolver();
		sistema.calcularErrorSolucion();
		sistema.mostrarResultado();

		if (sistema.test())
			System.out.println("Error aceptable");
		else
			System.out.println("Error elevado");

		String nombreArchivoSalida = nombreArchivo.substring(0,
				nombreArchivo.lastIndexOf('.'))
				+ ".out";
		sistema.generarOutput(nombreArchivoSalida);

		System.out.println("\nSu archivo se ha generado con exito\n");

		// Para cálculo del tiempo de rendimiento//
		long tiempoF = System.currentTimeMillis();
		System.out.println(sistEcuLin.utilitarios.Utilitarios.rendimiento(
				tiempoI, tiempoF));

		System.exit(0);
	}

}
