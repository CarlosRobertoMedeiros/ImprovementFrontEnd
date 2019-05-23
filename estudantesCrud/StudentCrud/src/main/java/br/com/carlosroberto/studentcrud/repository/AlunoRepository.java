package br.com.carlosroberto.studentcrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.carlosroberto.studentcrud.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

}
