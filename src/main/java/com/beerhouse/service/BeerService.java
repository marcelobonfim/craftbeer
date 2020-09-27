package com.beerhouse.service;

import java.util.List;

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

	public Beer saveBeer(Beer beer) {
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

	public void deleteBeer(Long id) {
		beerRepository.deleteById(id);
	}

}
