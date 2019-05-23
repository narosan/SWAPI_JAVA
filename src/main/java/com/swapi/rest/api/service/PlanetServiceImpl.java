package com.swapi.rest.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.swapi.rest.api.documents.Planet;
import com.swapi.rest.api.repositorys.PlanetRepository;

@Service
public class PlanetServiceImpl implements PlanetService {

	private final MongoTemplate mongoTemplate;
	
	@Autowired
	public PlanetServiceImpl(MongoTemplate template) {
		mongoTemplate = template;
	}
	
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
	public List<Planet> findByName(String name) {
		Query query = new Query();
		query.addCriteria(Criteria.where("name").is(name));
		return mongoTemplate.find(query, Planet.class);
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
