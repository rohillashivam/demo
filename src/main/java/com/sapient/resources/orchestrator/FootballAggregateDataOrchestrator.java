package com.sapient.resources.orchestrator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.data.aggregator.facade.DataAggregatorFacade;
import com.sapient.entity.football.TeamStandingAggregateView;

/**
 * 
 * @author shivam.rohilla
 *
 */
@Service
public class FootballAggregateDataOrchestrator {

	@Autowired
	private DataAggregatorFacade dataAggregationFacade;
	
	public List<TeamStandingAggregateView> orchestrate() {
		return dataAggregationFacade.getAggregateView();
	}
}
