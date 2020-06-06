package com.sapient.command.executor;

import java.io.IOException;
import java.net.MalformedURLException;

import com.sapient.command.Command;

/**
 * 
 * @author shivam.rohilla
 *
 * @param <Request>
 * @param <Response>
 */
public interface CommandExecutor<Request, Response> {

	public Response executeComamnd(Command command) throws MalformedURLException, IOException;
	
}
