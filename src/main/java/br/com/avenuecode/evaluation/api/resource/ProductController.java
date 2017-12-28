package br.com.avenuecode.evaluation.api.resource;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.stereotype.Component;

import br.com.avenuecode.evaluation.api.service.ProductService;
import br.com.avenuecode.evaluation.api.to.ProductTo;

@Component
@Produces(MediaType.APPLICATION_JSON)
@Path(value = "/products")
public class ProductController {

	
	@Inject()
	private ProductService productService;

	// Get all excluding relationships
	@GET
	public Response getProducts() {
		List<ProductTo> products = productService.findAllWithoutRelationships();
		return response(products);
	}

	// Get all including specified relationships (products)
	@GET
	@Path("/product")
	public Response findAllFetchProductsTo() {
		List<ProductTo> products = productService.findAllFetchProductTo();
		return response(products);
	}
	
	// Get all including specified relationships (images)
	@GET
	@Path("/image")
	public Response findAllFetchImage() {
		List<ProductTo> products = productService.findAllFetchImage();
		return response(products);
	}

	// Get all including specified relationships (products and/or images)
	@GET
	@Path("/product-and-or-image")
	public Response findAllFetchProductsAndOrImages() {
		List<ProductTo> products = productService.findAllFetchProductAndOrImage();
		return response(products);
	}
	
	// Get one excluding relationships
	@GET
	@Path("/{productId}")
	public Response geOnetWithoutRelationships(@PathParam ("productId") Long productId) {
		ProductTo product = productService.geOnetWithoutRelationship(productId);
		return response(product);
	}

	// Get one including relationships (products)
	@GET
	@Path("/{productId}/product")
	public Response getFetchProducts(@PathParam("productId") Long productId) {
		ProductTo productTo = productService.getOneFetchProduct(productId);
		return response(productTo);
	}
	
	// Get one including relationships (images)
	@GET
	@Path("/{productId}/image")
	public Response getOneFetchImage(@PathParam("productId") Long productId) {
		ProductTo productTo = productService.getOneFetchImage(productId);
		return response(productTo);
	}
	
	// Get one including relationships (products and/or images)
	@GET
	@Path("/{productId}/product-and-or-image")
	public Response getOneFetchProductsAndOrImages(@PathParam ("productId") Long productId) {
		ProductTo product = productService.getOneContainsProductAndOrImage(productId);
		return response(product);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response save(ProductTo productTo) {
		productService.save(productTo);
		return response(productTo);
	}
	
	@DELETE
	@Path("/{productId}")
	public Response delete(@PathParam("productId") Long productId){
		if (productService.getOne(productId) == null) {
			return Response.status(Status.NO_CONTENT).build();
		}
		productService.delete(productId);
		return Response.status(Status.ACCEPTED).build();
	}
	
	
	
	private Response response(ProductTo productTo) {
		if (productTo == null) {
			return Response.status(Status.NO_CONTENT).build();
		}
		return Response.ok(productTo).build();
	}
	
	private Response response(List<ProductTo> productsTo) {
		if (productsTo == null || productsTo.isEmpty()) {
			return Response.status(Status.NO_CONTENT).build();
		}
		return Response.ok(productsTo).build();
	}

}
