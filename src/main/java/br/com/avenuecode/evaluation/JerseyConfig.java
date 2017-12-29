package br.com.avenuecode.evaluation;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.avenuecode.evaluation.message.GenericExceptionMapper;

@Component
@ApplicationPath("/api/evaluation")
public class JerseyConfig extends ResourceConfig {

	@Autowired
	public JerseyConfig(ObjectMapper objectMapper) {
		packages("br.com.avenuecode.evaluation.api.resource");
		register(GenericExceptionMapper.class);
	}

}