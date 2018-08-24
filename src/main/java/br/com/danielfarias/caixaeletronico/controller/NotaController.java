package br.com.danielfarias.caixaeletronico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.danielfarias.caixaeletronico.model.Nota;
import br.com.danielfarias.caixaeletronico.repository.NotaRepositoryCustom;


@RestController
@RequestMapping("/notas")
public class NotaController {
	
	@Autowired
	private NotaRepositoryCustom notaRepository;
	
	/*@RequestMapping(method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity<HttpStatus> create(@RequestBody Nota nota) {
		notaRepository.save(nota);
		return ResponseEntity.ok().body(HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<HttpStatus> update(@RequestBody Nota nota) {
		notaRepository.save(nota);
		return ResponseEntity.ok().body(HttpStatus.OK);
	}*/
	
	@RequestMapping(value="/sacar",method = RequestMethod.GET)
	public List<Nota> sacar(@Param("valor") Double valor) {
		return notaRepository.sacar(valor);
	}
	
	@RequestMapping(value="/listar",method = RequestMethod.GET)
	public List<Nota> listarNotas() {
		List<Nota> notas = notaRepository.listarNotasDisponiveis();
		return notas;
	}

}
