package com.sapient.data.aggregator.facade

import static org.junit.Assert.assertNotNull
import static org.junit.Assert.assertNull

import com.fasterxml.jackson.core.type.TypeReference
import com.sapient.command.utils.JsonUtils
import com.sapient.data.aggregator.store.AggregatorDataStore
import com.sapient.entity.football.TeamStandingAggregateView

import spock.lang.Specification

/**
 * 
 * @author shivam.rohilla
 *
 */
class DataAggregatorFacadeTest extends Specification {

	private AggregatorDataStore AggregatorDataStore = Mock();
	
	def "get aggregations"() {
		given:
			DataAggregatorFacade dataAggFacade = new DataAggregatorFacade()
			//String response = "[{\"countryId\":null,\"countryName\":\"England\",\"leagueId\":149,\"leagueName\":\"Championship\",\"teamId\":2653,\"teamName\":\"Leeds\",\"standing\":1},{\"countryId\":null,\"countryName\":\"England\",\"leagueId\":149,\"leagueName\":\"Championship\",\"teamId\":2625,\"teamName\":\"West Brom\",\"standing\":2},{\"countryId\":null,\"countryName\":\"England\",\"leagueId\":149,\"leagueName\":\"Championship\",\"teamId\":2639,\"teamName\":\"Fulham\",\"standing\":3},{\"countryId\":null,\"countryName\":\"England\",\"leagueId\":149,\"leagueName\":\"Championship\",\"teamId\":2644,\"teamName\":\"Brentford\",\"standing\":4},{\"countryId\":null,\"countryName\":\"England\",\"leagueId\":149,\"leagueName\":\"Championship\",\"teamId\":2634,\"teamName\":\"Nottingham\",\"standing\":5},{\"countryId\":null,\"countryName\":\"England\",\"leagueId\":149,\"leagueName\":\"Championship\",\"teamId\":2643,\"teamName\":\"Preston\",\"standing\":6},{\"countryId\":null,\"countryName\":\"England\",\"leagueId\":149,\"leagueName\":\"Championship\",\"teamId\":2650,\"teamName\":\"Bristol City\",\"standing\":7},{\"countryId\":null,\"countryName\":\"England\",\"leagueId\":149,\"leagueName\":\"Championship\",\"teamId\":2638,\"teamName\":\"Millwall\",\"standing\":8},{\"countryId\":null,\"countryName\":\"England\",\"leagueId\":149,\"leagueName\":\"Championship\",\"teamId\":2637,\"teamName\":\"Cardiff\",\"standing\":9},{\"countryId\":null,\"countryName\":\"England\",\"leagueId\":149,\"leagueName\":\"Championship\",\"teamId\":2680,\"teamName\":\"Blackburn\",\"standing\":10},{\"countryId\":null,\"countryName\":\"England\",\"leagueId\":149,\"leagueName\":\"Championship\",\"teamId\":2618,\"teamName\":\"Swansea\",\"standing\":11},{\"countryId\":null,\"countryName\":\"England\",\"leagueId\":149,\"leagueName\":\"Championship\",\"teamId\":2642,\"teamName\":\"Derby\",\"standing\":12},{\"countryId\":null,\"countryName\":\"England\",\"leagueId\":149,\"leagueName\":\"Championship\",\"teamId\":2645,\"teamName\":\"QPR\",\"standing\":13},{\"countryId\":null,\"countryName\":\"England\",\"leagueId\":149,\"leagueName\":\"Championship\",\"teamId\":2647,\"teamName\":\"Reading\",\"standing\":14},{\"countryId\":null,\"countryName\":\"England\",\"leagueId\":149,\"leagueName\":\"Championship\",\"teamId\":2651,\"teamName\":\"Sheffield Wed\",\"standing\":15},{\"countryId\":null,\"countryName\":\"England\",\"leagueId\":149,\"leagueName\":\"Championship\",\"teamId\":2631,\"teamName\":\"Birmingham\",\"standing\":16},{\"countryId\":null,\"countryName\":\"England\",\"leagueId\":149,\"leagueName\":\"Championship\",\"teamId\":2624,\"teamName\":\"Stoke\",\"standing\":17},{\"countryId\":null,\"countryName\":\"England\",\"leagueId\":149,\"leagueName\":\"Championship\",\"teamId\":2622,\"teamName\":\"Huddersfield\",\"standing\":18},{\"countryId\":null,\"countryName\":\"England\",\"leagueId\":149,\"leagueName\":\"Championship\",\"teamId\":2648,\"teamName\":\"Middlesbrough\",\"standing\":19},{\"countryId\":null,\"countryName\":\"England\",\"leagueId\":149,\"leagueName\":\"Championship\",\"teamId\":2679,\"teamName\":\"Wigan\",\"standing\":20},{\"countryId\":null,\"countryName\":\"England\",\"leagueId\":149,\"leagueName\":\"Championship\",\"teamId\":2633,\"teamName\":\"Hull\",\"standing\":21},{\"countryId\":null,\"countryName\":\"England\",\"leagueId\":149,\"leagueName\":\"Championship\",\"teamId\":2663,\"teamName\":\"Charlton\",\"standing\":22},{\"countryId\":null,\"countryName\":\"England\",\"leagueId\":149,\"leagueName\":\"Championship\",\"teamId\":2695,\"teamName\":\"Luton\",\"standing\":23},{\"countryId\":null,\"countryName\":\"England\",\"leagueId\":149,\"leagueName\":\"Championship\",\"teamId\":2652,\"teamName\":\"Barnsley\",\"standing\":24},{\"countryId\":null,\"countryName\":\"France\",\"leagueId\":177,\"leagueName\":\"Ligue 2\",\"teamId\":3044,\"teamName\":\"Lorient\",\"standing\":1},{\"countryId\":null,\"countryName\":\"France\",\"leagueId\":177,\"leagueName\":\"Ligue 2\",\"teamId\":3060,\"teamName\":\"Lens\",\"standing\":2},{\"countryId\":null,\"countryName\":\"France\",\"leagueId\":177,\"leagueName\":\"Ligue 2\",\"teamId\":3043,\"teamName\":\"AC Ajaccio\",\"standing\":3},{\"countryId\":null,\"countryName\":\"France\",\"leagueId\":177,\"leagueName\":\"Ligue 2\",\"teamId\":3029,\"teamName\":\"Troyes\",\"standing\":4},{\"countryId\":null,\"countryName\":\"France\",\"leagueId\":177,\"leagueName\":\"Ligue 2\",\"teamId\":3042,\"teamName\":\"Clermont\",\"standing\":5},{\"countryId\":null,\"countryName\":\"France\",\"leagueId\":177,\"leagueName\":\"Ligue 2\",\"teamId\":3057,\"teamName\":\"Le Havre\",\"standing\":6},{\"countryId\":null,\"countryName\":\"France\",\"leagueId\":177,\"leagueName\":\"Ligue 2\",\"teamId\":3048,\"teamName\":\"Valenciennes\",\"standing\":7},{\"countryId\":null,\"countryName\":\"France\",\"leagueId\":177,\"leagueName\":\"Ligue 2\",\"teamId\":3032,\"teamName\":\"Guingamp\",\"standing\":8},{\"countryId\":null,\"countryName\":\"France\",\"leagueId\":177,\"leagueName\":\"Ligue 2\",\"teamId\":3078,\"teamName\":\"Grenoble\",\"standing\":9},{\"countryId\":null,\"countryName\":\"France\",\"leagueId\":177,\"leagueName\":\"Ligue 2\",\"teamId\":3071,\"teamName\":\"Chambly\",\"standing\":10},{\"countryId\":null,\"countryName\":\"France\",\"leagueId\":177,\"leagueName\":\"Ligue 2\",\"teamId\":3051,\"teamName\":\"Auxerre\",\"standing\":11},{\"countryId\":null,\"countryName\":\"France\",\"leagueId\":177,\"leagueName\":\"Ligue 2\",\"teamId\":3047,\"teamName\":\"Nancy\",\"standing\":12},{\"countryId\":null,\"countryName\":\"France\",\"leagueId\":177,\"leagueName\":\"Ligue 2\",\"teamId\":3028,\"teamName\":\"Caen\",\"standing\":13},{\"countryId\":null,\"countryName\":\"France\",\"leagueId\":177,\"leagueName\":\"Ligue 2\",\"teamId\":3058,\"teamName\":\"Sochaux\",\"standing\":14},{\"countryId\":null,\"countryName\":\"France\",\"leagueId\":177,\"leagueName\":\"Ligue 2\",\"teamId\":3061,\"teamName\":\"Chateauroux\",\"standing\":15},{\"countryId\":null,\"countryName\":\"France\",\"leagueId\":177,\"leagueName\":\"Ligue 2\",\"teamId\":3065,\"teamName\":\"Rodez\",\"standing\":16},{\"countryId\":null,\"countryName\":\"France\",\"leagueId\":177,\"leagueName\":\"Ligue 2\",\"teamId\":3055,\"teamName\":\"Paris FC\",\"standing\":17},{\"countryId\":null,\"countryName\":\"France\",\"leagueId\":177,\"leagueName\":\"Ligue 2\",\"teamId\":3059,\"teamName\":\"Niort\",\"standing\":18},{\"countryId\":null,\"countryName\":\"France\",\"leagueId\":177,\"leagueName\":\"Ligue 2\",\"teamId\":3133,\"teamName\":\"Le Mans\",\"standing\":19},{\"countryId\":null,\"countryName\":\"France\",\"leagueId\":177,\"leagueName\":\"Ligue 2\",\"teamId\":3053,\"teamName\":\"Orleans\",\"standing\":20}]";
			TeamStandingAggregateView teamStandingAggregateView = TeamStandingAggregateView.builder().countryName("England").teamId(2653).build();
			//List<TeamStandingAggregateView> teamStandAggView= JsonUtils.
			//		deserialize(response, new TypeReference<List<TeamStandingAggregateView>>(){})
			List<TeamStandingAggregateView> teamStandAggView = new ArrayList()
			teamStandAggView.add(teamStandingAggregateView)
			AggregatorDataStore.getTeamStandingAggregateViewList() >> teamStandAggView
		
		when:
			List<TeamStandingAggregateView> teamStandAggViewObj = dataAggFacade.getAggregateView()
		then:
			assertNull(teamStandAggViewObj)
	}
	
}