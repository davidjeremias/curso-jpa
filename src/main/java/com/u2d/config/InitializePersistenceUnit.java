package com.u2d.config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.u2d.model.Produto;

public class InitializePersistenceUnit {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("u2d-PU");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		Produto prod = entityManager.find(Produto.class, 1);
		System.out.println(prod.getNome());
		entityManager.close();
		entityManagerFactory.close();
	}
}
