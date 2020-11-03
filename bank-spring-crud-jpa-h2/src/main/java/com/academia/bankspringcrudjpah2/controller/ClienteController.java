package com.academia.bankspringcrudjpah2.controller;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.academia.bankspringcrudjpah2.exception.FieldErrorMessage;
import com.academia.bankspringcrudjpah2.model.Cliente;
import com.academia.bankspringcrudjpah2.model.LivroCaixa;
import com.academia.bankspringcrudjpah2.model.LivroCaixaSimplificado;
import com.academia.bankspringcrudjpah2.model.ModeloContabil;
import com.academia.bankspringcrudjpah2.repository.ClienteRepository;
import com.academia.bankspringcrudjpah2.repository.LivroCaixaRepository;

@RestController
public class ClienteController {
	
	@Autowired
    ClienteRepository clienteRepository;
	
	@Autowired
	LivroCaixaRepository livroCaixaRepository;

    @GetMapping("/cliente")
    Iterable<Cliente> read(){
        return clienteRepository.findAll();
    }

    /*
     * CREATE
     */
    @PostMapping("/cliente")
    Cliente create(@Valid @RequestBody Cliente cliente) throws ValidationException {
    	
            return clienteRepository.save(cliente);
       
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
    @PutMapping("/cliente")
    ResponseEntity<Cliente> update(@RequestBody Cliente cliente){
        if(clienteRepository.findById((cliente.getId())).isPresent()){
            return new ResponseEntity(clienteRepository.save(cliente),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity(cliente, HttpStatus.BAD_REQUEST);
        }

    }

    /*
     * DELETE
     */
    @DeleteMapping("/cliente/{id}")
    void delete(@PathVariable Long id){
        clienteRepository.deleteById(id);
    }

    @GetMapping("/cliente/{id}")
    Optional<Cliente> findById(@PathVariable Long id){
        return clienteRepository.findById(id);
    }

    /*
     * FIND By nome e/ou cpfcnpj e/ou cidade e/ou uf
     */
    @GetMapping("/cliente/busca")
    Iterable<Cliente> findByQuery(@RequestParam(value = "nome", required = false) String nome,
                                 @RequestParam(value = "cpfcnpj", required = false) String cpfcnpj,
                                 @RequestParam(value = "cidade", required = false) String cidade,
                                 @RequestParam(value = "uf", required = false) String uf){
    	
    	if (nome !=null) {
    		if (cpfcnpj != null) {
    			if (cidade != null) {
    				if (uf != null) {
    					return clienteRepository
    							.findByNomeAndCpfcnpjAndCidadeAndUf(nome, cpfcnpj, cidade, uf);
    				} else {
    					return clienteRepository.findByNomeAndCpfcnpjAndCidade(nome, cpfcnpj, cidade);
    				}
    			}//cidade ==null
    			else {
    				if (uf != null) {
    					return clienteRepository
    							.findByNomeAndCpfcnpjAndUf(nome, cpfcnpj, uf);
    				} else {
    					return clienteRepository.findByNomeAndCpfcnpj(nome, cpfcnpj);
    				}
    			}//cidade
    		}
    		else {//cpfcnpj == null
    			if (cidade != null) {
    				if (uf != null) {
    					return clienteRepository.findByNomeAndCidadeAndUf(nome, cidade, uf);
    				} else {
    					return clienteRepository.findByNomeAndCidade(nome, cidade);
    				}
    			}//cidade ==null
    			else {
    				if (uf != null) {
    					return clienteRepository
    							.findByNomeAndUf(nome, uf);
    				} else {
    					return clienteRepository.findByNome(nome);
    				}
    			}//cidade
    		}
    	}
    	else {//nome == null
    		
    		if (cpfcnpj != null) {
    			if (cidade != null) {
    				if (uf != null) {
    					return clienteRepository
    							.findByCpfcnpjAndCidadeAndUf(cpfcnpj, cidade, uf);
    				} else {
    					return clienteRepository.findByCpfcnpjAndCidade(cpfcnpj, cidade);
    				}
    			}//cidade ==null
    			else {
    				if (uf != null) {
    					return clienteRepository
    							.findByCpfcnpjAndUf(cpfcnpj, uf);
    				} else {
    					return clienteRepository.findByCpfcnpj(cpfcnpj);
    				}
    			}//cidade
    		}
    		else {//cpfcnpj == null
    			if (cidade != null) {
    				if (uf != null) {
    					return clienteRepository.findByCidadeAndUf(cidade, uf);
    				} else {
    					return clienteRepository.findByCidade(cidade);
    				}
    			}//cidade ==null
    			else {
    				if (uf != null) {
    					return clienteRepository
    							.findByUf(uf);
    				} else {
    					return clienteRepository.findAll();
    				}
    			}//cidade
    		}
    	}
    }

    
    @RequestMapping(
			value = "/cliente/contabil/{clienteid}",
			method = RequestMethod.GET,
			produces = "application/json")
	ResponseEntity<?> getModeloContabil(@PathVariable Long clienteid) {
		ModeloContabil modeloContabil = new ModeloContabil();
		LivroCaixaSimplificado livroCaixaSimplificado;
		
		if (clienteRepository.findById(clienteid).isPresent()) {
			Cliente cliente = new Cliente();
			cliente = clienteRepository.findById(clienteid).get();
			modeloContabil.setId(clienteid);
			modeloContabil.setNome(cliente.getNome());
			modeloContabil.setCPF_CNPJ(cliente.getCpfcnpj());
			modeloContabil.setTelefone(cliente.getTelefone());
			
			double saldoTemp = 0.0;
			
			Iterable<LivroCaixa> livrosCaixa = livroCaixaRepository
					.findByCliente(cliente);
			
			final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			int limit = livroCaixaRepository.countByCliente(cliente);
			int count = 0;
			while (livrosCaixa.iterator().hasNext()) {
				
				livroCaixaSimplificado = new LivroCaixaSimplificado();
				LivroCaixa livroCaixa = livrosCaixa.iterator().next();
				
				double valorTemp = livroCaixa.getValor();
				char tipoTemp = livroCaixa.getTipo();
				
				livroCaixaSimplificado.setDataLancamento(sdf.format(livroCaixa.getDatalancamento()));
				livroCaixaSimplificado.setDescricao(livroCaixa.getDescricao());
				livroCaixaSimplificado.setTipo(tipoTemp);
				livroCaixaSimplificado.setValor(valorTemp);
				
				if (tipoTemp == 'C') {
					saldoTemp += valorTemp;
				} else {
					saldoTemp -= valorTemp;
				}
				
				livroCaixaSimplificado.setSaldo(saldoTemp);
				
				modeloContabil.getContabil().add(livroCaixaSimplificado);
				
				count +=1;
				if (count == limit) break;
				
			}
			
			
			return new ResponseEntity(modeloContabil, HttpStatus.OK);
		}
		else {
			return new ResponseEntity("id de cliente nao encontrado", HttpStatus.BAD_REQUEST);
		}
	}

}

