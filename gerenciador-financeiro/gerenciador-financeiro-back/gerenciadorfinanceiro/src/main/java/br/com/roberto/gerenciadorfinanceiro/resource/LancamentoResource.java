package br.com.roberto.gerenciadorfinanceiro.resource;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.roberto.gerenciadorfinanceiro.event.RecursoCriadoEvent;
import br.com.roberto.gerenciadorfinanceiro.exceptionhandler.GerenciadorFinanceiroResponseEntityExceptionHandler.Erro;
import br.com.roberto.gerenciadorfinanceiro.model.Lancamento;
import br.com.roberto.gerenciadorfinanceiro.repository.LancamentoRepository;
import br.com.roberto.gerenciadorfinanceiro.repository.filter.LancamentoFilter;
import br.com.roberto.gerenciadorfinanceiro.service.LancamentoService;
import br.com.roberto.gerenciadorfinanceiro.service.exception.PessoaInexistenteOuInativaException;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

	@Autowired
	LancamentoRepository lancamentoRepository;
	
	@Autowired
	private LancamentoService lancamentoService;
	
	@Autowired
	ApplicationEventPublisher publisher;
	
	@Autowired
	MessageSource messageSource;

	@GetMapping
	public List<Lancamento> pesquisar(LancamentoFilter lancamentoFilter) {
		return lancamentoRepository.filtrar(lancamentoFilter);
	}

	@GetMapping("{id}")
	public ResponseEntity<Optional<Lancamento>> listarPorCodigo(@PathVariable Long id) {
		Optional<Lancamento> lancamentosCapturado = lancamentoRepository.findById(id);
		return lancamentosCapturado.isPresent() ? ResponseEntity.ok(lancamentosCapturado)
				: ResponseEntity.noContent().build();
	}
	
	@PostMapping
	public ResponseEntity<Lancamento> criar(@Valid @RequestBody Lancamento lancamento , HttpServletResponse response) {
		Lancamento lancamentoSalvo = lancamentoService.salvar(lancamento);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoSalvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalvo);
	}
	
	@ExceptionHandler(PessoaInexistenteOuInativaException.class)
	public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(PessoaInexistenteOuInativaException ex){
		String mensagemUsuario = messageSource.getMessage("pessoa.inexistente-ou-inativa", null, LocaleContextHolder.getLocale());
		//String mensagemDesenvolvedor = ex.getCause()!=null? ex.getCause().toString() : ex.toString();
		String mensagemDesenvolvedor = Optional.ofNullable(ex.getCause()).orElse(ex).toString();

		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		return ResponseEntity.badRequest().body(erros);
	}
	
	

}
