package oi.github.D4N23.imageapi.domain.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import oi.github.D4N23.imageapi.domain.enums.ImageExtension;

@Entity
@Table
@EntityListeners(AuditingEntityListener.class)
@Data 
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column
    private String name;

    @Column
    private Long size;

    @Column
    @Enumerated(EnumType.STRING)
     private ImageExtension extension;


     @Column
     @CreatedDate
     private LocalDateTime uploadDate;

     @Column
     private String tags;

     @Column
     @Lob // diz que o campo é um arquivo, um stream de dados  
     private byte[] file;

     //encontra a extensão da imgaem e concatena com o nome
     public String getFileName(){
        return getName().concat(getExtension().name());
     }

}
