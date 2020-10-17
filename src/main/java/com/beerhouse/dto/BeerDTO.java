package com.beerhouse.dto;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BeerDTO {
	
	@Id
	@NotNull
	private long id;
	
	@NotNull
	private String name;
	
	@NotNull
	private String ingredients;
	
	@NotNull
	private String alcoholContent;
	
	@NotNull
	private double price;
	
	@NotNull
	private String category;

}
