package sistEcuLin.utilitarios;

public class Utilitarios {

	public static String rendimiento(long ini, long fin) {

		long milisec = fin - ini;
		long hora = milisec / 3600000;
		long reshor = milisec % 3600000;
		long minuto = reshor / 60000;
		long restominuto = reshor % 60000;
		long segundo = restominuto / 1000;
		long restosegundo = restominuto % 1000;
		return hora + ":" + minuto + ":" + segundo + ":" + restosegundo;

	}

}
