package pl.coderslab.springbootdemo.service;

import org.springframework.stereotype.Service;
import pl.coderslab.springbootdemo.entity.Image;
import pl.coderslab.springbootdemo.repository.ImageRepository;

import java.util.List;

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public Image addImage(Image image) {
        return imageRepository.save(image);
    }

    public List<Image> findAll() {
        return imageRepository.findAll();
    }

    public Image findById(long id) {
        return imageRepository.findById(id).get();
    }
}
