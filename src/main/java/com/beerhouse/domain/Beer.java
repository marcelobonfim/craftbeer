package com.beerhouse.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class Beer {
	
	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String ingredients;
	private String alcoholContent;
	private double price;
	private String category;
	
	public Beer() {
		
	}

}
