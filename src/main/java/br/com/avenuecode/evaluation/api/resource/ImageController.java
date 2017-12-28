package br.com.avenuecode.evaluation.api.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.avenuecode.evaluation.api.service.ImageService;
import br.com.avenuecode.evaluation.api.to.ImageTo;

@Component
@Produces(MediaType.APPLICATION_JSON)
@Path(value = "/images")
public class ImageController {

	@Autowired
	ImageService imageService;
	
	@DELETE
	@Path("/{imageId}")
	public Response delete(@PathParam("imageId") Long imageId){
		if (imageService.getOne(imageId) == null) {
			return Response.status(Status.NO_CONTENT).build();
		}
		imageService.delete(imageId);
		return Response.status(Status.ACCEPTED).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response save(ImageTo imageTo) {
		imageService.save(imageTo);
		return response(imageTo);
	}
	
	private Response response(ImageTo imageTo) {
		if (imageTo == null) {
			return Response.status(Status.NO_CONTENT).build();
		}
		return Response.ok(imageTo).build();
	}
	
}
