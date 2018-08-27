package br.com.danielfarias.caixaeletronico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.danielfarias.caixaeletronico.model.Nota;

@Repository
public interface NotaRepository extends JpaRepository<Nota,Long>{
	
	@Query("SELECT SUM(n.valorNota * n.quantidadeNotas) from Nota n")
	Double obterEstoqueTotal();
	
	Nota findByValorNota(Double valor);
}
