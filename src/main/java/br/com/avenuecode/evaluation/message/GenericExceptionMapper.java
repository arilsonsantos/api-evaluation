package br.com.avenuecode.evaluation.message;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

	@Override
	public Response toResponse(ConstraintViolationException violations) {

		List<String> errors = new ArrayList<>();
		violations.getConstraintViolations().stream().forEach(e -> errors.add(e.getMessage()));

		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setErrors(errors);

		return Response.ok().status(Status.NOT_ACCEPTABLE).entity(errorMessage).build();

	}

}
