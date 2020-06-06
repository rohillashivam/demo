package com.sapient.command.http;

import java.io.IOException;
import java.util.List;

import com.sapient.command.Command;
import com.sapient.command.utils.RestUtils;
import com.sapient.entity.HTTPRequestEntity;
import com.sapient.entity.HttpResponseEntity;

import lombok.Builder;

/**
 * 
 * @author shivam.rohilla
 *
 */

@Builder
public class HttpCommand implements Command<HTTPRequestEntity, List<HttpResponseEntity>> {
	
	private List<HTTPRequestEntity> request = null;
	
	@Override
	public List<HttpResponseEntity> executeCommand() throws IOException {
		return RestUtils.executeGetRequest(request);
	}

}
