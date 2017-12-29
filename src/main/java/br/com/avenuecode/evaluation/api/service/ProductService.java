package br.com.avenuecode.evaluation.api.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.avenuecode.evaluation.api.model.Image;
import br.com.avenuecode.evaluation.api.model.Product;
import br.com.avenuecode.evaluation.api.repository.ImageRepository;
import br.com.avenuecode.evaluation.api.repository.ProductRepository;
import br.com.avenuecode.evaluation.api.to.ProductTo;
import br.com.avenuecode.evaluation.helper.ImageHelper;
import br.com.avenuecode.evaluation.helper.ProductHelper;

@Component
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ImageRepository imageRepository;

	public List<ProductTo> findAllWithoutRelationships() {
		List<Product> products = productRepository.findAllWithoutRelationship();
		List<ProductTo> productsTo = new ArrayList<>();

		products.forEach(p -> {
			ProductTo productTo = ProductHelper.convertProductWithoutRelationshipToProductTo(p);
			productsTo.add(productTo);
		});

		return productsTo;
	}

	public List<ProductTo> findAllFetchProductTo() {
		List<Product> products = productRepository.findAllFetchProduct();
		List<ProductTo> productsTo = new ArrayList<>();
		Map<Long, ProductTo> map = new HashMap<>();

		products.forEach(p -> {
			ProductTo productTo = ProductHelper.convertProductWithoutRelationshipToProductTo(p);
			ProductHelper.createStructure(productsTo, map, p, productTo);
		});

		return productsTo;
	}

	public List<ProductTo> findAllFetchImage() {
		List<Product> products = productRepository.findAllFetchImage();
		List<ProductTo> productsTo = new ArrayList<>();

		products.forEach(p -> {
			ProductTo productTo = ProductHelper.convertProductWithImageToProductTo(p);
			productsTo.add(productTo);
		});

		return productsTo;
	}

	public ProductTo geOnetWithoutRelationship(Long productId) {
		Product product = productRepository.geOnetWithoutRelationship(productId);

		if (product == null) {
			return null;
		}

		ProductTo productTo = ProductHelper.convertProductWithoutRelationshipToProductTo(product);

		return productTo;
	}

	public ProductTo getOneFetchImage(Long productId) {
		Product product = productRepository.getOneFetchImage(productId);

		if (product == null) {
			return null;
		}

		ProductTo productTo = ProductHelper.convertProductWithImageToProductTo(product);

		return productTo;
	}

	public ProductTo getOneFetchProduct(Long productId) {
		List<Product> products = productRepository.getOneFetchProduct(productId);
		List<ProductTo> productsTo = new ArrayList<>();
		Map<Long, ProductTo> mapChildParent = new HashMap<>();
		
		products.forEach(product -> {
			ProductTo productTo = ProductHelper.convertProductWithoutRelationshipToProductTo(product);
			ProductHelper.createStructure(productsTo, mapChildParent, product, productTo);
		});

		return !productsTo.isEmpty() ? productsTo.get(0) : null;
	}

	public ProductTo getOneContainsProductAndOrImage(Long productId) {
		List<Product> products = productRepository.getOneContainsProductAndOrImage(productId);
		List<ProductTo> productsTo = containsProductAndOrImage(products);

		return !productsTo.isEmpty() ? productsTo.get(0) : null;
	}

	public List<ProductTo> findAllFetchProductAndOrImage() {
		List<Product> products = productRepository.findAllFetchProductAndOrImage();
		List<ProductTo> productsTo = containsProductAndOrImage(products);

		return productsTo;
	}

	
	public Product save(ProductTo productTo) throws Exception {
		Product product = ProductHelper.convertProductToWithImageToProduct(productTo);
		
		productRepository.save(product);
		
		return product;
	}

	public void delete(Long productId) {
		productRepository.delete(productId);
	}

	public Product getOne(Long productId) {
		Product product = productRepository.geOnetWithoutRelationship(productId);

		if (product == null) {
			return null;
		}
		return product;
	}
	
	private List<ProductTo> containsProductAndOrImage(List<Product> products) {
		List<ProductTo> productsTo = new ArrayList<>();
		Map<Long, ProductTo> mapChildParent = new HashMap<>();

		products.forEach(product -> {
			ProductTo productTo = ProductHelper.convertProductWithoutRelationshipToProductTo(product);
			fillImageTo(productTo);
			ProductHelper.createStructure(productsTo, mapChildParent, product, productTo);
		});
		return productsTo;
	}

	private void fillImageTo(ProductTo productTo) {
		List<Image> images = imageRepository.findByProductId(productTo.getId());

		if (images != null) {
			ImageHelper.convertImageToImageTo(productTo, images);
		}
	}

}
