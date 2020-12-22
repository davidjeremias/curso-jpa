package com.u2d.config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public class InitializePersistenceUnitTest {

	protected static EntityManagerFactory entityManagerFactory;
	
	protected static EntityManager entityManager;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		entityManagerFactory = Persistence.createEntityManagerFactory("u2d-PU");
	}
	
	@AfterClass
	public static void tearDownAfterClass() {
		entityManagerFactory.close();
	}
	
	@Before
	public void setUp() {
		entityManager = entityManagerFactory.createEntityManager();
	}
	
	@After
	public void tearDown() {
		entityManager.close();
	}
	
}
