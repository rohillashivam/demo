package com.sapient.data.aggregator.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.sapient.data.aggregator.request.RequestOps;

/**
 * 
 * @author shivam.rohilla
 *
 */
public class RequestParamFactory {

	public static Optional<Map<String, String>> getRequestParamMap(RequestOps ops, String apiKey, String id) {
		if (ops == null)
			return Optional.empty();

		switch (ops) {
		case STANDING:
			return buildStadingRequestParam(id, apiKey);
		case LEAGUE:
			return buildLeagueRequestParam(id, apiKey);

		case COUNTRY:
			return buildTeamRequestParam(apiKey);
		}
		return Optional.empty();
	}

	private static Optional<Map<String, String>> buildStadingRequestParam(String leagueId, String apiKey) {
		Map<String, String> teamParams = new HashMap<>();
		teamParams.put("action", "get_standings");
		teamParams.put("APIkey", apiKey);
		teamParams.put("league_id", leagueId);
		return Optional.ofNullable(teamParams);
	}

	private static Optional<Map<String, String>> buildLeagueRequestParam(String countryId, String apiKey) {
		Map<String, String> teamParams = new HashMap<>();
		teamParams.put("action", "get_leagues");
		teamParams.put("APIkey", apiKey);
		teamParams.put("country_id", countryId);
		return Optional.ofNullable(teamParams);
	}

	private static Optional<Map<String, String>> buildTeamRequestParam(String apiKey) {
		Map<String, String> teamParams = new HashMap<>();
		teamParams.put("action", "get_countries");
		teamParams.put("APIkey", apiKey);
		return Optional.ofNullable(teamParams);
	}
}
