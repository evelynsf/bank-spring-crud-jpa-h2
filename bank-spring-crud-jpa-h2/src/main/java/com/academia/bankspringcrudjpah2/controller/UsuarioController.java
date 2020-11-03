package com.academia.bankspringcrudjpah2.controller;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.academia.bankspringcrudjpah2.exception.FieldErrorMessage;
import com.academia.bankspringcrudjpah2.model.Usuario;
import com.academia.bankspringcrudjpah2.repository.UsuarioRepository;



@RestController
public class UsuarioController {
	
	@Autowired
    UsuarioRepository usuarioRepository;
	
	

    @GetMapping("/usuario")
    Iterable<Usuario> read(){
        return usuarioRepository.findAll();
    }

    /*
     * CREATE
     */
    @PostMapping("/usuario")
    Usuario create(@Valid @RequestBody Usuario usuario) throws ValidationException {
    	
            return usuarioRepository.save(usuario);
       
    }
    
    Optional<Usuario> findByUsername(@PathVariable String username){
        return usuarioRepository.findByUsername(username);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    List<FieldErrorMessage> exceptionHandler(MethodArgumentNotValidException e){
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<FieldErrorMessage> fieldErrorMessages = fieldErrors.stream().map(fieldError -> 
        new FieldErrorMessage(fieldError.getField(),
                fieldError.getDefaultMessage())).collect(Collectors.toList());

        return fieldErrorMessages;
    }

    /*
     * UPDATE
     */
    @PutMapping("/usuario")
    ResponseEntity<Usuario> update(@RequestBody Usuario usuario){
        if(usuarioRepository.findById((usuario.getId())).isPresent()){
            return new ResponseEntity(usuarioRepository.save(usuario),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity(usuario, HttpStatus.BAD_REQUEST);
        }

    }

    /*
     * DELETE
     */
    @DeleteMapping("/usuario/{id}")
    void delete(@PathVariable Long id){
        usuarioRepository.deleteById(id);
    }

    @GetMapping("/usuario/{id}")
    Optional<Usuario> findById(@PathVariable Long id){
        return usuarioRepository.findById(id);
    }

    /*
     * FIND By NOME and/or Email
     */
    @GetMapping("/usuario/busca")
    Iterable<Usuario> findByQuery(@RequestParam(value = "nome", required = false) String nome,
                                 @RequestParam(value = "email", required = false) String email){
        if(nome != null && email != null) {
            return usuarioRepository.findByNomeAndEmail(nome, email);
        } else if (nome !=null){
            return usuarioRepository.findByNome(nome);
        } else if (email != null){
            return usuarioRepository.findByEmail(email);
        } else{
            return usuarioRepository.findAll();
        }
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    ResponseEntity<String> login(@RequestParam(value = "user", required = true) 
    String username, @RequestParam(value = "pwd", required = true) 
    String password) {
    	
    	if(usuarioRepository.findByUsername(username).isPresent()) {
    		
    		Usuario usuario = usuarioRepository
    				.findByUsername(username).get();
    		
    		if(usuario.getPassword().equalsIgnoreCase(password)) {
    			//verifica stattus
    			if (usuario.getStatus() == 'C') {
    				return new ResponseEntity("Usuario cancelado.",
            				HttpStatus.BAD_REQUEST);
    			}
    			else {
    				return new ResponseEntity("Login efetuado com sucesso.",
            				HttpStatus.OK);
    			}
    		}
    		else {
    			return new ResponseEntity("Senha invalida.",
        				HttpStatus.BAD_REQUEST);
    		}
    	}
    	else {
    		return new ResponseEntity("Username nao encontrado.",
    				HttpStatus.BAD_REQUEST);
    	}
        

       
    }
    
    


}
