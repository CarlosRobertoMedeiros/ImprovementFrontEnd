package br.com.roberto.gerenciadorfinanceiro.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.roberto.gerenciadorfinanceiro.model.Pessoa;
import br.com.roberto.gerenciadorfinanceiro.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	public Pessoa atualizar(Long codigo, Pessoa pessoa) {
		Pessoa pessoaSalva = buscarPeloCodigo(codigo);
		BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo");
		return pessoaRepository.save(pessoaSalva);
	}

	public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
		Pessoa pessoaSalva = buscarPeloCodigo(codigo);
		pessoaSalva.setAtivo(ativo);
		pessoaRepository.save(pessoaSalva);
	}

	public Pessoa buscarPeloCodigo(Long codigo) {
		try {
			Pessoa pessoaSalva = pessoaRepository.findById(codigo).get();
			return pessoaSalva;
		} catch (Exception e) {
			throw new EmptyResultDataAccessException(1);
		}

	}

}
