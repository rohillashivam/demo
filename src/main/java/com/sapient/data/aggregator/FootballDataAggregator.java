package com.sapient.data.aggregator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sapient.command.executor.http.HttpCommandExecutor;
import com.sapient.command.http.HttpCommand;
import com.sapient.data.aggregator.facade.exception.DataUpdateException;
import com.sapient.data.aggregator.orchestrator.DataAggregatorOrchestrator;
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

@Service
public class FootballDataAggregator {

	@Autowired
	private DataAggregatorOrchestrator dataAggregatorOrchestrator;
	
	@Autowired
	private HttpCommandExecutor commandExecutor;
	
	@Value("${football.url}")
	private String footballUrl;

	@Value("${api.key}")
	private String apiKey;

	private static final Logger log = LoggerFactory.getLogger(FootballDataAggregator.class);

	@PostConstruct
	public void aggregateData() {
		log.error("in aggregateData START");
		try {
			retrieveData();
		} catch (DataUpdateException e) {
			log.error("failed to update the data store");
			// TODO exit or continue to be decided
		}
	}
	
	public void retrieveData() throws DataUpdateException {
		dataAggregatorOrchestrator.orchestrateDataAggregation(footballUrl, apiKey, executeRequest());
	}

	private Function<HTTPRequestEntity, List<HttpResponseEntity>> executeRequest() {
		return (request) -> {
			List<HTTPRequestEntity> requestList = new ArrayList<>();
			requestList.add(request);
			try {
				return commandExecutor
						.executeComamnd(HttpCommand.builder().request(requestList).build());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		};
	}
}
