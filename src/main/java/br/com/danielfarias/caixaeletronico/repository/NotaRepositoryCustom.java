package br.com.danielfarias.caixaeletronico.repository;
import java.util.List;

import br.com.danielfarias.caixaeletronico.model.Nota;

public interface NotaRepositoryCustom {
	
	String sacar(Double valor);
	
	List<Nota> listarNotasDisponiveis();

}
