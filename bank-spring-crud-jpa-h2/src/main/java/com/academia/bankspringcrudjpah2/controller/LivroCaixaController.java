package com.academia.bankspringcrudjpah2.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.academia.bankspringcrudjpah2.exception.FieldErrorMessage;
import com.academia.bankspringcrudjpah2.model.Cliente;
import com.academia.bankspringcrudjpah2.model.LivroCaixa;
import com.academia.bankspringcrudjpah2.model.Usuario;
import com.academia.bankspringcrudjpah2.repository.ClienteRepository;
import com.academia.bankspringcrudjpah2.repository.LivroCaixaRepository;
import com.academia.bankspringcrudjpah2.repository.UsuarioRepository;

@RestController
public class LivroCaixaController {
	
	@Autowired
    LivroCaixaRepository livroCaixaRepository;
	
	@Autowired
	ClienteRepository clienteRepository;

    @GetMapping("/livro-caixa")
    Iterable<LivroCaixa> read(){
        return livroCaixaRepository.findAll();
    }

    /*
     * CREATE
     */
    @PostMapping("/livro-caixa")
//    LivroCaixa create(@Valid @RequestBody LivroCaixa livroCaixa) throws ValidationException {
//            return livroCaixaRepository.save(livroCaixa);
//       
//    }
    ResponseEntity<?> create(@RequestBody @Valid LivroCaixaEntry entry){
    	final Optional<Cliente> cliente = clienteRepository.findById(entry.getClienteid());
    	final LivroCaixa novo = new LivroCaixa();
    	novo.setCliente(cliente.get());
    	novo.setDescricao(entry.getDescricao());
    	novo.setTipo(entry.getTipo());
    	novo.setValor(entry.getValor());
    	return new ResponseEntity(livroCaixaRepository.save(novo),
                HttpStatus.OK);
    	
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
    @PutMapping("/livro-caixa")
    ResponseEntity<LivroCaixa> update(@RequestBody LivroCaixaUpdate toUpdate){
    	final Long updateId = toUpdate.getid();
    	final LivroCaixa livroCaixa = livroCaixaRepository.findById((updateId)).get();
        if(livroCaixa != null){
        	
        	livroCaixa.setDescricao(toUpdate.getDescricao());
        	livroCaixa.setTipo(toUpdate.getTipo());
        	livroCaixa.setValor(toUpdate.getValor());
            return new ResponseEntity(livroCaixaRepository.save(livroCaixa),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity(livroCaixa, HttpStatus.BAD_REQUEST);
        }

    }

    /*
     * DELETE
     */
    @DeleteMapping("/livro-caixa/{id}")
    void delete(@PathVariable Long id){
    	livroCaixaRepository.deleteById(id);
    }

    @GetMapping("/livro-caixa/{id}")
    Optional<LivroCaixa> findById(@PathVariable Long id){
        return livroCaixaRepository.findById(id);
    }

    /*
     * FIND By NOME and/or Email
     */
    @GetMapping("/livro-caixa/buscaPorCliente/{clienteid}")
    Iterable<LivroCaixa> findByQuery(@PathVariable Long clienteid){
        return livroCaixaRepository.findByCliente(clienteRepository.findById(clienteid).get());
    }
    

    
    //Classe entrada de livro-caixa
    static class LivroCaixaEntry {
        @NotNull(message = "id do cliente requerido")
        private Long clienteid;
        @NotNull
        @Size(message = "Descricao de 1 a 50 caracteres", min = 1, max = 50)
        private String descricao;
        @NotNull(message = "tipo requerido")
        private char tipo;
        @Min(0)
        private Double valor;
		public Long getClienteid() {
			return clienteid;
		}
		public void setClienteid(Long clienteid) {
			this.clienteid = clienteid;
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
    
    static class LivroCaixaUpdate {
    	
    	@NotNull(message = "id do livro caixa requerido")
        private Long id;
        @NotNull
        @Size(message = "Descricao de 1 a 50 caracteres", min = 1, max = 50)
        private String descricao;
        @NotNull(message = "tipo requerido")
        private char tipo;
        @Min(0)
        private Double valor;
		public Long getid() {
			return id;
		}
		public void setid(Long id) {
			this.id = id;
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

}
