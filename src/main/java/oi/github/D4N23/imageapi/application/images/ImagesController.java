package oi.github.D4N23.imageapi.application.images;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oi.github.D4N23.imageapi.domain.entity.Image;
import oi.github.D4N23.imageapi.domain.enums.ImageExtension;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.websocket.server.PathParam;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/images")
@Slf4j
@RequiredArgsConstructor
public class ImagesController {

    private final ImagesServiceImpl service;
    private final ImageMapper mapper;

    @PostMapping //controller responsavel por receber imagens do lado do servidor
    public ResponseEntity save(@RequestParam("file")MultipartFile file,
                               @RequestParam("name") String name,
                               @RequestParam("tags") List<String> tags) throws IOException{
                    log.info("Imagem recebida: name: {}, size: {}", file.getOriginalFilename(), file.getSize());
                    // log.info("Nome definido para a imagem: {}", name);
                    // log.info("Tags: {}", tags);
                    // log.info("Content Type: {}", file.getContentType());
                    // log.info("Media Type: {}", MediaType.valueOf(file.getContentType()));            

                                Image image =  mapper.mapToImage(file, name, tags);
                                Image savedImage = service.save(image);
                                URI imaUri = buildImageUri(savedImage);

        return ResponseEntity.created(imaUri).build();
    }

    // /v1/images/asdasdasdasdd
    @GetMapping("{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable String id){
       var possibleImage = service.getById(id);
       if(possibleImage.isEmpty()){
         return ResponseEntity.notFound().build();
       }

       var image = possibleImage.get();
       HttpHeaders headers = new HttpHeaders();
       headers.setContentType(image.getExtension().getMediaType());
       headers.setContentLength(image.getSize());

       //inline; filename="image.PNG"
       headers.setContentDispositionFormData("inline; filename=\"" + image.getFileName() + "\"",image.getFileName());

        return new ResponseEntity<>(image.getFile(), headers, HttpStatus.OK);
    }

    //localhost:8080/v1/images?extension=PNG&query=Nature
    @GetMapping
    public ResponseEntity<List<ImagesDto>> search(
        @RequestParam(value = "extension", required = false) String extension, 
        @RequestParam(value = "query", required = false) String query){
        
        var result = service.search(ImageExtension.ofName(extension), query);

        var images = result.stream().map(image -> {
            var url = buildImageUri(image);
            return mapper.imageToDto(image, url.toString());
        }).collect(Collectors.toList());
        
        return ResponseEntity.ok(images);

    }

    //localhost:8080/v1/images/asiddasdasdasd
    private URI buildImageUri(Image image){ 
        String imagePath = "/" + image.getId();

     return ServletUriComponentsBuilder
            .fromCurrentRequestUri()
            .path(imagePath)
            .build()
            .toUri();
    } 

}
