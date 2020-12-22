package com.u2d.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
public class Produto implements Serializable{
	
	private static final long serialVersionUID = -3328980771791788165L;
	
	@EqualsAndHashCode.Include
	@Id
	private Integer id;
	private String nome;
	private String descricao;
	private BigDecimal preco;

}
