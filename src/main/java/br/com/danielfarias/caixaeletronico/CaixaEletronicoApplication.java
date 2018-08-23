package br.com.danielfarias.caixaeletronico;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.danielfarias.caixaeletronico.enums.ValorNota;
import br.com.danielfarias.caixaeletronico.model.Nota;

@SpringBootApplication
public class CaixaEletronicoApplication {
    Integer notas50 = 30;
    Integer notas20 = 40;
    Integer notas10 = 100;
    Integer notas5 = 42;
    Integer notas2 = 90;

	public static void main(String[] args) {
		SpringApplication.run(CaixaEletronicoApplication.class, args);
		System.out.println(calcularQuantidadeNotas(3400.0, "",30,40,100,42,90)); //3690
	}
	
	/*private Integer obterQuantidadeNotas(Double valorSaque, Nota nota){
		Double valorNota = nota.getValorNota().getValor();
		Integer resultado = calcularDivisao(valorSaque, valorNota).intValue();
		Integer qtdNotas = nota.getQuantidadeNotas();
		
		if(qtdNotas >= resultado){ //se tiver notas suficientes em estoque, subtraio as notas usadas do estoque
			qtdNotas -= resultado;
			resto = calcularResto(valorSaque, valorNota);
		}else { //se não tiver notas suficientes, pego todas as notas disponíveis
			resultado = qtdNotas;
			resto = valorSaque - (qtdNotas * valorNota);
			qtdNotas = 0;
		}
			
		return resultado;
	}*/
	
	
	private static List<Nota> calcularQuantidadeNotas(Double valorSaque,Integer qtdNotas50,Integer qtdNotas20,Integer qtdNotas10,Integer qtdNotas5,Integer qtdNotas2) {
		Double resto = 0.0;
		Integer resultado = 0;
		List<Nota> notas = new ArrayList<>();
		Double valorNota = 0.0;
		
		if(valorSaque >= ValorNota.CINQUENTA.getValor() && qtdNotas50 > 0) { //testo se o valor é maior ou igual a esta nota e se tem notas disponíveis deste valor
			valorNota = ValorNota.CINQUENTA.getValor();
			resultado = calcularDivisao(valorSaque, valorNota).intValue();
			if(qtdNotas50 >= resultado){ //se tiver notas suficientes em estoque, subtraio as notas usadas do estoque
				qtdNotas50 -= resultado;
				resto = calcularResto(valorSaque, valorNota);
			}else { //se não tiver notas suficientes, pego todas as notas disponíveis
				resultado = qtdNotas50;
				resto = valorSaque - (qtdNotas50 * valorNota);
				qtdNotas50 = 0;
			}
			notas.add(new Nota(ValorNota.CINQUENTA,resultado));
		}else if(valorSaque >= ValorNota.VINTE.getValor() && qtdNotas20 > 0) {
			valorNota = ValorNota.VINTE.getValor();
			resto = calcularResto(valorSaque, valorNota);
			resultado = calcularDivisao(valorSaque, valorNota).intValue();
			if(qtdNotas20 >= resultado){
				qtdNotas20 -= resultado;
			}else { 
				resultado = qtdNotas20;
				resto = valorSaque - (qtdNotas20 * valorNota);
				qtdNotas20 = 0;			
			}
			notas.add(new Nota(ValorNota.VINTE,resultado));
		}else if(valorSaque >= ValorNota.DEZ.getValor() && qtdNotas10 > 0) {
			valorNota = ValorNota.DEZ.getValor();
			resto = calcularResto(valorSaque, valorNota);
			resultado = calcularDivisao(valorSaque, valorNota).intValue();
			if(qtdNotas10 >= resultado){
				qtdNotas10 -= resultado;
			}else { 
				resultado = qtdNotas10;
				resto = valorSaque - (qtdNotas10 * valorNota);
				qtdNotas10 = 0;		
			}
			notas.add(new Nota(ValorNota.DEZ,resultado));
		}else if(valorSaque >= ValorNota.CINCO.getValor() && qtdNotas5 > 0) {
			valorNota = ValorNota.CINCO.getValor();
			resto = calcularResto(valorSaque, valorNota);
			resultado = calcularDivisao(valorSaque, valorNota).intValue();
			if(qtdNotas5 >= resultado){
				qtdNotas5 -= resultado;
			}else { 
				resultado = qtdNotas5;
				resto = valorSaque - (qtdNotas5 * valorNota);
				qtdNotas5 = 0;
			}
			notas.add(new Nota(ValorNota.CINCO,resultado));
		}else if(valorSaque >= ValorNota.DOIS.getValor() && qtdNotas2 > 0){
			valorNota = ValorNota.DOIS.getValor();
			resto = calcularResto(valorSaque, valorNota);
			resultado = calcularDivisao(valorSaque, valorNota).intValue();
			if(qtdNotas2 >= resultado){
				qtdNotas2 -= resultado;
			}else { 
				resultado = qtdNotas2;
				resto = valorSaque - (qtdNotas2 * valorNota);
				qtdNotas2 = 0;
			}
			notas.add(new Nota(ValorNota.DOIS,resultado));
		}
		
		if(resto > 0.0) {
			calcularQuantidadeNotas(resto,qtdNotas50,qtdNotas20,qtdNotas10,qtdNotas5,qtdNotas2);
		}
		
		return notas;
	}
	
	private static Double calcularResto(Double dividendo, Double divisor) {
		return dividendo%divisor;
	}
	
	private static Double calcularDivisao(Double dividendo, Double divisor) {
		return dividendo/divisor;
	}
}
