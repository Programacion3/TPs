package sistEcuLin.main;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import sistEcuLin.utilitarios.ManejoArchivos;

public class SEL {

	private int dimension;
	private double e = -1;
	private VectorMath vecB, vecX;
	private MatrizMath matA;

	// matA * vecX = vecB
	// vecX' = matA'(-1) * vecB

	public SEL(int n) {
		this.dimension = n;
		this.vecB = new VectorMath(n);
		this.vecX = new VectorMath(n);
		this.matA = new MatrizMath(n, n);
	}

	public SEL() {
		this.matA = new MatrizMath(0, 0);
		this.vecB = new VectorMath(0);
	}

	public SEL(int fil, int col) {
		this.matA = new MatrizMath(fil, col);
		this.vecB = new VectorMath(fil);
	}

	public SEL(String ePath) throws IOException {

		List<String> lista = ManejoArchivos.leerArchivo(ePath);

		String[] primeraLinea = lista.get(0).split(" ");
		int cantFilas = Integer.parseInt(primeraLinea[0]);
		int colCoeficients = Integer.parseInt(primeraLinea[1]) - 1;
		lista.remove(0);

		this.matA = new MatrizMath(cantFilas, cantFilas);
		this.vecB = new VectorMath(cantFilas);

		for (String linea : lista) {
			String[] aux = linea.split(" ");
			int fila = Integer.parseInt(aux[0]);
			int col = Integer.parseInt(aux[1]);
			if (col != colCoeficients)
				this.matA.setValor(fila, col, Double.parseDouble(aux[2]));
			else
				this.vecB.setValor(fila, Double.parseDouble(aux[2]));
		}
	}

	public String toString() {
		String aux = "EcuacionesLineales\ndimension: " + this.dimension
				+ "\te: " + e + "\n";
		aux += this.matA.toString();
		aux += this.vecB.toString() + "\n";
		aux += this.vecX.toString() + "\n";

		return aux;
	}

	public void calcularErrorSolucion() {
		VectorMath b = new VectorMath(this.dimension);
		b = this.matA.Producto(this.vecX);

		this.e = (this.vecB.Restar(b)).Norma();
	}

	public void generarOut(String dir) {
		FileWriter fichero = null;
		PrintWriter pw = null;
		try {
			fichero = new FileWriter(dir);
			pw = new PrintWriter(fichero);

			pw.println(this.dimension);

			for (int i = 0; i < this.vecX.getDimension(); i++)
				pw.println(this.vecX.getVec()[i]);

			pw.println(this.e);
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			try {
				// Nuevamente aprovechamos el finally para
				// asegurarnos que se cierra el fichero.
				if (null != fichero)
					fichero.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public void resolver() {
		MatrizMath inversa = new MatrizMath(this.getDimension(),
				this.getDimension());
		inversa = this.getMatA().inversa();
		this.setVecX(inversa.Producto(this.getVecB()));
	}

	public boolean test() {
		return this.e <= 0.0001;
	}

	public int getDimension() {
		return dimension;
	}

	public void setDimension(int dimension) {
		this.dimension = dimension;
	}

	public VectorMath getVecB() {
		return vecB;
	}

	public void setVecB(VectorMath vecB) {
		this.vecB = vecB;
	}

	public VectorMath getVecX() {
		return vecX;
	}

	public void setVecX(VectorMath vecX) {
		this.vecX = vecX;
	}

	public MatrizMath getMatA() {
		return matA;
	}

	public void setMatA(MatrizMath matA) {
		this.matA = matA;
	}

	public void mostrarResultado() {
		System.out.println(this);
	}

	public void generarOutput(String nombreArchivo) throws IOException {
		List<String> contenidoSalida = new ArrayList<String>();
		int i=0;
		boolean flag=false;
		String valor;
		while(i<this.vecX.getDimension()-1 && !flag) {
			valor=String.valueOf(this.vecX.getValor(i));
			flag=valor.equals("NaN");
			valor=flag?"Sin Solucion":String.valueOf(this.vecX.getValor(i));
			contenidoSalida.add(valor);
			i++;
		}
		ManejoArchivos.escribirArchivo(nombreArchivo, contenidoSalida);
	}
	
//	public void generarOutput(String nombreArchivo) throws IOException {
//		List<String> contenidoSalida = new ArrayList<String>();
//		for (int i = 0; i < this.vecX.getDimension()-1; i++) {
//			contenidoSalida.add(String.valueOf(this.vecX.getValor(i)));
//		}
//		ManejoArchivos.escribirArchivo(nombreArchivo, contenidoSalida);
//	}
}
