package oi.github.D4N23.imageapi.images.mappers;

import java.io.IOException;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import oi.github.D4N23.imageapi.domain.entity.Image;
import oi.github.D4N23.imageapi.domain.enums.ImageExtension;
import oi.github.D4N23.imageapi.images.dto.ImagesDto;

@Component
public class ImageMapper {
     
    public Image mapToImage(MultipartFile file, String name, List<String> tags) throws IOException{
            return Image.builder()
                        .name(name)
                        .tags(String.join(",", tags)) //["tag1","tag2"] -> tag1,tag2
                        .size(file.getSize())
                        .extension(ImageExtension.valueOf(MediaType.valueOf(file.getContentType())))
                        .file(file.getBytes())
                        .build();
    }

    public ImagesDto imageToDto(Image image, String url){
        return ImagesDto.builder()
                .url(url)
                .extension(image.getExtension().name())
                .name(image.getName())
                .size(image.getSize())
                .uploadDate(image.getUploadDate().toLocalDate())
                .build();
    }
}
