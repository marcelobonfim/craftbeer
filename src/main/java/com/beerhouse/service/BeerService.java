package com.beerhouse.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beerhouse.domain.Beer;
import com.beerhouse.dto.BeerDTO;
import com.beerhouse.exception.BeerNotFoundException;
import com.beerhouse.mappers.BeerMapper;
import com.beerhouse.repository.BeerRepository;

@Service
public class BeerService {
	
	@Autowired
	private BeerRepository beerRepository;
	
	@Autowired
	private BeerMapper beerMapper;

	public List<BeerDTO> getBeers() {
		return beerRepository.findAll()
				.stream().map(b -> this.beerMapper.entityToDto(b))
                .collect(Collectors.toList());
	}

	public BeerDTO addBeer(BeerDTO beerDTO) {
		Beer beer = this.beerMapper.dtoToEntity(beerDTO);
		
		beerRepository.save(beer);
		
		return this.beerMapper.entityToDto(beer);
	}

	public BeerDTO getBeer(Long id) {
		return beerRepository.findById(id).map(b -> this.beerMapper.entityToDto(b)).orElseThrow(() -> new BeerNotFoundException(id));
	}

	public BeerDTO changeBeer(Long id, BeerDTO changeBeerDTO) {
		 Beer changeBeer = beerRepository.findById(id)
				.map(beer -> {
					beer.setName(changeBeerDTO.getName());
					beer.setIngredients(changeBeerDTO.getIngredients());
					beer.setAlcoholContent(changeBeerDTO.getAlcoholContent());
					beer.setPrice(changeBeerDTO.getPrice());
					beer.setCategory(changeBeerDTO.getCategory());
					return beerRepository.save(beer);
				})
				.orElseGet(() -> {
					changeBeerDTO.setId(id);
					return beerRepository.save(this.beerMapper.dtoToEntity(changeBeerDTO));
				});
		 
		return this.beerMapper.entityToDto(changeBeer);
	}

	public BeerDTO partialChangeBeer(Long id, BeerDTO partialChangeBeer) {
		Beer beer = beerRepository.findById(id).orElseThrow(() -> new BeerNotFoundException(id));
		
		beer.setName(partialChangeBeer.getName() != null ? partialChangeBeer.getName() : beer.getName());
		beer.setIngredients(partialChangeBeer.getIngredients() != null ? partialChangeBeer.getIngredients() : beer.getIngredients());
		beer.setAlcoholContent(partialChangeBeer.getAlcoholContent() != null ? partialChangeBeer.getAlcoholContent() : beer.getAlcoholContent());
		beer.setPrice(partialChangeBeer.getPrice() > 0 ? partialChangeBeer.getPrice() : beer.getPrice());
		beer.setCategory(partialChangeBeer.getCategory() != null ? partialChangeBeer.getCategory() : beer.getCategory());
		
		return this.beerMapper.entityToDto(beerRepository.save(beer));
	}
	
	public void deleteBeer(Long id) {
		beerRepository.deleteById(id);
	}

	public boolean existisBeer(Long id) {
		return beerRepository.existsById(id);
	}

}
