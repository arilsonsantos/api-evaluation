package br.com.avenuecode.evaluation.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.avenuecode.evaluation.api.model.Image;
import br.com.avenuecode.evaluation.api.model.Product;
import br.com.avenuecode.evaluation.api.repository.ImageRepository;
import br.com.avenuecode.evaluation.api.to.ImageTo;
import br.com.avenuecode.evaluation.api.to.ProductTo;
import br.com.avenuecode.evaluation.helper.ImageHelper;
import br.com.avenuecode.evaluation.message.MessageResponse;

@Component
public class ImageService {

	@Autowired
	ImageRepository imageRepository;

	@Autowired
	ProductService productService;

	public Image getOne(Long imageId) {
		Image image = imageRepository.getOne(imageId);

		if (image == null) {
			return null;
		}
		return image;
	}

	public void save(ImageTo imageTo) {
		Image image = ImageHelper.convertImageToImage(imageTo);
		imageRepository.save(image);
	}

	// TODO Add response
	public MessageResponse save(Long productId, List<ImageTo> imagesTo) {
		Product product = productService.getOne(productId);
		
		List<Image> images = new ArrayList<>();
		
		MessageResponse productMessage = new MessageResponse(productId, null);
		
		if (product != null) {
			for (ImageTo imageTo : imagesTo) {
				imageTo.setProductTo(new ProductTo(productId));
				Image image = ImageHelper.convertImageToImage(imageTo);
				image.setProduct(product);
				images.add(image);
			}

			imageRepository.save(images);
			
			ProductTo productTo = productService.getOneFetchImage(productId);
			
			productMessage.setImagesTo(productTo.getImages());
		}
		
		return productMessage;
	}

	public void delete(Long imageId) {
		imageRepository.delete(imageId);
	}
	
	public void delete(Product product) {
		imageRepository.delete(product);
	}
}
