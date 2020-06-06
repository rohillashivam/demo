package com.sapient.entity.football;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author shivam.rohilla
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class League {

	@JsonProperty("country_id")
	private Integer countryId;
	
	@JsonProperty("country_name")
	private String countryName;
	
	@JsonProperty("league_id")
	private Integer id;
	
	@JsonProperty("league_name")
	private String name;
	
	@JsonProperty("league_season")
	private String leagueSeason;
	
	@JsonProperty("league_logo")
	private String leagueLogo;

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

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

	public String getLeagueSeason() {
		return leagueSeason;
	}

	public void setLeagueSeason(String leagueSeason) {
		this.leagueSeason = leagueSeason;
	}

	public String getLeagueLogo() {
		return leagueLogo;
	}

	public void setLeagueLogo(String leagueLogo) {
		this.leagueLogo = leagueLogo;
	}
	
}
