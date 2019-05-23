package com.swapi.rest.api.service;

import java.util.List;

import com.swapi.rest.api.documents.Planet;

public interface PlanetService {
	List<Planet> findAll();
	Planet findById(String id);
	Planet findByName(String name);
	Planet create(Planet planet);
	Planet update(Planet planet);
	void delete(String id);
}
