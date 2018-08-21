package br.com.danielfarias.caixaeletronico.enums;

public enum ValorNota {
	DOIS(2.00),CINCO(5.00),DEZ(10.00),VINTE(20.00),CINQUENTA(50.00);
	
	private Double valor;
	
	ValorNota(Double valor) {
		this.valor = valor;
	}

	public Double getValor() {
		return valor;
	}
	
}
