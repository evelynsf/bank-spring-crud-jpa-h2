package com.academia.bankspringcrudjpah2.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "livro_caixa")
public class LivroCaixa extends AbstractEntity{
	 

    //    foreign key (clienteid) references clientes(id)
	@ManyToOne
	@JoinColumn(name = "clienteid", nullable = false, updatable = false)
	private Cliente cliente;
	
    //  data_lancamento timestamp not null,
	@Column(name = "data_lancamento", nullable = false)
	@CreationTimestamp
	private Date datalancamento;
	
    //  descricao varchar(50) not null,
	@Column(name = "descricao", nullable = false)
	//varchar(30)
	private String descricao;
    
    //  tipo char(1) not null,
	@Column(name = "tipo", nullable = false)
	private char tipo;
	
    //  valor decimal(12,2) not null, 
	@Column(name = "valor", nullable = false)
	private Double valor;
	
	

	public LivroCaixa() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LivroCaixa(Cliente cliente, Date datalancamento, String descricao, char tipo, Double valor) {
		super();
		this.cliente = cliente;
		this.datalancamento = datalancamento;
		this.descricao = descricao;
		this.tipo = tipo;
		this.valor = valor;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getDatalancamento() {
		return datalancamento;
	}

	public void setDatalancamento(Date datalancamento) {
		this.datalancamento = datalancamento;
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
	
	
  
}
