package com.roberto.listatelefonica.api.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.roberto.listatelefonica.api.model.OperadoraTelefoneCategoria;
import com.roberto.listatelefonica.api.repository.OperadoraTelefoneCategoriaRepository;

@RestController
@RequestMapping("/OperadoraTelefoneCategoria")
public class OperadoraTelefoneCategoriaResource {

	@Autowired
	private OperadoraTelefoneCategoriaRepository operadoraTelefoneCategoriaRepository;
	
	@GetMapping
	public List<OperadoraTelefoneCategoria> listar(){
		return (List<OperadoraTelefoneCategoria>) operadoraTelefoneCategoriaRepository.findAll();
		
	}
	
	//O @RequestBody serve para informar que um objeto do tipo OperadoraTelefoneCategoria está sendo 
	//chamado através de uma requisição ao serviço e com isso ele associa automaticamente o body chamado do serviço
	//ao objeto operadoraTelefoneCategoria em questão.
	@PostMapping
	public ResponseEntity<OperadoraTelefoneCategoria> criar(@RequestBody OperadoraTelefoneCategoria operadoraTelefoneCategoria, HttpServletResponse response) {
		OperadoraTelefoneCategoria operadoraTelefoneCategoriaSalva =  operadoraTelefoneCategoriaRepository.save(operadoraTelefoneCategoria);
		
		//Segundo o Documento que informa a melhor maneira de criar um micro serviço pelo Martin Fowler
		// Essa função serve para incluir no Header o atributo Location com o valor criado do objeto
		//nesse exemplo vou mostrar no location o caminho para confirmar a criação do arquivo 
		URI uri =  ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
					.buildAndExpand(operadoraTelefoneCategoriaSalva.getCodigo()).toUri();
		
		response.setHeader("Location", uri.toASCIIString());
		
		//O retorno do ResponseEntity created já vai ser o 201
		return ResponseEntity.created(uri).body(operadoraTelefoneCategoriaSalva);
	
	}
	
	//Associação do código da requisição ao código do getmapping
	@GetMapping("/{codigo}")
	public Optional<OperadoraTelefoneCategoria> buscarPeloCodigo(@PathVariable Long codigo) {
		return operadoraTelefoneCategoriaRepository.findById(codigo);
	}
	
}

//Continuar a aula no inicio do video 3.7
//Desafio: Retornar 404 Caso Não exista a categoria
//Fiz a mudança do jpaRepository para o crudrepository



