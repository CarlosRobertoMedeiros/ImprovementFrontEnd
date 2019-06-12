package br.com.roberto.gerenciadorfinanceiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.roberto.gerenciadorfinanceiro.model.Lancamento;
import br.com.roberto.gerenciadorfinanceiro.repository.lancamento.LancamentoRepositoryQuery;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery{

}
