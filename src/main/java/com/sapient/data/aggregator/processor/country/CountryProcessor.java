package com.sapient.data.aggregator.processor.country;

import java.io.IOException;
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

/**
 * 
 * @author shivam.rohilla
 *
 */
@Component
public class CountryProcessor {
	
	public List<Country> process(String url, String apiKey, Function<HTTPRequestEntity, List<HttpResponseEntity>> executeRequest) throws IOException {
		HTTPRequestEntity countryRequest = createCountryRequest(url, apiKey);
		List<Country> countryList = null;
		List<HttpResponseEntity> countryResponse = null;
		countryResponse = executeRequest.apply(countryRequest);
		for (HttpResponseEntity httpResponseEntity : countryResponse) {
			if(httpResponseEntity.getResponseCode() != 200)
				continue;
			countryList = (List<Country>) JsonUtils.deserialize(String.valueOf(httpResponseEntity.getResponse()), new TypeReference<List<Country>>(){});
		}
		return countryList;
	}
	
	private HTTPRequestEntity createCountryRequest(String url, String apiKey) {
		Optional<Map<String, String>> requestOpts = RequestParamFactory.getRequestParamMap(RequestOps.COUNTRY, apiKey, null);
		return HTTPRequestEntity.builder().methodType("GET").url(url).requestParam(requestOpts.isPresent() ? requestOpts.get() : new HashMap<>()).build();
	}
}
