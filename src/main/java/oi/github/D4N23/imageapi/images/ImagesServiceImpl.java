package oi.github.D4N23.imageapi.images;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import oi.github.D4N23.imageapi.domain.entity.Image;
import oi.github.D4N23.imageapi.domain.enums.ImageExtension;
import oi.github.D4N23.imageapi.domain.service.ImageService;
import oi.github.D4N23.imageapi.infra.repositoy.ImageRepository;


@Service
@RequiredArgsConstructor
public class ImagesServiceImpl implements ImageService {

 private final ImageRepository repository;

    @Override
    @Transactional
    public Image save(Image image) {
      return repository.save(image);
    }

    @Override
    public Optional<Image> getById(String id) {
      return repository.findById(id);
    }

    @Override
    public List<Image> search(ImageExtension extension, String query) {
          return repository.findByExtensionAndNameOrTagsLike(extension, query);
    }  

}
