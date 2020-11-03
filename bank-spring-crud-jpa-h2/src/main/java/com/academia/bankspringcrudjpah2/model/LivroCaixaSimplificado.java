package com.academia.bankspringcrudjpah2.model;

public class LivroCaixaSimplificado{
	 private String dataLancamento;
	 private String descricao;
	 private char tipo;
	 private Double valor;
	 private Double saldo;
	 
	public String getDataLancamento() {
		return dataLancamento;
	}
	public void setDataLancamento(String dataLancamento) {
		this.dataLancamento = dataLancamento;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public char getTipo() {
		return tipo;
	}
	public void setTipo(char tipo) {
		this.tipo = tipo;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public Double getSaldo() {
		return saldo;
	}
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	public LivroCaixaSimplificado() {
		super();
	}
	 
	
}
