package br.com.carlosroberto.studentcrud.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.carlosroberto.studentcrud.event.RecursoCriadoEvent;
import br.com.carlosroberto.studentcrud.model.Aluno;
import br.com.carlosroberto.studentcrud.repository.AlunoRepository;

@RestController
@RequestMapping("/alunos")
public class AlunoResource {

	@Autowired
	private AlunoRepository alunoRepository;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public ResponseEntity<?> listarTodos() {
		List<Aluno> alunos = alunoRepository.findAll();
		return !alunos.isEmpty() ? ResponseEntity.ok(alunos) : ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Optional<Aluno>> retornarPeloId(@PathVariable Long id) {
		Optional<Aluno> alunoSalvo = alunoRepository.findById(id);

		if (!alunoSalvo.isPresent()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok().body(alunoSalvo);
	}

	@PostMapping
	public ResponseEntity<Aluno> criar(@Valid @RequestBody Aluno aluno, HttpServletResponse response) {

		Aluno alunoSalvo = alunoRepository.save(aluno);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, alunoSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(alunoSalvo);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> atualizar(@RequestBody Aluno aluno, @PathVariable Long id) {
		Optional<Aluno> alunoSalvo = alunoRepository.findById(id);

		if (!alunoSalvo.isPresent())
			return ResponseEntity.notFound().build();

		aluno.setId(id);

		alunoRepository.save(aluno);

		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public void excluirPeloCodigo(@PathVariable Long id) {
		alunoRepository.deleteById(id);
	}

}
