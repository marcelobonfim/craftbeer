package com.beerhouse.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beerhouse.domain.Beer;
import com.beerhouse.exception.BeerNotFoundException;
import com.beerhouse.repository.BeerRepository;

@Service
public class BeerService {
	
	@Autowired
	private BeerRepository beerRepository;

	public List<Beer> getBeers() {
		return (List<Beer>) beerRepository.findAll();
	}

	public Beer addBeer(Beer beer) {
		return beerRepository.save(beer);
	}

	public Beer getBeer(Long id) {
		return beerRepository.findById(id).orElseThrow(() -> new BeerNotFoundException(id));
	}

	public Beer changeBeer(Long id, Beer changeBeer) {
		return beerRepository.findById(id)
				.map(beer -> {
					beer.setName(changeBeer.getName());
					beer.setIngredients(changeBeer.getIngredients());
					beer.setAlcoholContent(changeBeer.getAlcoholContent());
					beer.setPrice(changeBeer.getPrice());
					beer.setCategory(changeBeer.getCategory());
					return beerRepository.save(beer);
				})
				.orElseGet(() -> {
					changeBeer.setId(id);
					return beerRepository.save(changeBeer);
				});
	}

	public Beer partialChangeBeer(Long id, @Valid Beer partialChangeBeer) {
		Beer beer = beerRepository.findById(id).orElseThrow(() -> new BeerNotFoundException(id));
		
		beer.setName(partialChangeBeer.getName() != null ? partialChangeBeer.getName() : beer.getName());
		beer.setIngredients(partialChangeBeer.getIngredients() != null ? partialChangeBeer.getIngredients() : beer.getIngredients());
		beer.setAlcoholContent(partialChangeBeer.getAlcoholContent() != null ? partialChangeBeer.getAlcoholContent() : beer.getAlcoholContent());
		beer.setPrice(partialChangeBeer.getPrice() > 0 ? partialChangeBeer.getPrice() : beer.getPrice());
		beer.setCategory(partialChangeBeer.getCategory() != null ? partialChangeBeer.getCategory() : beer.getCategory());
		
		return beerRepository.save(beer);
	}
	
	public void deleteBeer(Long id) {
		beerRepository.deleteById(id);
	}


}
