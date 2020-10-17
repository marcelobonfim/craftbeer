package com.beerhouse.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.beerhouse.dto.BeerDTO;
import com.beerhouse.service.BeerService;

@RestController
@RequestMapping("/beers")
public class BeerController {
	
	@Autowired
	private BeerService beerService;
	
	@GetMapping
	public ResponseEntity<List<BeerDTO>> allBeers() {
		try {
			List<BeerDTO> beers = beerService.getBeers();
			
			if (beers == null) {
				return ResponseEntity.notFound().build();
			}
			
			return ResponseEntity.ok(beers);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping
	public ResponseEntity<BeerDTO> addBeer(@Valid @RequestBody BeerDTO beerDTO) throws Exception {
		try {
			BeerDTO beerNew = beerService.addBeer(beerDTO);
			
			if (beerNew == null) {
				return ResponseEntity.notFound().build();
			}
			
			return ResponseEntity.created(new URI("/beers/" + beerNew.getId())).body(beerNew);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BeerDTO> getBeer(@PathVariable Long id) {
		try {
			if (id <= 0) {
				return ResponseEntity.badRequest().build();
			}
			
			BeerDTO beerDTO = beerService.getBeer(id);
			
			if (beerDTO == null) {
				return ResponseEntity.notFound().build();
			}
			
			return ResponseEntity.ok(beerDTO);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<BeerDTO> putBeer(@PathVariable Long id, @Valid @RequestBody BeerDTO beerDTO) {
		try {
			BeerDTO changeBeerDTO = beerService.changeBeer(id, beerDTO);
			
			if (changeBeerDTO == null) {
				return ResponseEntity.notFound().build();
			}
			
			return ResponseEntity.ok(changeBeerDTO);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<BeerDTO> patchBeer(@PathVariable Long id, @RequestBody BeerDTO beerDTO) {
		try {
			BeerDTO changeBeerDTO = beerService.partialChangeBeer(id, beerDTO);
			
			if (changeBeerDTO == null) {
				return ResponseEntity.notFound().build();
			}
			
			return ResponseEntity.ok(changeBeerDTO);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBeer(@PathVariable Long id) {
		try {
			beerService.deleteBeer(id);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
