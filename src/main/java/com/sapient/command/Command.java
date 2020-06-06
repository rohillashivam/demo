package com.sapient.command;

import java.io.IOException;

/**
 * 
 * @author shivam.rohilla
 *
 * @param <Request>
 * @param <Response>
 */
public interface Command<Request, Response> {

	public Response executeCommand() throws IOException;

}
