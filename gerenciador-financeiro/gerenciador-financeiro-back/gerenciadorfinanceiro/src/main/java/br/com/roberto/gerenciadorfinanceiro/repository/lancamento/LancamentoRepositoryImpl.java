package br.com.roberto.gerenciadorfinanceiro.repository.lancamento;

import java.util.List;
import java.util.function.Predicate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.roberto.gerenciadorfinanceiro.model.Lancamento;
import br.com.roberto.gerenciadorfinanceiro.repository.filter.LancamentoFilter;

public class LancamentoRepositoryImpl implements LancamentoRepositoryQuery{

	@PersistenceContext
	private EntityManager manager;
	
	
	@Override
	public List<Lancamento> filtrar(LancamentoFilter lancamentoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Lancamento> criteria = builder.createQuery(Lancamento.class);
		Root<Lancamento> root = criteria.from(Lancamento.class);
		
		/*Restrições*/
		
		Predicate[] predicates=null;
		criteria.where(predicates);
		
		
		TypedQuery<Lancamento> query = manager.createQuery(criteria);
		return query.getResultList();
	}

}
