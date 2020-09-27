package com.beerhouse.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beerhouse.domain.Beer;
import com.beerhouse.service.BeerService;

@RestController
@RequestMapping("/beers")
public class BeerController {
	
	@Autowired
	private BeerService beerService;
	
	@GetMapping
	public ResponseEntity<List<Beer>> allBeers() {
		List<Beer> beers = beerService.getBeers();
		
		if (beers == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(beers);
	}
	
	@PostMapping
	public ResponseEntity<Beer> addBeer(@RequestBody Beer beer) throws Exception {
		Beer beerNew = beerService.saveBeer(beer);
		
		if (beerNew == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.created(new URI("/beers/" + beerNew.getId())).body(beerNew);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Beer> getBeer(@PathVariable Long id) {
		if (id <= 0) {
			return ResponseEntity.badRequest().build();
		}
		
		Beer beer = beerService.getBeer(id);
		
		if (beer == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(beer);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Beer> putBeer(@PathVariable Long id, @RequestBody Beer beer) {
		
		Beer changeBeer = beerService.changeBeer(id, beer);
		
		if (beer == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(changeBeer);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<Beer> patchBeer(@PathVariable Long id, @RequestBody Beer beer) {
		
		Beer changeBeer = beerService.changeBeer(id, beer);
		
		if (beer == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(changeBeer);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBeer(@PathVariable Long id) {
		beerService.deleteBeer(id);
		return ResponseEntity.noContent().build();
	}

}
