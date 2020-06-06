package com.sapient.data.aggregator.orchestrator;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sapient.command.executor.http.HttpCommandExecutor;
import com.sapient.data.aggregator.facade.DataAggregatorFacade;
import com.sapient.data.aggregator.facade.exception.DataUpdateException;
import com.sapient.data.aggregator.processor.country.CountryProcessor;
import com.sapient.data.aggregator.processor.league.LeagueProcessor;
import com.sapient.data.aggregator.processor.standing.StandingAggregatorProcessor;
import com.sapient.entity.HTTPRequestEntity;
import com.sapient.entity.HttpResponseEntity;
import com.sapient.entity.football.Country;
import com.sapient.entity.football.League;
import com.sapient.entity.football.TeamStandingAggregateView;

/**
 * 
 * @author shivam.rohilla
 *
 */
@Component
public class DataAggregatorOrchestrator {

	@Autowired
	private StandingAggregatorProcessor standingAggregatorProcessor;
	
	@Autowired
	private LeagueProcessor leagueProcessor;
	
	@Autowired
	private CountryProcessor countryProcessor;
	
	@Autowired
	private DataAggregatorFacade dataAggregatorFacade;
	
	public void orchestrateDataAggregation(String url, String apiKey, Function<HTTPRequestEntity, List<HttpResponseEntity>> executeRequest ) throws DataUpdateException {
		List<Country> countryList;
		try {
			countryList = countryProcessor.process(url, apiKey, executeRequest);
			List<League> leagueList = leagueProcessor.process(countryList, url, apiKey, executeRequest);
			List<TeamStandingAggregateView> teamStandingAggViewList = standingAggregatorProcessor.process(leagueList, url, apiKey, executeRequest);
			dataAggregatorFacade.updateAggregate(teamStandingAggViewList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
