package br.com.roberto.gerenciadorfinanceiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.roberto.gerenciadorfinanceiro.model.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

}
