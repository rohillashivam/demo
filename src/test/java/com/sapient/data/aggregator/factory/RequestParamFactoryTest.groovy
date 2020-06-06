package com.sapient.data.aggregator.factory

import static org.junit.jupiter.api.Assertions.assertTrue

import com.sapient.data.aggregator.request.RequestOps

import spock.lang.Specification

class RequestParamFactoryTest extends Specification {
	
	def "getRequestParamMap"() {
		given:
			String apiKey = "test"
			String id = 3
	when:
		Optional<Map<String, String>> optMap = RequestParamFactory.getRequestParamMap(RequestOps.COUNTRY, apiKey, id)
	then:
		assertTrue(optMap.isPresent())
	}
}
