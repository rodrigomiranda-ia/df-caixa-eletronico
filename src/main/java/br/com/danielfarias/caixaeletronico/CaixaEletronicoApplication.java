package br.com.danielfarias.caixaeletronico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CaixaEletronicoApplication {
    Integer notas50 = 30;
    Integer notas20 = 40;
    Integer notas10 = 100;
    Integer notas5 = 42;
    Integer notas2 = 90;

	public static void main(String[] args) {
		SpringApplication.run(CaixaEletronicoApplication.class, args);
		System.out.println(calcularQuantidadeNotas(23234.0, "",30,40,100,42,90));
	}
	
	private static String calcularQuantidadeNotas(Double valor,String resultadoSaque,Integer notas50,Integer notas20,Integer notas10,Integer notas5,Integer notas2) {
		Double resto = 0.0;
		Integer resultado = 0;
        String stringResultado = "";
        
		
		if(valor >= 50.0) {
			resultado = calcularDivisao(valor, 50.0).intValue();
			if(notas50 >= resultado){ //se tiver notas suficientes em estoque, subtraio as notas usadas do estoque
				notas50 -= resultado;
				resto = calcularResto(valor, 50.0);
			}else { //se não tiver, pego todas as notas disponíveis
				resultado = notas50;
				notas50 = 0;
				resto = calcularResto(valor - (notas50*50), 50.0);
			}
			stringResultado += resultado + " notas de 50 \n";
		}else if(valor >= 20.0) {
			resto = calcularResto(valor, 20.0);
			resultado = calcularDivisao(valor, 20.0).intValue();
			if(notas20 >= resultado){
				notas20 -= resultado;
			}else { 
				resultado = notas20;
				notas20 = 0;
				resto = calcularResto(valor - (notas20*20), 20.0);			
			}
			stringResultado += resultado + " notas de 20 \n";
		}else if(valor >= 10.0) {
			resto = calcularResto(valor, 10.0);
			resultado = calcularDivisao(valor, 10.0).intValue();
			if(notas10 >= resultado){
				notas10 -= resultado;
			}else { 
				resultado = notas10;
				notas10 = 0;
				resto = calcularResto(valor - (notas10*10), 10.0);		
			}
			stringResultado += resultado + " notas de 10 \n";
		}else if(valor >= 5.0) {
			resto = calcularResto(valor, 5.0);
			resultado = calcularDivisao(valor, 5.0).intValue();
			if(notas5 >= resultado){
				notas5 -= resultado;
			}else { 
				resultado = notas5;
				notas5 = 0;
				resto = calcularResto(valor - (notas5*5), 5.0);
			}
			stringResultado += resultado + " notas de 5 \n";
		}else if(valor >= 2.0){
			resto = calcularResto(valor, 2.0);
			resultado = calcularDivisao(valor, 2.0).intValue();
			if(notas2 >= resultado){
				notas2 -= resultado;
			}else { 
				resultado = notas2;
				notas2 = 0;
				resto = calcularResto(valor - (notas2*2), 2.0);
			}
			stringResultado += resultado + " notas de 2 \n";
		}
		
      System.out.println("Resto: " + resto);
		if(resto > 0.0) {
			stringResultado += calcularQuantidadeNotas(resto, stringResultado,notas50,notas20,notas10,notas5,notas2);
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
