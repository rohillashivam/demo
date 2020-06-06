package com.sapient.data.aggregator.processor.league;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sapient.command.utils.JsonUtils;
import com.sapient.data.aggregator.factory.RequestParamFactory;
import com.sapient.data.aggregator.request.RequestOps;
import com.sapient.entity.HTTPRequestEntity;
import com.sapient.entity.HttpResponseEntity;
import com.sapient.entity.football.Country;
import com.sapient.entity.football.League;

/**
 * 
 * @author shivam.rohilla
 *
 */
@Component
public class LeagueProcessor {

	public List<League> process(List<Country> countryList, String url, String apiKey, Function<HTTPRequestEntity, List<HttpResponseEntity>> executeRequest) {
		List<League> leagueList = new ArrayList<>();
		if(countryList == null || countryList.isEmpty())
			return leagueList;
		
		for(Country country : countryList) {
			if(country == null || country.getId() == null)
				continue;
			HTTPRequestEntity leagueRequest = createLeagueRequest(String.valueOf(country.getId()), url, apiKey);
			List<HttpResponseEntity> leagueResponse = executeRequest.apply(leagueRequest);
			if(leagueResponse == null || leagueResponse.isEmpty())
				continue;
			for(HttpResponseEntity leagueResponseObj : leagueResponse) {
				buildList(leagueList, leagueResponseObj);
			}
		}
		return leagueList;
	
	}

	private void buildList(List<League> leagueList, HttpResponseEntity leagueResponseObj) {
		if(leagueResponseObj.getResponseCode() != 200)
			return;
		List<League> leagueListNew = (List<League>) JsonUtils.deserialize(String.valueOf(leagueResponseObj.getResponse()), new TypeReference<List<League>>(){});
		if(leagueListNew == null)
			return;
		leagueList.addAll(leagueListNew);
	}
	
	private HTTPRequestEntity createLeagueRequest(String countryId, String url, String apiKey) {
		Optional<Map<String, String>> requestOpts = RequestParamFactory.getRequestParamMap(RequestOps.LEAGUE, apiKey, countryId);
		return HTTPRequestEntity.builder().methodType("GET").url(url).requestParam(requestOpts.isPresent() ? requestOpts.get() : new HashMap<>()).build();
	}
}
