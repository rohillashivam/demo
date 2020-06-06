package com.sapient.command.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author shivam.rohilla
 *
 */
public class JsonUtils {

	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	private static final Logger log = LoggerFactory.getLogger(JsonUtils.class);
	
	public static Object deserialize(String str, Class klass) {
		try {
			return objectMapper.readValue(str, klass);
		} catch (JsonProcessingException e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	public static Object deserialize(String str, TypeReference typeReference) {
		try {
			return objectMapper.readValue(str, typeReference);
		} catch (JsonProcessingException e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
}
