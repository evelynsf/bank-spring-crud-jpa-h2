package com.academia.bankspringcrudjpah2.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.academia.bankspringcrudjpah2.repository.ClienteRepository;
import com.academia.bankspringcrudjpah2.repository.LivroCaixaRepository;

@Component
public class ModeloContabil {
	
	private Long id;
	private String nome;
	private String CPF_CNPJ;
	private String telefone;
	private List<?> contabil;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCPF_CNPJ() {
		return CPF_CNPJ;
	}
	public void setCPF_CNPJ(String cPF_CNPJ) {
		CPF_CNPJ = cPF_CNPJ;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public List<LivroCaixaSimplificado> getContabil() {
		return (List<LivroCaixaSimplificado>) contabil;
	}
	public void setContabil(List<?> contabil) {
		this.contabil = contabil;
	}
	public ModeloContabil(Long id, String nome, String cPF_CNPJ, String telefone, List<?> contabil) {
		super();
		this.id = id;
		this.nome = nome;
		this.CPF_CNPJ = cPF_CNPJ;
		this.telefone = telefone;
		this.contabil = contabil;
	}
	public ModeloContabil() {
		super();
		this.contabil = new ArrayList<>();
	}
	

}
