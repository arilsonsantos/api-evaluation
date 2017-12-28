package br.com.avenuecode.evaluation.helper;

import java.util.List;
import java.util.Map;

import br.com.avenuecode.evaluation.api.model.Image;
import br.com.avenuecode.evaluation.api.model.Product;
import br.com.avenuecode.evaluation.api.to.ImageTo;
import br.com.avenuecode.evaluation.api.to.ProductTo;

public class ProductHelper {

	public static Product convertProductToWithImageToProduct(ProductTo productTo) {
		Product product = new Product();
		product.setId(productTo.getId());
		product.setName(productTo.getName());
		product.setDescription(productTo.getDescription());

		if (productTo.getParentProductId() != null) {
			product.setParentProduct(new Product(productTo.getParentProductId()));
		}

		productTo.getImages().forEach(img -> {
			Image image = new Image();
			image.setId(img.getId());
			image.setType(img.getType());
			image.setProduct(product);
			product.addImage(image);
		});

		return product;
	}

	public static ProductTo convertProductWithImageToProductTo(Product product) {
		ProductTo productTo = new ProductTo();

		fillProductTo(product, productTo);

		if (product.getParentProduct() != null && product.getParentProduct().getId() != null) {
			productTo.setParentProduct(new ProductTo(productTo.getParentProductId()));
		}

		product.getImages().forEach(img -> {
			ImageTo imageTo = new ImageTo();
			imageTo.setId(img.getId());
			imageTo.setType(img.getType());
			imageTo.setProductTo(productTo);
			productTo.add(imageTo);
		});

		return productTo;
	}

	public static ProductTo convertProductWithoutRelationshipToProductTo(Product product) {
		ProductTo productTo = new ProductTo();

		fillProductTo(product, productTo);

		return productTo;
	}
	
	public static ProductTo convertProductWithRelationshipToProductTo(Product product) {
		ProductTo productTo = new ProductTo();

		fillProductTo(product, productTo);

		return productTo;
	}

	private static void fillProductTo(Product product, ProductTo productTo) {
		productTo.setId(product.getId());
		productTo.setName(product.getName());
		productTo.setDescription(product.getDescription());
	}
	
	public static void createStructure(List<ProductTo> produtosTo, Map<Long, ProductTo> mapChildParent, Product product,
			ProductTo productTo) {
		
		if (product.getParentProduct() == null) {
			produtosTo.add(productTo);
		} else {
			productTo.setParentProductId(product.getParentProductId());
			if (product.getProducts() != null && !mapChildParent.containsKey(productTo.getId())) {
				mapChildParent.get(product.getParentProductId()).add(productTo);
			}
		}

		mapChildParent.put(productTo.getId(), productTo);
	}
	
}
