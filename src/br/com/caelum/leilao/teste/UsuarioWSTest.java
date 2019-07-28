package br.com.caelum.leilao.teste;



import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.path.xml.XmlPath;
import com.jayway.restassured.response.Header;

import br.com.caelum.leilao.modelo.Usuario;

import static com.jayway.restassured.RestAssured.*;

public class UsuarioWSTest {

	private Usuario mauricio;
	private Usuario guilherme;
	
	/*@Test
	public void deveRetornarListaDeUsuarios() {
		XmlPath path = get("/usuarios?_format=xml").andReturn().xmlPath();
		Usuario usuario1 = path.getObject("list.usuario[0]", Usuario.class);
		Usuario usuario2 = path.getObject("list.usuario[1]", Usuario.class);		
		Usuario esperado1 =  new Usuario(1l, "Mauricio Aniche", "mauricio.aniche@caelum.com.br");
		Usuario esperado2 =  new Usuario(2l, "Guilherme Silveira", "guilherme.silveira@caelum.com.br");		
		assertEquals(esperado1, usuario1);
		assertEquals(esperado2, usuario2);		
	}*/
	
	@Before
	public void setUp() {
		
		mauricio =  new Usuario(1l, "Mauricio Aniche", "mauricio.aniche@caelum.com.br");
		guilherme =  new Usuario(2l, "Guilherme Silveira", "guilherme.silveira@caelum.com.br");	
		RestAssured.baseURI =  "http://localhost";
		RestAssured.port = 8080;
	}
	
	@Test
	public void deveRetornarListaDeUsuarios() {
		XmlPath path =
				given().
				header("Accept","application/xml").
				get("/usuarios").andReturn().xmlPath();
		Usuario usuario1 = path.getObject("list.usuario[0]", Usuario.class);
		Usuario usuario2 = path.getObject("list.usuario[1]", Usuario.class);		
			
		assertEquals(mauricio, usuario1);
		assertEquals(guilherme, usuario2);		
	}
	
	@Test
	public void deveRetornarUsuaioPeloId() {
		/*JsonPath path = given().header("Accept","application/json")
		.get("/usuarios/show?usuario.id=1")
		.andReturn().jsonPath();*/
		JsonPath path = given().header("Accept","application/json")
				.parameter("usuario.id", 1)
				.get("/usuarios/show")
				.andReturn().jsonPath();
		
		Usuario usuario = path.getObject("usuario", Usuario.class);		
		mauricio =  new Usuario(1l, "Mauricio Aniche", "mauricio.aniche@caelum.com.br");
		assertEquals(mauricio, usuario);		
	}
	
	@Test
	public void deveAdicionarUmUsuario() {
		Usuario joao =  new Usuario("João da Silva", "joao@dasilva.com");
		
		XmlPath path = given()
			.header("Accept", "application/xml")
			.contentType("application/xml")
			.body(joao)
		.expect()
			.statusCode(200)
		.when()
			.post("usuarios")
		.andReturn()
			.xmlPath();
		
		Usuario resposta =  path.getObject("usuario", Usuario.class);
		
		assertEquals(joao.getNome(), resposta.getNome());
		assertEquals(joao.getEmail(), resposta.getEmail());		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
