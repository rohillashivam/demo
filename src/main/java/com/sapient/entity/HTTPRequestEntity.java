package com.sapient.entity;

import java.util.Map;

import lombok.Builder;
import lombok.Data;

/**
 * 
 * @author shivam.rohilla
 *
 */
@Data
@Builder
public class HTTPRequestEntity {

	private String url;
	private String methodType;
	private Map<String, String> requestParam;
	private String requestBody;
}
