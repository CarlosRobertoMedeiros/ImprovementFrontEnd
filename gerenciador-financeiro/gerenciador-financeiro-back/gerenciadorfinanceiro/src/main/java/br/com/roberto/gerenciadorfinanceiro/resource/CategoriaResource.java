package br.com.roberto.gerenciadorfinanceiro.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.roberto.gerenciadorfinanceiro.model.Categoria;
import br.com.roberto.gerenciadorfinanceiro.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
	
	@Autowired
	CategoriaRepository categoriaRepository; 
	
	@GetMapping
	public ResponseEntity<?> listarTodos(){
		 List<Categoria> categorias = categoriaRepository.findAll();
		 return !categorias.isEmpty() ? ResponseEntity.ok(categorias) : ResponseEntity.noContent().build(); 
	} 
	
	@GetMapping("{id}")
	public ResponseEntity<?> listarPorCodigo(@PathVariable Long id){
		Optional<Categoria> categoriaPorId = categoriaRepository.findById(id); 
		return categoriaPorId.isPresent() ? ResponseEntity.ok(categoriaPorId) : ResponseEntity.noContent().build() ;
	}
	
	@PostMapping
	public ResponseEntity<Categoria> criar(@Valid @RequestBody Categoria categoria, HttpServletResponse response) {
		Categoria categoriaSalva = categoriaRepository.save(categoria);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
			.buildAndExpand(categoriaSalva.getCodigo()).toUri();
		
		return ResponseEntity.created(uri).body(categoriaSalva);
	}
}
