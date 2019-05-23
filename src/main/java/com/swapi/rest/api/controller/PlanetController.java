package com.swapi.rest.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swapi.rest.api.documents.Planet;
import com.swapi.rest.api.responses.Response;
import com.swapi.rest.api.service.PlanetService;


@RestController
@RequestMapping(path = "/api/planet")
public class PlanetController {
	
	@Autowired
	private PlanetService ps;
	
	@GetMapping
	public ResponseEntity<Response<List<Planet>>> findAll() {
		List<Planet> planets = ps.findAll();
		planets.forEach(p -> p.setAppear(planetApparition(p.getName())));
		return ResponseEntity.ok(new Response<List<Planet>>(planets));
	}
	
	@GetMapping(path = "{id}")
	public ResponseEntity<Response<Planet>> findById(@PathVariable(name = "id") String id) {
		Planet findedPlanet = ps.findById(id);
		findedPlanet.setAppear(planetApparition(findedPlanet.getName()));
		return ResponseEntity.ok(new Response<Planet>(findedPlanet));
	}
	
	@PostMapping
	public ResponseEntity<Response<Planet>> create(@RequestBody Planet planet, BindingResult result) {
		if(result.hasErrors()) {
			List<String> errors = new ArrayList<String>();
			result.getAllErrors().forEach(err -> errors.add(err.getDefaultMessage()));
			return ResponseEntity.badRequest().body(new Response<Planet>(errors));
		}
		Planet createdPlanet = ps.create(planet);
		createdPlanet.setAppear(planetApparition(createdPlanet.getName()));
		return ResponseEntity.ok(new Response<Planet>(createdPlanet));
	}
	
	@PutMapping(path = "{id}")
	public ResponseEntity<Response<Planet>> update(@PathVariable(name = "id") String id, @RequestBody Planet planet, BindingResult result) {
		if(result.hasErrors()) {
			List<String> errors = new ArrayList<String>();
			result.getAllErrors().forEach(err -> errors.add(err.getDefaultMessage()));
			return ResponseEntity.badRequest().body(new Response<Planet>(errors));
		}
		planet.setId(id);
		Planet updatedPlanet = ps.update(planet);
		updatedPlanet.setAppear(planetApparition(updatedPlanet.getName()));
		return ResponseEntity.ok(new Response<Planet>(updatedPlanet));
	}
	
	@DeleteMapping(path = "{id}")
	public ResponseEntity<Boolean> update(@PathVariable(name = "id") String id) {
		if(id.trim().equals("")) {
			return ResponseEntity.badRequest().body(false);
		}
		ps.delete(id);
		return ResponseEntity.ok(true);
	}		
	
	private final String url = "https://swapi.co/api/planets/?search=";
	private HttpURLConnection con; 
	
	private StringBuffer reqSwApi (String name) {
		try {
			URL uri = new URL(url + name);
			con = (HttpURLConnection) uri.openConnection();
			
			con.setRequestMethod("GET");
			con.setConnectTimeout(5000);
			con.setReadTimeout(5000);
			con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
			con.setRequestProperty("Accept", "application/json");
			
			BufferedReader in = new BufferedReader(
					new InputStreamReader(con.getInputStream()));
			
			String inputLine;
			StringBuffer response = new StringBuffer();
			while((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			return response;
			
		} catch(MalformedURLException e) {
			e.printStackTrace();
			return new StringBuffer();
		} catch(IOException e) {
			e.printStackTrace();
			return new StringBuffer();
		} finally {
			con.disconnect();
		}
	}
	
	protected int planetApparition(String name) {
		int apparition = 0; 
		StringBuffer res = reqSwApi(name);
		
		if(res == null) { return 0; }
		
		JSONObject obj = new JSONObject(res.toString());
		JSONArray objResults = obj.getJSONArray("results");
		for(int i = 0; i < objResults.length(); i ++) {
			JSONObject a = objResults.optJSONObject(i);
			apparition = a.getJSONArray("films").length();
		}
		return apparition;
	}
}
