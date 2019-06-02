package br.com.roberto.gerenciadorfinanceiro.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.roberto.gerenciadorfinanceiro.event.RecursoCriadoEvent;
import br.com.roberto.gerenciadorfinanceiro.model.Lancamento;
import br.com.roberto.gerenciadorfinanceiro.repository.LancamentoRepository;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

	@Autowired
	LancamentoRepository lancamentoRepository;
	
	@Autowired
	ApplicationEventPublisher publisher;

	@GetMapping
	public ResponseEntity<List<Lancamento>> listarTodos() {
		List<Lancamento> lancamentos = lancamentoRepository.findAll();
		return !lancamentos.isEmpty() ? ResponseEntity.ok().body(lancamentos) : ResponseEntity.noContent().build();
	}

	@GetMapping("{id}")
	public ResponseEntity<Optional<Lancamento>> listarPorCodigo(@PathVariable Long id) {
		Optional<Lancamento> lancamentosCapturado = lancamentoRepository.findById(id);
		return lancamentosCapturado.isPresent() ? ResponseEntity.ok(lancamentosCapturado)
				: ResponseEntity.noContent().build();
	}
	
	@PostMapping
	public ResponseEntity<Lancamento> criar(@Valid @RequestBody Lancamento lancamento , HttpServletResponse response) {
		Lancamento lancamentoSalvo = lancamentoRepository.save(lancamento);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoSalvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalvo);
	}
	
	

}
