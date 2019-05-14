package com.roberto.listatelefonica.api.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.UniqueElements;

@Entity
@Table(name = "tb_operadora")
public class Operadora {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@UniqueElements
	@Column(name = "codigooperadora")
	private Long codigoOperadora;

	@NotBlank
	private String nome;

	@NotBlank
	@Column(name = "valorporminuto")
	private BigDecimal valorPorMinuto;

	@OneToOne()
	@JoinColumn(name="codigooperadoratelefonecategoria")
	private OperadoraTelefoneCategoria operadoraTelefoneCategoria;

	public Long getCodigoOperadora() {
		return codigoOperadora;
	}

	public void setCodigoOperadora(Long codigoOperadora) {
		this.codigoOperadora = codigoOperadora;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public OperadoraTelefoneCategoria getOperadoraTelefoneCategoria() {
		return operadoraTelefoneCategoria;
	}

	public void setOperadoraTelefoneCategoria(OperadoraTelefoneCategoria operadoraTelefoneCategoria) {
		this.operadoraTelefoneCategoria = operadoraTelefoneCategoria;
	}

	public Long getCodigo() {
		return codigo;
	}

	public BigDecimal getValorPorMinuto() {
		return valorPorMinuto;
	}

	public void setValorPorMinuto(BigDecimal valorPorMinuto) {
		this.valorPorMinuto = valorPorMinuto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Operadora other = (Operadora) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
