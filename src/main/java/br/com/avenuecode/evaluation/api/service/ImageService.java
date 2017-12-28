package br.com.avenuecode.evaluation.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.avenuecode.evaluation.api.model.Image;
import br.com.avenuecode.evaluation.api.repository.ImageRepository;
import br.com.avenuecode.evaluation.api.to.ImageTo;
import br.com.avenuecode.evaluation.helper.ImageHelper;

@Component
public class ImageService {

	@Autowired
	ImageRepository imageRepository;
	
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
	
	public void delete(Long imageId) {
		imageRepository.delete(imageId);
	}
}
