package com.sapient.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.entity.football.TeamStandingAggregateView;
import com.sapient.resources.orchestrator.FootballAggregateDataOrchestrator;

/**
 * 
 * @author shivam.rohilla
 *
 */
@RestController
@RequestMapping("/football")
public class FootballAggregateResource {

	@Autowired
	private FootballAggregateDataOrchestrator footballAggregateDataOrchestrator;
	
	@GetMapping("/v1/team/league/stands")
	public List<TeamStandingAggregateView> getTeamStandingAggregates() {
		return footballAggregateDataOrchestrator.orchestrate();
	}
}
