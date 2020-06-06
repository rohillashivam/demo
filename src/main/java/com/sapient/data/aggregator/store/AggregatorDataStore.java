package com.sapient.data.aggregator.store;

import java.util.List;

import com.sapient.entity.football.TeamStandingAggregateView;

/**
 * 
 * @author shivam.rohilla
 *
 */
public class AggregatorDataStore {

	private static List<TeamStandingAggregateView> teamStandingAggregateViewList;
	
	public static void updateList(List<TeamStandingAggregateView> teamStandingAggregateViewList) {
		AggregatorDataStore.teamStandingAggregateViewList = teamStandingAggregateViewList;
	}
	
	public static List<TeamStandingAggregateView> getTeamStandingAggregateViewList() {
		return teamStandingAggregateViewList;
	}
	
}
