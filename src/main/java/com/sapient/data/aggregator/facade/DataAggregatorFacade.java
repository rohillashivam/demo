package com.sapient.data.aggregator.facade;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sapient.data.aggregator.facade.exception.DataUpdateException;
import com.sapient.data.aggregator.store.AggregatorDataStore;
import com.sapient.entity.football.TeamStandingAggregateView;

/**
 * 
 * @author shivam.rohilla
 *
 */
@Repository
public class DataAggregatorFacade {

	public List<TeamStandingAggregateView> getAggregateView() {
		return AggregatorDataStore.getTeamStandingAggregateViewList();
	}
	
	public void updateAggregate(List<TeamStandingAggregateView> teamStandingAggregateViewList) throws DataUpdateException {
		if(teamStandingAggregateViewList == null || teamStandingAggregateViewList.isEmpty()) {
			throw new DataUpdateException("empty data");
		}
		AggregatorDataStore.updateList(teamStandingAggregateViewList);
	}
}
