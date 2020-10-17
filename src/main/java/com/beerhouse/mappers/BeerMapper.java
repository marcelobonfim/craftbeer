package com.beerhouse.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.beerhouse.domain.Beer;
import com.beerhouse.dto.BeerDTO;

@Component
public class BeerMapper {
	
	private final ModelMapper modelMapper;

	public BeerMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}
	
	public Beer dtoToEntity(BeerDTO beerDTO) {
		Beer beer = modelMapper.map(beerDTO, Beer.class);
		return beer;
	}
	
	public BeerDTO entityToDto(Beer beer) {
		BeerDTO beerDTO = modelMapper.map(beer, BeerDTO.class);
		return beerDTO;	
	}

}
