package br.com.roberto.gerenciadorfinanceiro.repository.lancamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.roberto.gerenciadorfinanceiro.model.Lancamento;
import br.com.roberto.gerenciadorfinanceiro.repository.filter.LancamentoFilter;
import br.com.roberto.gerenciadorfinanceiro.repository.projection.ResumoLancamento;

public interface LancamentoRepositoryQuery{
	
	public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);
	public Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable);
}
