package validaciones;

public class validar {
	
	public static String[] pintarLetras(String palabraAleatoria, String ultimaLetra,String[]celdas) {
		String[]palabraSplit=palabraAleatoria.split("");
		for(int i=0;i<palabraSplit.length;i++) {
			if(palabraSplit[i].equals(ultimaLetra)) {
				celdas[i]=ultimaLetra;
			}
		}
		return celdas;
	}
	
	public static boolean validarLetra(String letrasUsadas, String ultimaLetra) {
		boolean resultado=true;
		char caracter=ultimaLetra.charAt(0);
		if(!Character.isLetterOrDigit(caracter) || Character.isDigit(caracter)) {
			resultado=false;
			return resultado;
		}
		if(letrasUsadas.contains(ultimaLetra)) {
			resultado=false;
			return resultado;
		}
		else {
			return resultado;}
	}
	
	public static boolean comprobarFinal(String[]celdas,String palabraAleatoria) {
		Boolean finalizado=false;
		String celdas2=String.join("", celdas);
		if(celdas2.equals(palabraAleatoria)) {
			finalizado=true;
		}

		return finalizado;
	}
}
