package sistEcuLin.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import static java.lang.Math.*;

public class MatrizMath {

	private int fil, col;
	private double m[][];

	public MatrizMath(int i, int j) {
		this.m = new double[i][j];
		this.fil = i;
		this.col = j;
	}

	public MatrizMath(String dir) {
		File f = null;
		FileReader fr = null;
		BufferedReader br = null;

		try {
			f = new File(dir);
			fr = new FileReader(f);
			br = new BufferedReader(fr);

			String linea;
			linea = br.readLine();
			String[] datos;
			datos = linea.split(" ");
			this.fil = Integer.parseInt(datos[0]);
			this.col = Integer.parseInt(datos[1]);
			this.m = new double[fil][col];

			while ((linea = br.readLine()) != null) {
				datos = linea.split(" ");
				m[Integer.parseInt(datos[0])][Integer.parseInt(datos[1])] = Double
						.parseDouble(datos[2]);
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public MatrizMath Sumar(MatrizMath v) {
		try {
			MatrizMath aux = new MatrizMath(v.fil, v.col);

			for (int i = 0; i < this.fil; ++i)
				for (int j = 0; j < this.col; ++j)
					aux.m[i][j] = this.m[i][j] + v.m[i][j];
			return aux;
		}

		catch (Exception e) {
			System.out.println("No son de la misma dimensión");
			return null;
		}
	}

	public MatrizMath Restar(MatrizMath v) {
		try {
			MatrizMath aux = new MatrizMath(v.fil, v.col);

			for (int i = 0; i < this.fil; ++i)
				for (int j = 0; j < this.col; ++j)
					aux.m[i][j] = this.m[i][j] - v.m[i][j];

			return aux;
		}

		catch (Exception e) {
			System.out.println("No son de la misma dimensión");
			return null;
		}
	}

	public MatrizMath Producto(double esc) {
		try {
			MatrizMath aux = new MatrizMath(this.fil, this.col);

			for (int i = 0; i < this.fil; ++i)
				for (int j = 0; j < this.col; ++j)
					aux.m[i][j] = this.m[i][j] * esc;

			return aux;
		}

		catch (Exception e) {
			System.out.println("No son de la misma dimensión");
			return null;
		}
	}

	public MatrizMath Producto(MatrizMath mat) {
		try {
			MatrizMath aux = new MatrizMath(this.fil, mat.col);

			for (int i = 0; i < this.fil; ++i)
				for (int j = 0; j < mat.col; ++j)
					for (int k = 0; k < this.col; ++k)
						aux.m[i][j] += this.m[i][k] * mat.m[k][j];

			return aux;
		} catch (Exception e) {
			System.out.println("No son de la misma dimensión");
			return null;
		}
	}

	public VectorMath Producto(VectorMath vec) {
		if (this.col != vec.getDimension())
			return null;

		try {
			VectorMath aux = new VectorMath(this.fil);

			for (int i = 0; i < this.fil; ++i)
				for (int j = 0; j < this.col; ++j)
					aux.getVec()[i] += this.m[i][j] * vec.getVec()[j];

			return aux;
		}

		catch (Exception e) {
			System.out.println("No son de la misma dimensión");
			return null;
		}
	}

	public int getFil() {
		return fil;
	}

	public void setFil(int fil) {
		this.fil = fil;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	// public String toString() {
	// String aux = "";
	// for(int i = 0; i < this.fil; ++i)
	// for(int j = 0; j < this.col; ++j)
	// aux += "m[" + i + "][" + j + "]: " + m[i][j] + "\n";
	//
	// return "MatrizMath\n(fil, col): (" + this.fil + ", " + this.col + ")\n" +
	// aux;
	// }

	public String toString() {
		String aux = "";
		for (int i = 0; i < this.fil; ++i) {
			for (int j = 0; j < this.col; ++j)
				aux += m[i][j] + "\t";
			aux += "\n";
		}
		return "MatrizMath\n(fil, col): (" + this.fil + ", " + this.col + ")\n"
				+ aux;
	}

	public double[][] getM() {
		return m;
	}

	public void setM(double[][] m) {
		this.m = m;
	}

	public MatrizMath anadeFila(int i1, int i2, double k) {
		MatrizMath resultado = new MatrizMath(this.fil, this.col);
		for (int i = 0; i < this.fil; i++) {
			for (int j = 0; j < this.col; j++) {
				if (i == i1) {
					// A los elementos de la fila i1 le sumamos k veces el
					// elemento correspondiente de la fila i2
					resultado.m[i1][j] = this.m[i][j] + k * this.m[i2][j];
				} else {
					resultado.m[i][j] = this.m[i][j]; // Los demás elementos los
														// dejamos tal cual
				}
			}
		}
		return resultado;
	}

	public MatrizMath gaussElim() {
		MatrizMath resultado = this; // Empezamos con la matriz actual como
										// resultado;
		// no hay problema porque las operaciones de fila no modifican la
		// original sino que devuelven matrices nuevas
		for (int j = 0; j < this.col; j++) {
			for (int i = j + 1; i < this.fil; i++) {
				resultado = resultado.anadeFila(i, j, -resultado.m[i][j]
						/ resultado.m[j][j]);
			}
		}
		return resultado;
	}

	public MatrizMath multiplicaFila(int i1, double k) {
		MatrizMath resultado = new MatrizMath(this.fil, this.col);
		for (int i = 0; i < this.fil; i++) {
			for (int j = 0; j < this.col; j++) {
				if (i == i1) {
					resultado.m[i1][j] = k * this.m[i][j]; // Los elementos de
															// la fila i1 la
															// multiplicamos por
															// k
				} else {
					resultado.m[i][j] = this.m[i][j]; // Los demás elementos los
														// dejamos tal cual
				}
			}
		}
		return resultado;
	}

	public MatrizMath gaussJordanElim() {
		MatrizMath resultado = this.gaussElim(); // Empezamos con la matriz
													// triangular superior como
													// resultado
		for (int i = 0; i < resultado.fil; i++) {
			resultado = resultado.multiplicaFila(i, 1.0 / resultado.m[i][i]); // Hacemos
																				// los
																				// pivotes
																				// igual
																				// a
																				// la
																				// unidad
		}
		for (int j = 0; j < this.fil; j++) {
			for (int i = 0; i < j; i++) {
				resultado = resultado.anadeFila(i, j, -resultado.m[i][j]
						/ resultado.m[j][j]); // Anulamos los elementos por
												// encima de los pivotes (i<j)
			}
		}
		return resultado;
	}

	public MatrizMath inversa() {
		// Primero creamos una nueva matriz que concatena la actual y la matriz
		// identidad
		MatrizMath tmp = new MatrizMath(this.fil, 2 * this.col);
		for (int i = 0; i < this.fil; i++) {
			// Primera mitad, matriz original
			for (int j = 0; j < this.col; j++) {
				tmp.m[i][j] = this.m[i][j];
			}
			// Segunda mitad, matriz identidad
			for (int j = this.col; j < 2 * this.col; j++) {
				if (i + this.col == j) {
					tmp.m[i][j] = 1; // Valen 1 los elementos de la diagonal de
										// la parte derecha
				} else {
					tmp.m[i][j] = 0; // Valen 0 el resto de los elementos
				}
			}
		}
		// Segundo: aplicamos el método de eliminación de Gauss-Jordan
		tmp = tmp.gaussJordanElim();
		// Tercero: creamos una nueva matriz que equivale a la parte derecha de
		// la matriz actual
		MatrizMath resultado = new MatrizMath(this.fil, this.col);
		for (int i = 0; i < this.fil; i++) {
			for (int j = 0; j < this.col; j++) {
				resultado.m[i][j] = tmp.m[i][j + this.col];
			}
		}
		return resultado;
	}

	public double error(MatrizMath inv) {
		MatrizMath ide = new MatrizMath(this.fil, this.col);
		double sum = 0;
		for (int i = 0; i < this.fil; i++)
			ide.m[i][i] = 1;
		MatrizMath merr = ide.Restar(this.Producto(inv));
		for (int i = 0; i < merr.fil; i++)
			for (int j = 0; j < merr.col; j++)
				sum += merr.m[i][j] * merr.m[i][j];
		return sqrt(sum);
	}

	public void setValor(int fila, int col2, double valor) {
		this.m[fila][col2] = valor;
	}

}
