package sistEcuLin.main;

import static java.lang.Math.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;

;

public class VectorMath {

	private int dimension;
	private double[] vec;

	public VectorMath(int dimension) {
		this.dimension = dimension;
		this.vec = new double[dimension];
	}

	public VectorMath(String dir) {
		File f = null;
		FileReader fr = null;
		BufferedReader br = null;

		try {
			f = new File(dir);
			fr = new FileReader(f);
			br = new BufferedReader(fr);

			String linea;
			linea = br.readLine();
			this.dimension = Integer.parseInt(linea);
			this.vec = new double[dimension];

			int i = 0;
			while ((linea = br.readLine()) != null)
				vec[i++] = Double.parseDouble(linea);
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public VectorMath Sumar(VectorMath v) {
		try {
			VectorMath aux = new VectorMath(v.dimension);

			for (int i = 0; i < this.dimension; ++i)
				aux.vec[i] = this.vec[i] + v.vec[i];

			return aux;
		}

		catch (Exception e) {
			System.out.println("No tiene la misma....");
			return null;
		}
	}

	public VectorMath Restar(VectorMath v) {
		try {
			VectorMath aux = new VectorMath(v.dimension);

			for (int i = 0; i < this.dimension; ++i)
				aux.vec[i] = this.vec[i] - v.vec[i];

			return aux;
		}

		catch (Exception e) {
			System.out.println("No tiene la misma....");
			return null;
		}
	}

	public VectorMath Producto(double esc) {
		VectorMath aux = new VectorMath(this.dimension);
		for (int i = 0; i < this.dimension; ++i)
			aux.vec[i] = this.vec[i] * esc;
		return aux;
	}

	public double Producto(VectorMath v) {
		double acum = 0;

		try {
			for (int i = 0; i < this.dimension; ++i)
				acum += this.vec[i] * v.vec[i];

			return acum;
		} catch (Exception e) {
			System.out.println("No tiene la misma dimensión");
			return 0;
		}
	}

	public VectorMath Producto(MatrizMath m) {
		if (this.dimension != m.getCol())
			return null;

		try {
			VectorMath aux = new VectorMath(this.dimension);

			for (int i = 0; i < m.getFil(); ++i)
				for (int j = 0; j < m.getCol(); ++j)
					aux.getVec()[i] += this.vec[j] * m.getM()[j][i];

			return aux;
		}

		catch (Exception e) {
			System.out.println("No son de la misma dimensión");
			return null;
		}
	}

	public void setDimension(int dimension) {
		this.dimension = dimension;
	}

	public void setVec(double[] vec) {
		this.vec = vec;
	}

	public String toString() {
		String aux = "";
		for (int i = 0; i < dimension; ++i)
			aux += vec[i] + "\t";
		return "VectorMath\ndimension: " + dimension + "\n" + aux;
	}

	public int getDimension() {
		return dimension;
	}

	public double[] getVec() {
		return vec;
	}

	public double Norma() {
		double aux = 0;
		for (int i = 0; i < this.dimension; ++i)
			aux += this.vec[i] * this.vec[i];

		return sqrt(aux);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VectorMath other = (VectorMath) obj;
		if (dimension != other.dimension)
			return false;
		if (!Arrays.equals(vec, other.vec))
			return false;
		return true;
	}

	public void setValor(int fila, double valor) {
		this.vec[fila] = valor;
	}

	public double getValor(int i) {
		return this.vec[i];
	}

}
