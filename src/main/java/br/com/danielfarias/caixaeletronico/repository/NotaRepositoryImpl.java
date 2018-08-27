package br.com.danielfarias.caixaeletronico.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.danielfarias.caixaeletronico.model.Nota;
import br.com.danielfarias.caixaeletronico.util.Constantes;

public class NotaRepositoryImpl implements NotaRepositoryCustom{
	
	@Autowired
	private NotaRepository notaRepository;
	
	@Override
	public List<Nota> sacar(Double valorSaque){
		List<Nota> notasDisponiveis = notaRepository.findAll();
		Nota nota50 = notasDisponiveis.stream().filter(n -> Constantes.CINQUENTA.equals(n.getValorNota())).findAny().orElse(null);
		Nota nota20 = notasDisponiveis.stream().filter(n -> Constantes.VINTE.equals(n.getValorNota())).findAny().orElse(null);
		Nota nota10 = notasDisponiveis.stream().filter(n -> Constantes.DEZ.equals(n.getValorNota())).findAny().orElse(null);
		Nota nota5 = notasDisponiveis.stream().filter(n -> Constantes.CINCO.equals(n.getValorNota())).findAny().orElse(null);
		Nota nota2 = notasDisponiveis.stream().filter(n -> Constantes.DOIS.equals(n.getValorNota())).findAny().orElse(null);
		
		List<Nota> notasNecessarias = new ArrayList<>();
		calcularQuantidadeNotas(valorSaque, nota50.getQuantidadeNotas(), nota20.getQuantidadeNotas(), nota10.getQuantidadeNotas(), nota5.getQuantidadeNotas(), nota2.getQuantidadeNotas(),notasNecessarias);
		atualizarEstoque(notasNecessarias);
		return notasNecessarias;
	}

	@Override
	public List<Nota> listarNotasDisponiveis() {
		return notaRepository.findAll();
	}
	
	@Override
	public Double obterEstoqueTotal() {
		return notaRepository.obterEstoqueTotal();
	}
	
	@Override
	public Nota salvar(Nota nota) {
		return notaRepository.save(nota);	
	}
	
	private void atualizarEstoque(List<Nota> notas) {
		for(Nota n: notas) {
			Nota nota = notaRepository.findByValorNota(n.getValorNota());
			Integer qtdNotasAtual = nota.getQuantidadeNotas() - n.getQuantidadeNotas(); //Subtraio as notas utilzadas no saque
			nota.setQuantidadeNotas(qtdNotasAtual);
			notaRepository.save(nota);
		}
	}
	
	private List<Nota> calcularQuantidadeNotas(Double valorSaque,Integer qtdNotas50,Integer qtdNotas20,Integer qtdNotas10,Integer qtdNotas5,Integer qtdNotas2,List<Nota> notas) {
		Double resto = 0.0;
		Integer resultado = 0;
		Double valorNota = 0.0;
		
		if(valorSaque >= Constantes.CINQUENTA && qtdNotas50 > 0) { //testo se o valor é maior ou igual a esta nota e se tem notas disponÃ­veis deste valor
			valorNota = Constantes.CINQUENTA;
			resultado = calcularDivisao(valorSaque, valorNota).intValue();
			if(qtdNotas50 >= resultado){ //se tiver notas suficientes em estoque, subtraio as notas usadas do estoque
				qtdNotas50 -= resultado;
				resto = calcularResto(valorSaque, valorNota);
			}else { //se não tiver notas suficientes, pego todas as notas disponíveis
				resultado = qtdNotas50;
				resto = valorSaque - (qtdNotas50 * valorNota);
				qtdNotas50 = 0;
			}
			notas.add(new Nota(Constantes.CINQUENTA,resultado));
		}else if(valorSaque >= Constantes.VINTE && qtdNotas20 > 0) {
			valorNota = Constantes.VINTE;
			resto = calcularResto(valorSaque, valorNota);
			resultado = calcularDivisao(valorSaque, valorNota).intValue();
			if(qtdNotas20 >= resultado){
				qtdNotas20 -= resultado;
			}else { 
				resultado = qtdNotas20;
				resto = valorSaque - (qtdNotas20 * valorNota);
				qtdNotas20 = 0;			
			}
			notas.add(new Nota(Constantes.VINTE,resultado));
		}else if(valorSaque >= Constantes.DEZ && qtdNotas10 > 0) {
			valorNota = Constantes.DEZ;
			resto = calcularResto(valorSaque, valorNota);
			resultado = calcularDivisao(valorSaque, valorNota).intValue();
			if(qtdNotas10 >= resultado){
				qtdNotas10 -= resultado;
			}else { 
				resultado = qtdNotas10;
				resto = valorSaque - (qtdNotas10 * valorNota);
				qtdNotas10 = 0;		
			}
			notas.add(new Nota(Constantes.DEZ,resultado));
		}else if(valorSaque >= Constantes.CINCO && qtdNotas5 > 0) {
			valorNota = Constantes.CINCO;
			resto = calcularResto(valorSaque, valorNota);
			resultado = calcularDivisao(valorSaque, valorNota).intValue();
			if(qtdNotas5 >= resultado){
				qtdNotas5 -= resultado;
			}else { 
				resultado = qtdNotas5;
				resto = valorSaque - (qtdNotas5 * valorNota);
				qtdNotas5 = 0;
			}
			notas.add(new Nota(Constantes.CINCO,resultado));
		}else if(valorSaque >= Constantes.DOIS && qtdNotas2 > 0){
			valorNota = Constantes.DOIS;
			resto = calcularResto(valorSaque, valorNota);
			resultado = calcularDivisao(valorSaque, valorNota).intValue();
			if(qtdNotas2 >= resultado){
				qtdNotas2 -= resultado;
			}else { 
				resultado = qtdNotas2;
				resto = valorSaque - (qtdNotas2 * valorNota);
				qtdNotas2 = 0;
			}
			notas.add(new Nota(Constantes.DOIS,resultado));
		}
		
		if(resto > 0.0) {
			calcularQuantidadeNotas(resto,qtdNotas50,qtdNotas20,qtdNotas10,qtdNotas5,qtdNotas2,notas);
		}
		
		return notas;
	}
	
	private Double calcularResto(Double dividendo, Double divisor) {
		return dividendo%divisor;
	}
	
	private Double calcularDivisao(Double dividendo, Double divisor) {
		return dividendo/divisor;
	}

}
