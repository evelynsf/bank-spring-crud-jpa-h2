package com.academia.bankspringcrudjpah2.repository;

import org.springframework.data.repository.CrudRepository;

import com.academia.bankspringcrudjpah2.model.Cliente;
import com.academia.bankspringcrudjpah2.model.LivroCaixa;
import com.academia.bankspringcrudjpah2.model.Usuario;

public interface LivroCaixaRepository extends CrudRepository<LivroCaixa, Long>{
	
	Iterable<LivroCaixa> findByCliente(Cliente cliente);
	
	int countByCliente(Cliente cliente);

}
