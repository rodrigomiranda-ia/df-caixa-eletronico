package br.com.danielfarias.caixaeletronico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.danielfarias.caixaeletronico.model.Nota;
import br.com.danielfarias.caixaeletronico.repository.NotaRepository;


@RestController
@RequestMapping("/notas")
public class NotaController {
	
	@Autowired
	private NotaRepository notaRepository;
	
	/*@RequestMapping(method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity<HttpStatus> create(@RequestBody Nota nota) {
		notaRepository.save(nota);
		return ResponseEntity.ok().body(HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<HttpStatus> update(@RequestBody Nota nota) {
		notaRepository.save(nota);
		return ResponseEntity.ok().body(HttpStatus.OK);
	}
	
	@RequestMapping(value="/sacar",method = RequestMethod.GET)
	public List<Nota> sacar(@Param("valor") Double valor) {
		List<Pessoa> pessoas = this.pessoaRepository.findAll();
		return pessoas;
	}*/
	
	@RequestMapping(value="/listar",method = RequestMethod.GET)
	public List<Nota> listarNotas() {
		List<Nota> notas = notaRepository.findAll();
		return notas;
	}

}
