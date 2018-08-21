package br.com.danielfarias.caixaeletronico.repository;

public class NotaRepositoryImpl implements NotaRepositoryCustom{
	
	@Override
	public String sacar(Double valor){
		System.out.println(calcularQuantidadeNotas(valor, ""));
		return calcularQuantidadeNotas(valor, "");
	}
	
	private String calcularQuantidadeNotas(Double valor,String resultadoSaque) {
		Double resto = 0.0;
		double resultado = 0.0;
        String stringResultado = "";
		
		if(valor >= 50.0) {
			resto = calcularResto(valor, 50.0);
			resultado = calcularDivisao(valor, 50.0);
			stringResultado += (int)resultado + " notas de 50 ";
		}else if(valor >= 20.0) {
			resto = calcularResto(valor, 20.0);
			resultado = calcularDivisao(valor, 20.0);
			stringResultado += (int)resultado + " notas de 20 ";
		}else if(valor >= 10.0) {
			resto = calcularResto(valor, 10.0);
			resultado = calcularDivisao(valor, 10.0);
			stringResultado += (int)resultado + " notas de 10 ";
		}else if(valor >= 5.0) {
			resto = calcularResto(valor, 5.0);
			resultado = calcularDivisao(valor, 5.0);
			stringResultado += (int)resultado + " notas de 5 ";
		}else if(valor >= 2.0){
			resto = calcularResto(valor, 2.0);
			resultado = calcularDivisao(valor, 2.0);
			stringResultado += (int)resultado + " notas de 2 ";
		}
		
      System.out.println("Resto: " + resto);
		if(resto > 0.0) {
			stringResultado += calcularQuantidadeNotas(resto, stringResultado);
		}
		
		return stringResultado;
	}
	
	private Double calcularResto(Double dividendo, Double divisor) {
		return dividendo%divisor;
	}
	
	private Double calcularDivisao(Double dividendo, Double divisor) {
		return dividendo/divisor;
	}

}
