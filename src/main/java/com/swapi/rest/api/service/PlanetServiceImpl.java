package com.swapi.rest.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swapi.rest.api.documents.Planet;
import com.swapi.rest.api.repositorys.PlanetRepository;

@Service
public class PlanetServiceImpl implements PlanetService {

	@Autowired
	private PlanetRepository pr;
	
	@Override
	public List<Planet> findAll() {
		return pr.findAll();
	}

	@Override
	public Planet findById(String id) {
		return pr.findById(id).orElse(null);
	}

	@Override
	public Planet findByName(String name) {
		return null;
	}

	@Override
	public Planet create(Planet planet) {
		// TODO Auto-generated method stub
		return pr.save(planet);
	}

	@Override
	public Planet update(Planet planet) {
		// TODO Auto-generated method stub
		return pr.save(planet);
	}

	@Override
	public void delete(String id) {
		pr.deleteById(id);
	}

}
