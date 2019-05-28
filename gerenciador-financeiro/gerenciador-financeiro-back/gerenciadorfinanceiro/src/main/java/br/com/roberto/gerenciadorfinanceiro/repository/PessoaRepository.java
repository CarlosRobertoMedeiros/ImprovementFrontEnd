package br.com.roberto.gerenciadorfinanceiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.roberto.gerenciadorfinanceiro.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
