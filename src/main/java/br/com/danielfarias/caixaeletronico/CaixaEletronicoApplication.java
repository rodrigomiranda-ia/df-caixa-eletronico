package br.com.danielfarias.caixaeletronico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CaixaEletronicoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CaixaEletronicoApplication.class, args);
		System.out.println(calcularQuantidadeNotas(2175.0, ""));
	}
	
	private static String calcularQuantidadeNotas(Double valor,String resultadoSaque) {
		Double resto = 0.0;
		double resultado = 0.0;
        String stringResultado = "";
		
		if(valor >= 50.0) {
			resto = calcularResto(valor, 50.0);
			resultado = calcularDivisao(valor, 50.0);
			stringResultado += (int)resultado + " notas de 50 \n";
		}else if(valor >= 20.0) {
			resto = calcularResto(valor, 20.0);
			resultado = calcularDivisao(valor, 20.0);
			stringResultado += (int)resultado + " notas de 20 \n";
		}else if(valor >= 10.0) {
			resto = calcularResto(valor, 10.0);
			resultado = calcularDivisao(valor, 10.0);
			stringResultado += (int)resultado + " notas de 10 \n";
		}else if(valor >= 5.0) {
			resto = calcularResto(valor, 5.0);
			resultado = calcularDivisao(valor, 5.0);
			stringResultado += (int)resultado + " notas de 5 \n";
		}else if(valor >= 2.0){
			resto = calcularResto(valor, 2.0);
			resultado = calcularDivisao(valor, 2.0);
			stringResultado += (int)resultado + " notas de 2 \n";
		}
		
      System.out.println("Resto: " + resto);
		if(resto > 0.0) {
			stringResultado += calcularQuantidadeNotas(resto, stringResultado);
		}
		
		return stringResultado;
	}
	
	private static Double calcularResto(Double dividendo, Double divisor) {
		return dividendo%divisor;
	}
	
	private static Double calcularDivisao(Double dividendo, Double divisor) {
		return dividendo/divisor;
	}
}
