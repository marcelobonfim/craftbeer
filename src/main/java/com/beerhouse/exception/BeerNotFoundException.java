package com.beerhouse.exception;

public class BeerNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 2224167911997035296L;

	public BeerNotFoundException(Long id) {
		super("Beer id: "+ id +" not found");
	}
}
