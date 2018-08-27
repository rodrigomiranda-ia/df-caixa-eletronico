package br.com.danielfarias.caixaeletronico.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.danielfarias.caixaeletronico.exception.CaixaEletronicoException;
import br.com.danielfarias.caixaeletronico.model.Nota;
import br.com.danielfarias.caixaeletronico.repository.NotaRepositoryCustom;
import br.com.danielfarias.caixaeletronico.util.Constantes;


@RestController
@RequestMapping("/notas")
public class NotaController {
	
	@Autowired
	private NotaRepositoryCustom notaRepository;
	
	@RequestMapping(value="/cadastrar", method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity<Object> create(@RequestBody Nota nota) {
		Nota notaSalva = notaRepository.salvar(nota);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(notaSalva.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
	
	@RequestMapping(value="/sacar",method = RequestMethod.GET)
	public List<Nota> sacar(@Param("valorSaque") Double valorSaque) throws CaixaEletronicoException {
		Double estoqueTotal = notaRepository.obterEstoqueTotal();	
		if(valorSaque <= 0 || valorSaque > 10.000) {
			throw new CaixaEletronicoException(Constantes.MSG_CAIXA_FAIXA_DE_VALORES);
		}
		
		if(estoqueTotal < valorSaque) {
			throw new CaixaEletronicoException(Constantes.MSG_CAIXA_ELETRONICO_SEM_ESTOQUE);
		}	
		return notaRepository.sacar(valorSaque);
	}
	
	@RequestMapping(value="/listar",method = RequestMethod.GET)
	public List<Nota> listarNotas() {
		List<Nota> notas = notaRepository.listarNotasDisponiveis();
		return notas;
	}

}
