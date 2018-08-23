package br.com.danielfarias.caixaeletronico.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.danielfarias.caixaeletronico.model.Nota;

@Repository
public interface NotaRepository extends JpaRepository<Nota,Long>{
	
	List<Nota> findAllOrderByValorNotaDesc();
}
