package com.swapi.rest.api.repositorys;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.swapi.rest.api.documents.Planet;

public interface PlanetRepository extends MongoRepository<Planet, String> {

}
