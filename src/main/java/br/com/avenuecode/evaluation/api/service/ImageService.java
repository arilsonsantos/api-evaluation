package br.com.avenuecode.evaluation.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.avenuecode.evaluation.api.model.Image;
import br.com.avenuecode.evaluation.api.model.Product;
import br.com.avenuecode.evaluation.api.repository.ImageRepository;
import br.com.avenuecode.evaluation.api.repository.ProductRepository;
import br.com.avenuecode.evaluation.api.to.ImageTo;
import br.com.avenuecode.evaluation.api.to.ProductTo;
import br.com.avenuecode.evaluation.helper.ImageHelper;

@Component
public class ImageService {

	@Autowired
	ImageRepository imageRepository;
	
	@Autowired
	ProductRepository productRepository;

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
	
	public void save(Long productId, List<ImageTo> imagesTo) {
		Product product = productRepository.getOneFetchImage(productId);
		List<Image> images = new ArrayList<>();
		
		for (ImageTo imageTo : imagesTo) {
			imageTo.setProductTo(new ProductTo(productId));
			Image image = ImageHelper.convertImageToImage(imageTo);
			image.setProduct(product);
			images.add(image);
		}
		
		imageRepository.save(images);
	}
	

	public void delete(Long imageId) {
		imageRepository.delete(imageId);
	}
}
