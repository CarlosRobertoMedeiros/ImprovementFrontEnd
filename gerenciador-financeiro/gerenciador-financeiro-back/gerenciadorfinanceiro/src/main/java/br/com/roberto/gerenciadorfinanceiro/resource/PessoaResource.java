package br.com.roberto.gerenciadorfinanceiro.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.roberto.gerenciadorfinanceiro.model.Pessoa;
import br.com.roberto.gerenciadorfinanceiro.repository.PessoaRepository;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

	@Autowired
	PessoaRepository pessoaRepository;

	@GetMapping
	public ResponseEntity<List<Pessoa>> listar() {

		List<Pessoa> pessoas = pessoaRepository.findAll();

		if (pessoas.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok().body(pessoas);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Optional<Pessoa>> pesquisarPorCodigo(@PathVariable Long id) {
		
		Optional<Pessoa> pessoaSalva = pessoaRepository.findById(id);
		
		if (!pessoaSalva.isPresent()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(pessoaSalva);
	}
	
	@PostMapping
	public ResponseEntity<Pessoa> criar(Pessoa pessoa, ServletResponse response){
		Pessoa pessoaSalva = pessoaRepository.save(pessoa);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
				.buildAndExpand(pessoaSalva.getCodigo()).toUri();
		
		return ResponseEntity.created(uri).body(pessoaSalva);
	}
	
	













}
