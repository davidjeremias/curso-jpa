package com.u2d.operacoes;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import com.u2d.config.InitializePersistenceUnitTest;
import com.u2d.model.Produto;

public class OperacaoComTrasacaoTest extends InitializePersistenceUnitTest{

	@Test
	public void abrirFecharTransacao() {
		
		Produto produto = new Produto();
		
		entityManager.getTransaction().begin();
		
//		entityManager.persist(produto);
//		entityManager.merge(produto);
//		entityManager.remove(produto);
		
		
		entityManager.getTransaction().commit();
	}
	
	@Test
	public void inserirProdutoVerificandoNoBancoComCache() {
		
		Produto produto = Produto.builder().id(2).nome("Câmera kodak").descricao("a camera reliquia").preco(new BigDecimal(1500)).build();
		
		entityManager.getTransaction().begin();
		entityManager.persist(produto);
		entityManager.getTransaction().commit();
		
		// A pesquisa não será efetuada no banco pois o objeto está em cache memoria
		Produto produtoRetorno = entityManager.find(Produto.class, produto.getId());
		Assert.assertNotNull(produtoRetorno);
	}
	
	@Test
	public void inserirProdutoVerificandoNoBancoSemCache() {
		
		Produto produto = Produto.builder().nome("Câmera kodak").descricao("a camera reliquia").preco(new BigDecimal(1500)).build();
		
		entityManager.getTransaction().begin();
		entityManager.persist(produto);
		entityManager.getTransaction().commit();
		
		// o metodo clear limpa a memoria e assim tendo que ir no banco para consultar o produto
		entityManager.clear();
		
		Produto produtoRetorno = entityManager.find(Produto.class, produto.getId());
		Assert.assertNotNull(produtoRetorno);
	}
	
	@Test
	public void inserirProdutoSemIniciarTransacao() {
		
		Produto produto = Produto.builder().nome("Câmera kodak").descricao("a camera reliquia").preco(new BigDecimal(1500)).build();
		
		// ao persistir um objeto no banco o metodo persist verifica se há transação, se não tiver ele aguarda uma transação ser criada para persistir
		entityManager.persist(produto);
		
		entityManager.getTransaction().begin();
		entityManager.getTransaction().commit();
		
		Produto produtoRetorno = entityManager.find(Produto.class, produto.getId());
		Assert.assertNotNull(produtoRetorno);
	}
	
	@Test
	public void inserirProdutoSemTransacao() {
		
		Produto produto = Produto.builder().nome("Câmera kodak").descricao("a camera reliquia").preco(new BigDecimal(1500)).build();
		
		// ao persistir um objeto no banco o metodo persist verifica se há transação, se não tiver ele aguarda uma transação ser criada para persistir
		entityManager.persist(produto);
		// o metodo flush força a inserção do objeto sem transação, porem para efetuar qualquer operação de banco é necessario uma transação ocorre uma exception de no transaction
		entityManager.flush();
		
		Produto produtoRetorno = entityManager.find(Produto.class, produto.getId());
		Assert.assertNotNull(produtoRetorno);
	}
	
	@Test
	public void deletarProdutoNaoExistenteNoBanco() {
		
		Produto produto = new Produto();
		produto.setId(300);
		
		entityManager.getTransaction().begin();
		// não dar erro pelo fato do metodo remove fazer um select para ver se o objeto existe na base
		entityManager.remove(produto);
		entityManager.getTransaction().commit();
	}
	
	@Test
	public void deletarProdutoNaoGerenciadoDatached() {
		
		Produto produto = new Produto();
		produto.setId(3);
		
		entityManager.getTransaction().begin();
		// erro de entity datached ou seja entidade não gerenciada pelo entitymanager
		entityManager.remove(produto);
		entityManager.getTransaction().commit();
	}
	
	@Test
	public void deletarProduto() {
		
		Produto produto = entityManager.find(Produto.class, 3);
		
		entityManager.getTransaction().begin();
		entityManager.remove(produto);
		entityManager.getTransaction().commit();
		
		Produto produtoRetorno = entityManager.find(Produto.class, 3);
		
		Assert.assertNull(produtoRetorno);
	}
	
	@Test
	public void deletarProdutoSemTransacaoIniciada() {
		
		Produto produto = entityManager.find(Produto.class, 3);
		
		// ao remover um objeto no banco o metodo remove verifica se há transação, se não tiver ele aguarda uma transação ser criada para remover
		entityManager.remove(produto);
		
		entityManager.getTransaction().begin();
		entityManager.getTransaction().commit();
		
//		entityManager.clear(); não necessario na remoção pois ele é removido da base e da memoria cache
		
		Produto produtoRetorno = entityManager.find(Produto.class, 3);
		
		Assert.assertNull(produtoRetorno);
	}
	
	
}
