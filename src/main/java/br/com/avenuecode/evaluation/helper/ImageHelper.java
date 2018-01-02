package br.com.avenuecode.evaluation.helper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.avenuecode.evaluation.api.model.Image;
import br.com.avenuecode.evaluation.api.model.Product;
import br.com.avenuecode.evaluation.api.to.ImageTo;
import br.com.avenuecode.evaluation.api.to.ProductTo;

public class ImageHelper {

	public static ProductTo convertImageToImageTo(ProductTo productTo, List<Image> images) {
		Set<ImageTo> imagesTo = new HashSet<>();

		for (Image img : images) {
			ImageTo imageTo = new ImageTo();
			imageTo.setId(img.getId());
			imageTo.setType(img.getType());
			imageTo.setProductTo(productTo);
			imagesTo.add(imageTo);
		}
		productTo.setImages(imagesTo);
		
		return productTo;
	}
	
	public static Image convertImageToImage(ImageTo imageTo) {
		Image image = new Image();
		image.setId(imageTo.getId());
		image.setType(imageTo.getType());
		image.setProduct(new Product(imageTo.getProductTo().getId()));

		return image;
	}
	
	public static ImageTo convertImageToImageToWithoutReletionship(Image image) {
		ImageTo imageTo = new ImageTo();
		imageTo.setId(image.getId());
		imageTo.setType(image.getType());
		
		return imageTo;
	}
	
	public static ImageTo convertImageToImageToWithProduct(Image image) {
		ImageTo imageTo = new ImageTo();
		imageTo.setId(image.getId());
		imageTo.setType(image.getType());
		
		ProductTo productTo = ProductHelper.convertProductWithImageToProductTo(image.getProduct());
		imageTo.setProductTo(productTo);

		return imageTo;
	}
	
	
	
}
