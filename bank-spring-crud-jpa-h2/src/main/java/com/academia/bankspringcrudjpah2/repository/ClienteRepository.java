package com.academia.bankspringcrudjpah2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.academia.bankspringcrudjpah2.model.Cliente;
import com.academia.bankspringcrudjpah2.model.Usuario;

public interface ClienteRepository extends CrudRepository<Cliente, Long>{
	
	//nome e/ou cpf_cnpj e/ou cidade e/ou uf
	Iterable<Cliente> findByNomeAndCpfcnpjAndCidadeAndUf(String nome, String cpfcnpj,
			String cidade, String uf);
	
	Iterable<Cliente> findByNomeAndCpfcnpjAndCidade(String nome, String cpfcnpj,
			String cidade);
	
	Iterable<Cliente> findByNomeAndCpfcnpjAndUf(String nome, String cpfcnpj,
			String uf);
	
	Iterable<Cliente> findByNomeAndCpfcnpj(String nome, String cpfcnpj);
	
	Iterable<Cliente> findByNomeAndCidadeAndUf(String nome, String cidade, String uf);
	
	Iterable<Cliente> findByNomeAndCidade(String nome, String cidade);
	
	Iterable<Cliente> findByNomeAndUf(String nome, String uf);
	
	Iterable<Cliente> findByCpfcnpjAndCidadeAndUf(String cpfcnpj,
			String cidade, String uf);
	
	Iterable<Cliente> findByCpfcnpjAndCidade(String cpfcnpj,
			String cidade);
	
	Iterable<Cliente> findByCpfcnpjAndUf(String cpfcnpj, String uf);

	Iterable<Cliente> findByCidadeAndUf(String cidade, String uf);
	
    Iterable<Cliente> findByNome(String nome);

    Iterable<Cliente> findByCpfcnpj(String cpfcnpj);
    
    Iterable<Cliente> findByCidade(String cidade);
    
    Iterable<Cliente> findByUf(String uf);

}
