package com.academia.bankspringcrudjpah2.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "cliente")
public class Cliente extends AbstractEntity {
	

	@OneToMany(mappedBy="cliente", cascade = CascadeType.ALL)
    List<LivroCaixa> livrosCaixa = new ArrayList<>();
	
	//data_cadastro timestamp not null,
	@Column(name = "data_cadastro", nullable = false)
	@CreationTimestamp
	private Date dataCadastro;
	
	//nome varchar(30) not null,
	@Column(name = "nome", nullable = false)
	//varchar(30)
	private String nome;
    
	//cpf_cnpj varchar(14) not null,
	@Column(name = "cpfcnpj", nullable = false)
	private String cpfcnpj;
	
    //plogradouro varchar(50) not null,
	@Column(name = "logradouro", nullable = false)
	private String logradouro;
	
	//cidade varchar(40) not null,
	@Column(name = "cidade", nullable = false)
	private String cidade;
	
	//uf char(2) not null,
	@Column(name = "uf", nullable = false)
	private String uf;
	
	//cep char(8) not null,
	@Column(name = "cep", nullable = false)
	private String cep;
	
    //telefone varchar(11),
	@Column(name = "telefone")
	private String telefone;
	
    //email varchar(100),
	@Column(name = "email")
	private String email;
	
	
	public Cliente() {
		super();
	}

	public Cliente(Date dataCadastro, String nome, String cpfcnpj, String logradouro, String cidade, String uf,
			String cep, String telefone, String email) {
		super();
		this.dataCadastro = dataCadastro;
		this.nome = nome;
		this.cpfcnpj = cpfcnpj;
		this.logradouro = logradouro;
		this.cidade = cidade;
		this.uf = uf;
		this.cep = cep;
		this.telefone = telefone;
		this.email = email;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpfcnpj() {
		return cpfcnpj;
	}

	public void setCpfcnpj(String cpf_cnpj) {
		this.cpfcnpj = cpf_cnpj;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
   
	

}
