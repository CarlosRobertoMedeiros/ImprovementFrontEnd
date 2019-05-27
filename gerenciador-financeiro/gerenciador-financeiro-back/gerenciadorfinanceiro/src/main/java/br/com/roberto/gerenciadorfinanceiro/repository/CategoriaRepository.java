package br.com.roberto.gerenciadorfinanceiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.roberto.gerenciadorfinanceiro.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
