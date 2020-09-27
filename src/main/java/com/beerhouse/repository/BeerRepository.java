package com.beerhouse.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.beerhouse.domain.Beer;

@Repository
public interface BeerRepository  extends CrudRepository<Beer, Long>{

}
