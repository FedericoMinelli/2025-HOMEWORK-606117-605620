package it.uniroma3.test.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ComandoTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		System.out.println("eseguo il setUp()");
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test1() {
		System.out.println("eseguo il test1");
	}
	@Test
	void test2() {
		System.out.println("eseguo il test2");
	}
	@Test
	void test3() {
		System.out.println("eseguo il test3");
	}

}
