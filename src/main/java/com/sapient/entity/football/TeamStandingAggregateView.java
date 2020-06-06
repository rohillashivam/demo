package com.sapient.entity.football;

import lombok.Builder;
import lombok.Data;

/**
 * 
 * @author shivam.rohilla
 *
 */
@Data
@Builder
public class TeamStandingAggregateView {

	private Integer countryId;
	
	private String countryName;
	
	private Integer leagueId;
	
	private String leagueName;
	
	private Integer teamId;
	
	private String teamName;
	
	private Integer standing;
}
