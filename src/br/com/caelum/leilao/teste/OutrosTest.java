package br.com.caelum.leilao.teste;

import static org.junit.Assert.*;

import org.junit.Test;
import static com.jayway.restassured.RestAssured.expect;

public class OutrosTest {

	@Test
	public void deveGerarUmCookie() {
		
		expect()
			.cookie("rest-assured", "funciona")
		.get("/cookie/teste");
	}
	
	@Test
	public void deveGerarUmHeader() {
		
		expect()
			.cookie("novo-header", "abc")
		.get("/cookie/teste");
	}

}
