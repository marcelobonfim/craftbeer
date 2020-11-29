package com.beerhouse.dto;

import javax.validation.constraints.NotBlank;
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
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String ingredients;
	
	@NotBlank
	private String alcoholContent;
	
	@NotNull
	private Double price;
	
	@NotBlank
	private String category;

}
