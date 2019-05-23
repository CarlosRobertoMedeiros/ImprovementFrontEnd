package br.com.carlosroberto.studentcrud.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.carlosroberto.studentcrud.model.Aluno;
import br.com.carlosroberto.studentcrud.repository.AlunoRepository;

@RestController
public class AlunoResource {

	@Autowired
	private AlunoRepository alunoRepository;

	@GetMapping("/alunos")
	public List<Aluno> listarAlunos() {
		return alunoRepository.findAll();
	}

	@GetMapping("/alunos/{id}")
	public Aluno retornarAluno(@PathVariable Long id) throws Exception {
		Optional<Aluno> alunoSalvo = alunoRepository.findById(id);

		if (!alunoSalvo.isPresent()) {
			throw new RuntimeException("id-" + id);
		}
		return alunoSalvo.get();
	}

	@DeleteMapping("/alunos/{id}")
	public void excluiAluno(@PathVariable Long id) {
		alunoRepository.deleteById(id);
	}

	@PostMapping("/alunos")
	public ResponseEntity<Object> criaAluno(@RequestBody Aluno aluno) {
		Aluno alunoSalvo = alunoRepository.save(aluno);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(alunoSalvo.getId())
				.toUri();

		return ResponseEntity.created(location).build();

	}

	@PutMapping("/alunos/{id}")
	public ResponseEntity<Object> atualizaAluno(@RequestBody Aluno aluno, @PathVariable Long id) {
		Optional<Aluno> alunoSalvo = alunoRepository.findById(id);

		if (!alunoSalvo.isPresent())
			return ResponseEntity.notFound().build();

		aluno.setId(id);

		alunoRepository.save(aluno);

		return ResponseEntity.noContent().build();
	}

}
