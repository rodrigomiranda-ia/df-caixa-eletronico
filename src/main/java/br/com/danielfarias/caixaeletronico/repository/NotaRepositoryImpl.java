package br.com.danielfarias.caixaeletronico.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.danielfarias.caixaeletronico.model.Nota;

public class NotaRepositoryImpl implements NotaRepositoryCustom{
	
	@Autowired
	private NotaRepository notaRepository;
	
	@Override
	public String sacar(Double valor){
		System.out.println(calcularQuantidadeNotas(valor, ""));
		return calcularQuantidadeNotas(valor, "");
	}

	@Override
	public List<Nota> listarNotasDisponiveis() {
		return notaRepository.findAll();
	}
	
	private Integer calcularQuantidadeNotas(Double valor){
		
	}
	
	private Double calcularResto(Double dividendo, Double divisor) {
		return dividendo%divisor;
	}
	
	private Double calcularDivisao(Double dividendo, Double divisor) {
		return dividendo/divisor;
	}

}
