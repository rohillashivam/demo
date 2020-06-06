package com.sapient.command.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.sapient.command.executor.http.HttpCommandExecutor;
import com.sapient.entity.HTTPRequestEntity;
import com.sapient.entity.HttpResponseEntity;

/**
 * 
 * @author shivam.rohilla
 *
 */
public class RestUtils {

	private static RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(HttpCommandExecutor.class);
    private static int TIME_OUT = 30;
    private static int HTTP_OK = 200;

	static {
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setConnectTimeout(1000 * TIME_OUT);
        httpRequestFactory.setReadTimeout(1000 * TIME_OUT);
        httpRequestFactory.setConnectionRequestTimeout(1000 * TIME_OUT);
        restTemplate = new RestTemplate(httpRequestFactory);
    }
	
	public static List<HttpResponseEntity> executeGetRequest(List<HTTPRequestEntity> requestList) throws IOException {
		if(requestList == null || requestList.isEmpty())
			return null;
		List<HttpResponseEntity> httpResponseEntityList = new ArrayList<>();
		for (HTTPRequestEntity request : requestList) {
			logger.info("Inside HTTP get ::" + request.getUrl());
			String responseObject = null;
	        try {
	            if (StringUtils.isEmpty(request.getUrl())) {
	                logger.error("URL is null/empty, cannot get the http connection");
	            }

	            String url = buildUrl(request.getUrl(), request.getRequestParam());
	            responseObject = restTemplate.getForObject(url, String.class ,request.getRequestParam());
	            if (responseObject != null) {
	                logger.info("Response object is " + responseObject);
	            } else {
	                logger.error("Null response from :" + request.getUrl());
	            }
	        } catch (Exception e) {
	            logger.error("Exception while calling api ::" + request.getUrl(), e);
	        }

	        logger.info("End HTTP get ::" + responseObject);
	        httpResponseEntityList.add(HttpResponseEntity.builder().responseCode(HTTP_OK).response(responseObject).build());
		}
		return httpResponseEntityList;
	}

	private static String buildUrl(String url, Map<String, String> requestParam) {
		boolean flag = false;
		StringBuilder sb = new StringBuilder(url);
		Iterator it = requestParam.entrySet().iterator();
		while(it.hasNext()) {
			if(!flag) {
				sb.append("?");
				flag = true;
			} else {
				sb.append("&");
			}
	        Map.Entry pair = (Map.Entry)it.next();
	        sb.append(pair.getKey()+"="+pair.getValue());

		}
		return sb.toString();
	}
}
