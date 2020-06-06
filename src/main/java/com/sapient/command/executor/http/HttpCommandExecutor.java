package com.sapient.command.executor.http;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sapient.command.Command;
import com.sapient.command.executor.CommandExecutor;
import com.sapient.entity.HTTPRequestEntity;
import com.sapient.entity.HttpResponseEntity;

/**
 * 
 * @author shivam.rohilla
 *
 */
@Service
public class HttpCommandExecutor implements CommandExecutor<HTTPRequestEntity, List<HttpResponseEntity>> {

	@Override
	public List<HttpResponseEntity> executeComamnd(Command command) throws IOException {
		return (List<HttpResponseEntity>) command.executeCommand();
	}

}
