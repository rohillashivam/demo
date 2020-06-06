package com.sapient.entity;

import lombok.Builder;
import lombok.Data;

/**
 * 
 * @author shivam.rohilla
 *
 * @param <Type>
 */
@Data
@Builder
public class HttpResponseEntity<Type> {

	private Type response;
	
	private Integer responseCode;
}
