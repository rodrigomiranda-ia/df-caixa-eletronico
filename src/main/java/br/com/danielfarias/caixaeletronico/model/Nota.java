package br.com.danielfarias.caixaeletronico.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.danielfarias.caixaeletronico.enums.ValorNota;

@Entity
@Table(name = "nota")
public class Nota {
	
	@Id
	@GeneratedValue
	(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "valor_nota", unique = true)
	@Enumerated(EnumType.STRING)
	private ValorNota valorNota;
	
	@Column(name = "quantidade_notas")
	private Integer quantidadeNotas;

	public Nota(ValorNota valorNota, Integer quantidadeNotas) {
		super();
		this.valorNota = valorNota;
		this.quantidadeNotas = quantidadeNotas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ValorNota getValorNota() {
		return valorNota;
	}

	public void setValorNota(ValorNota valorNota) {
		this.valorNota = valorNota;
	}

	public Integer getQuantidadeNotas() {
		return quantidadeNotas;
	}

	public void setQuantidadeNotas(Integer quantidadeNotas) {
		this.quantidadeNotas = quantidadeNotas;
	}
	
}
