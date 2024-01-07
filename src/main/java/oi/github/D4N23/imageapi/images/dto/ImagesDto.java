package oi.github.D4N23.imageapi.images.dto;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ImagesDto {
private String url;
private String name;    
private String extension;
private Long size;
private LocalDate uploadDate;

}
