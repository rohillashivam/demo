package com.sapient.data.aggregator.processor.standing;

import java.io.IOException;
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
import com.sapient.entity.football.League;
import com.sapient.entity.football.TeamStandingAggregateView;

/**
 * 
 * @author shivam.rohilla
 *
 */
@Component
public class StandingAggregatorProcessor {

	public List<TeamStandingAggregateView> process(List<League> leagueList, String url, String apiKey, Function<HTTPRequestEntity, List<HttpResponseEntity>> executeRequest) throws IOException {
		List<TeamStandingAggregateView> teamStandingAggViewList = new ArrayList<>();
		for(League league : leagueList) {
			HTTPRequestEntity standingRequest = createTeamStandingRequest(String.valueOf(league.getId()), url, apiKey);
			List<HttpResponseEntity> standingResponseList = executeRequest.apply(standingRequest);
			if(standingResponseList == null)
				return null;
			
			for(HttpResponseEntity standingResponse : standingResponseList) {
				List<Map<String, String>> responseMapList =  (List<Map<String, String>>) JsonUtils.deserialize(String.valueOf(standingResponse.getResponse()), new TypeReference<List<Map<String, String>>>(){});
				for(Map<String, String> standingMap : responseMapList) {
					TeamStandingAggregateView teamStandingAggregateView = TeamStandingAggregateView.builder().
							countryId(standingMap.get("country_id") != null ? Integer.parseInt(standingMap.get("country_id")) : null).
							countryName(standingMap.get("country_name")).leagueId(standingMap.get("league_id") != null ? Integer.parseInt(standingMap.get("league_id")) : null).
							leagueName(standingMap.get("league_name")).
							teamId(standingMap.get("team_id") !=  null ? Integer.parseInt(standingMap.get("team_id")) : null).
							teamName(standingMap.get("team_name")).
							standing(standingMap.get("overall_league_position") != null ? Integer.parseInt(standingMap.get("overall_league_position")): null)
							.build();
					teamStandingAggViewList.add(teamStandingAggregateView);
				}
			}
		}
		return teamStandingAggViewList;
	}
	
	private HTTPRequestEntity createTeamStandingRequest(String leagueId, String url, String apiKey) {
		Optional<Map<String, String>> requestOpts = RequestParamFactory.getRequestParamMap(RequestOps.STANDING, apiKey, leagueId);
		return HTTPRequestEntity.builder().methodType("GET").url(url).requestParam(requestOpts.isPresent() ? requestOpts.get() : new HashMap<>()).build();
	}
}
