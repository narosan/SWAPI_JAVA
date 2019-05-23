package com.swapi.rest.api.documents;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Planet")
public class Planet {

	public Planet() {
		super();
	}
	
	public Planet(String id, String name, String terrain, String climate) {
		super();
		this.id = id;
		this.name = name;
		this.terrain = terrain;
		this.climate = climate;
	}

	@Id
	private String id;
	
	@NotEmpty(message = "Name can't be empty")
	private String name;
	
	@NotEmpty(message = "Terrain can't be empty")
	private String terrain;
	
	@NotEmpty(message = "Climate can't be empty")
	private String climate;
	
	private int appear;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTerrain() {
		return terrain;
	}

	public void setTerrain(String terrain) {
		this.terrain = terrain;
	}

	public String getClimate() {
		return climate;
	}

	public void setClimate(String climate) {
		this.climate = climate;
	}

	public int getAppear() {
		return appear;
	}

	public void setAppear(int appear) {
		this.appear = appear;
	}
	
	
}
