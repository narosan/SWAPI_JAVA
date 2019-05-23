package com.swapi.rest.api.repositorys;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.swapi.rest.api.documents.Planet;

public interface PlanetRepository extends MongoRepository<Planet, String> {

}
