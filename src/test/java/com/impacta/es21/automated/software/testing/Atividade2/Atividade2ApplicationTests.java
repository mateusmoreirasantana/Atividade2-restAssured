package com.impacta.es21.automated.software.testing.Atividade2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static io.restassured.RestAssured.get;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ContextConfiguration
class Atividade2ApplicationTests {

	@BeforeAll
	public static void setup() {
		RestAssured.baseURI = "https://parallelum.com.br/fipe/api/v1/";
	}

	@Test
	public void testGetValueFIPEVehicleSuccess() {
		Response response = get("carros/marcas/59/modelos/5940/anos/2014-3").then().extract().response();
		assertEquals(HttpStatus.SC_OK, response.statusCode());
		assertEquals("AMAROK High.CD 2.0 16V TDI 4x4 Dies. Aut", response.jsonPath().getString("Modelo"));
	}

	@Test
	public void testGetValueFipeVehicleNotFound() {
		Response response = get("carros/marcas/59/modelos/594012/anos/2014-3").then().extract().response();
		assertEquals(HttpStatus.SC_NOT_FOUND, response.statusCode());

	}

}
