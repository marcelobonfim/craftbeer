package com.beerhouse.controller;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import com.beerhouse.domain.Beer;
import com.beerhouse.service.BeerService;

import io.restassured.http.ContentType;

@WebMvcTest
public class BeerControllerTest {
	
	@Autowired
	private BeerController beerController;
	
	@MockBean
	private BeerService beerService;
	
	private Beer beer;
	
	@BeforeEach
	public void setup() {
		standaloneSetup(this.beerController);
		
		beer = new Beer(1L, "Appia", "Rapadura e Mel", "8%", 12, "Honey Wheat Ale");
	}
	
	@Test
	public void shouldReturnSuccess_WhenSearchBeers() {
		when(beerService.getBeers()).thenReturn(Arrays.asList(beer));
		
		given()
			.accept(ContentType.JSON)
		.when()
			.get("/beers")
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void shouldReturnNotFound_WhenSearchBeers() {
		when(beerService.getBeers()).thenReturn(null);
		
		given()
			.accept(ContentType.JSON)
		.when()
			.get("/beers")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	@Test
	public void shouldReturnSuccess_WhenAddBeer() {
		when(beerService.addBeer(beer)).thenReturn(beer);
		
		given()
			.contentType(ContentType.JSON)
			.body(beer)
		.when()
			.post("/beers")
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
	
	@Test
	public void shouldReturnNotFound_WhenAddBeer() {
		when(beerService.addBeer(beer)).thenReturn(null);
		
		given()
			.contentType(ContentType.JSON)
			.body(beer)
		.when()
			.post("/beers")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());	
	}
	
	@Test
	public void shouldReturnSuccess_WhenSearchBeer() {

		when(this.beerService.getBeer(1L))
			.thenReturn(beer);
		
		given()
			.accept(ContentType.JSON)
		.when()
			.get("/beers/{id}", 1L)
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void shouldReturnNotFound_WhenSearchBeer() {
		
		when(this.beerService.getBeer(1L))
			.thenReturn(null);
		
		given()
			.accept(ContentType.JSON)
		.when()
			.get("/beers/{id}", 1L)
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	@Test
	public void shouldReturnBadRequest_WhenSearchBeer() {
		
		given()
			.accept(ContentType.JSON)
		.when()
			.get("/beers/{id}", -1L)
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value());
		
		verify(this.beerService, never()).getBeer(-1L);
	}
	
	@Test
	public void shouldReturnSuccess_WhenChangeBeer() {
		
		beer.setName("Cauim");
		beer.setIngredients("Trigo");
		beer.setCategory("Pilsen");

		when(this.beerService.changeBeer(1L, beer))
			.thenReturn(beer);
		
		given()
			.contentType(ContentType.JSON)
			.body(beer)
		.when()
			.put("/beers/{id}", 1L)
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void shouldReturnNotFound_WhenChangeBeer() {
		
		beer.setName("Cauim");
		beer.setIngredients("Trigo");
		beer.setCategory("Pilsen");
		
		when(this.beerService.changeBeer(1L, beer))
			.thenReturn(null);
		
		given()
			.contentType(ContentType.JSON)
			.body(beer)
		.when()
			.put("/beers/{id}", 1L)
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	@Test
	public void shouldReturnSuccess_WhenPartialChangeBeer() {
		
		beer.setPrice(11);

		when(this.beerService.partialChangeBeer(1L, beer))
			.thenReturn(beer);
		
		given()
			.contentType(ContentType.JSON)
			.body(beer)
		.when()
			.patch("/beers/{id}", 1L)
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void shouldReturnNotFound_WhenPartialChangeBeer() {
		
		beer.setPrice(11);
		
		when(this.beerService.partialChangeBeer(1L, beer))
			.thenReturn(null);
		
		given()
			.contentType(ContentType.JSON)
			.body(beer)
		.when()
			.patch("/beers/{id}", 1L)
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	@Test
	public void shouldReturnSuccess_WhenDeleteBeer() {
		given()
			.accept(ContentType.JSON)
		.when()
			.delete("/beers/{id}", 1L)
		.then()
			.statusCode(HttpStatus.NO_CONTENT.value());
		
		verify(this.beerService, Mockito.times(1)).deleteBeer(1L);
	}

}
