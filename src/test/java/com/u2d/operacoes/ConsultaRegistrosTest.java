package com.u2d.operacoes;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.u2d.config.InitializePersistenceUnitTest;
import com.u2d.model.Produto;

public class ConsultaRegistrosTest extends InitializePersistenceUnitTest{

	@Test
	public void buscarporIdentificador () {
		Produto produto = entityManager.find(Produto.class, 1);
		
		Assert.assertNotNull(produto);
		Assert.assertEquals("Kindle", produto.getNome());
	}
	
	@Test
	public void testaNomeDoProdutoKindle () {
		Produto produto = entityManager.find(Produto.class, 1);
		
		Assert.assertEquals("Kindle", produto.getNome());
	}
	
	@Test
	public void buscarTodosProdutos () {
		List<Produto> produto = entityManager.createQuery("from Produto", Produto.class).getResultList();
		
		Assert.assertEquals(true, produto.size() > 0);
	}
}
