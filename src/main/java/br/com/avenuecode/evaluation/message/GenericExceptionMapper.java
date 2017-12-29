package br.com.avenuecode.evaluation.message;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.com.avenuecode.evaluation.api.to.ProductTo;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

	@Override
	public Response toResponse(ConstraintViolationException violations) {

		List<String> errors = new ArrayList<>();
		violations.getConstraintViolations().stream().forEach(e -> errors.add(e.getMessage()));

		Object object = violations.getConstraintViolations().iterator().next().getLeafBean();

		if (!errors.isEmpty()) {

			if (object instanceof ProductTo) {
				ProductMessage productMessage = new ProductMessage();
				productMessage.setProductTo((ProductTo) object);
				productMessage.setErrors(errors);

				return Response.ok().status(Status.NOT_ACCEPTABLE).entity(productMessage).build();
			}

		}

		return Response.ok().status(Status.CREATED).build();
	}

}
