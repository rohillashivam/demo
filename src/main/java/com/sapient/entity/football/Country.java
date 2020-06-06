package com.sapient.entity.football;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author shivam.rohilla
 *
 */
@JsonIgnoreProperties (ignoreUnknown = true)
public class Country {
	
	@JsonProperty("country_id")
	private Integer id;
	
	@JsonProperty("country_name")
	private String name;
	
	@JsonProperty("country_logo")
	private String countryLogo;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCountryLogo() {
		return countryLogo;
	}
	public void setCountryLogo(String countryLogo) {
		this.countryLogo = countryLogo;
	}
	
	
}
