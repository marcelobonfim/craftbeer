package com.beerhouse;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import com.beerhouse.domain.Beer;
import com.beerhouse.dto.BeerDTO;
import com.beerhouse.mappers.BeerMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan(basePackageClasses = Application.class)
public class ApplicationTests {
	
	@Autowired
	private BeerMapper beerMapper;

	@Test
	public void whenConvertBeerDtoToBeertEntity_thenCorrect() {
		BeerDTO beerDTO = new BeerDTO(1L, "Appia", "Rapadura e Mel", "8%", 12, "Honey Wheat Ale");
		
		Beer beer = beerMapper.dtoToEntity(beerDTO);
		
		assertEquals(beer.getId(), beerDTO.getId());
		assertEquals(beer.getName(), beerDTO.getName());
		assertEquals(beer.getIngredients(), beerDTO.getIngredients());
		assertEquals(beer.getAlcoholContent(), beerDTO.getAlcoholContent());
		assertEquals(beer.getPrice(), beerDTO.getPrice());
		assertEquals(beer.getCategory(), beerDTO.getCategory());
	}
	
	@Test
    public void whenConvertBeerEntityToBeerDto_thenCorrect() {
		Beer beer = new Beer(1L, "Appia", "Rapadura e Mel", "8%", 12, "Honey Wheat Ale");
		
		BeerDTO beerDTO = beerMapper.entityToDto(beer);
		
		assertEquals(beerDTO.getId(), beer.getId());
		assertEquals(beerDTO.getName(), beer.getName());
		assertEquals(beerDTO.getIngredients(), beer.getIngredients());
		assertEquals(beerDTO.getAlcoholContent(), beer.getAlcoholContent());
		assertEquals(beerDTO.getPrice(), beer.getPrice());
		assertEquals(beerDTO.getCategory(), beer.getCategory());
    }

}