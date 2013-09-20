package sistEcuLin.utilitarios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ManejoArchivos {

	// Metodos //
	public static List<String> leerArchivo(String aPath) throws IOException {
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		String pathActual = new File(".").getCanonicalPath() + "\\INPUT\\";
		List<String> lista = new ArrayList<String>();

		try {
			// Apertura del fichero y creacion de BufferedReader para poder
			// hacer una lectura comoda (disponer del metodo readLine()).
			archivo = new File(pathActual + aPath);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			String linea;

			while ((linea = br.readLine()) != null)
				lista.add(linea);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return lista;
	}

	public static void escribirArchivo(String aPath, List<String> lista) throws IOException {

		FileWriter fichero = null;
		PrintWriter pw = null;
		String pathActual = new File(".").getCanonicalPath() + "\\OUTPUT_GENERADO\\";

		try {
			fichero = new FileWriter(pathActual + aPath);
			pw = new PrintWriter(fichero);
			for (String linea : lista) {
				pw.println(linea);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fichero)
					fichero.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
