package com.academia.bankspringcrudjpah2.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.academia.bankspringcrudjpah2.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long>{
	
	Iterable<Usuario> findByNomeAndEmail(String nome, String email);

    Iterable<Usuario> findByNome(String nome);

    Iterable<Usuario> findByEmail(String email);

	Optional<Usuario> findByUsername(String username);
	
	Optional<Usuario> findByUsernameAndPassword(String username, String password);
	

}
