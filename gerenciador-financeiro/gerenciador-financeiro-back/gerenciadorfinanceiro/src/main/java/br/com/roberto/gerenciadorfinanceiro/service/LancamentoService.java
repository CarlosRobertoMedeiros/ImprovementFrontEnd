package br.com.roberto.gerenciadorfinanceiro.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.roberto.gerenciadorfinanceiro.model.Lancamento;
import br.com.roberto.gerenciadorfinanceiro.model.Pessoa;
import br.com.roberto.gerenciadorfinanceiro.repository.LancamentoRepository;
import br.com.roberto.gerenciadorfinanceiro.repository.PessoaRepository;
import br.com.roberto.gerenciadorfinanceiro.service.exception.PessoaInexistenteOuInativaException;

@Service
public class LancamentoService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	
	public Lancamento salvar(@Valid Lancamento lancamento) {
		Pessoa pessoa = pessoaRepository.findById(lancamento.getPessoa().getCodigo()).get();
		
		if ((pessoa==null) || !pessoa.isAtivo()){
			throw new PessoaInexistenteOuInativaException();
		}
		
		return lancamentoRepository.save(lancamento);
	}

}
