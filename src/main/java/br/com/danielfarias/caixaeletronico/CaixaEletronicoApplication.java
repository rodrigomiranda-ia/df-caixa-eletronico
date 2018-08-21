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
		Integer resultado = 0;
        String stringResultado = "";
        
        Integer notas50 = 30;
        Integer notas20 = 40;
        Integer notas10 = 100;
        Integer notas5 = 42;
        Integer notas2 = 90;
        
		
		if(valor >= 50.0) {
			resto = calcularResto(valor, 50.0);
			resultado = calcularDivisao(valor, 50.0).intValue();
			if(resultado > notas50){
				
			}
			stringResultado += resultado + " notas de 50 \n";
		}else if(valor >= 20.0) {
			resto = calcularResto(valor, 20.0);
			resultado = calcularDivisao(valor, 20.0).intValue();
			stringResultado += resultado + " notas de 20 \n";
		}else if(valor >= 10.0) {
			resto = calcularResto(valor, 10.0);
			resultado = calcularDivisao(valor, 10.0).intValue();
			stringResultado += resultado + " notas de 10 \n";
		}else if(valor >= 5.0) {
			resto = calcularResto(valor, 5.0);
			resultado = calcularDivisao(valor, 5.0).intValue();
			stringResultado += resultado + " notas de 5 \n";
		}else if(valor >= 2.0){
			resto = calcularResto(valor, 2.0);
			resultado = calcularDivisao(valor, 2.0).intValue();
			stringResultado += resultado + " notas de 2 \n";
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
