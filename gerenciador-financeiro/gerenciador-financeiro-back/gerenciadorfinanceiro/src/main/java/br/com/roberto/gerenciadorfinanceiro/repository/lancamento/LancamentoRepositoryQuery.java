package br.com.roberto.gerenciadorfinanceiro.repository.lancamento;

import java.util.List;

import br.com.roberto.gerenciadorfinanceiro.model.Lancamento;
import br.com.roberto.gerenciadorfinanceiro.repository.filter.LancamentoFilter;

public interface LancamentoRepositoryQuery{
	
	public List<Lancamento> filtrar(LancamentoFilter lancamentoFilter);
}
