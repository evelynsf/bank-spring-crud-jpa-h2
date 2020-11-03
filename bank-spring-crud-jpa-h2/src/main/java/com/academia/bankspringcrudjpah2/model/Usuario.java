package com.academia.bankspringcrudjpah2.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "usuario")
public class Usuario extends AbstractEntity{
	
	@Column(name = "data_cadastro", nullable = false)
	@CreationTimestamp
	//@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date dataCadastro;
	
	@Column(name = "nome", nullable = false)
	//varchar(30)
	private String nome;
    
    //login varchar(15) not null,
	//unique key username (login)
	@Column(name = "username", nullable = false, unique = true)
	@Size(message = "min = 1 e max = 15 caracteres", max = 15, min = 1)
	private String username;
	
    //password varchar(10) not null,
	@Size(message = "min = 1 e max = 10 caracteres", max = 10, min = 1)
	@Column(name = "password", nullable = false)
	private String password;
	
    //telefone varchar(11),
	@Column(name = "telefone")
	private String telefone;
	
    //email varchar(100),
	@Column(name = "email")
	@Email(message = "Email deve estar em um formato valido")
	private String email;
	
    //perfil char(1) not null,
	//A - administrador | O - operador
	@Column(name = "perfil", nullable = false)
	private char perfil;
	
    //status char(1) not null,
	// A - ativo | C - cancelado
	@Column(name = "status", nullable = false)
	private char status;
    //primary key (id),
	
	
	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Usuario(Date dataCadastro, String nome, String username, String password, String telefone, String email,
			char perfil, char status) {
		super();
		//dataCadastro = new Timestamp(System.currentTimeMillis());
		this.dataCadastro = dataCadastro;
		this.nome = nome;
		this.username= username;
		this.password = password;
		this.telefone = telefone;
		this.email = email;
		this.perfil = perfil;
		this.status = status;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public char getPerfil() {
		return perfil;
	}

	public void setPerfil(char perfil) {
		this.perfil = perfil;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}
	
	
    
}
