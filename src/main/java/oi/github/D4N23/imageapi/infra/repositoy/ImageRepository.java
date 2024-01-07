package oi.github.D4N23.imageapi.infra.repositoy;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.util.StringUtils;
import oi.github.D4N23.imageapi.domain.entity.Image;
import oi.github.D4N23.imageapi.domain.enums.ImageExtension;
import static org.springframework.data.jpa.domain.Specification.anyOf;
import static org.springframework.data.jpa.domain.Specification.where;
import static oi.github.D4N23.imageapi.infra.repositoy.specs.ImageSpecs.extensionEqual;
import static oi.github.D4N23.imageapi.infra.repositoy.specs.ImageSpecs.nameLike;
import static oi.github.D4N23.imageapi.infra.repositoy.specs.ImageSpecs.tagsLike;
import static oi.github.D4N23.imageapi.infra.repositoy.specs.GenericSpecs.conjunction;

public interface ImageRepository extends JpaRepository<Image, String>, JpaSpecificationExecutor<Image> {

    /**
     * 
     * @param extension
     * @param query
     * @return
     * 
     * SELECT * FROM IMAGE WHERE 1 = 1 AND EXTENSION = 'PNG' AND (NAME LIKE 'QUERY' OR TAGS LIKE 'QUERY') 
     * 
     */


    default List<Image> findByExtensionAndNameOrTagsLike(ImageExtension extension, String query){
       //SELECT * FROM IMAGE WHERE 1 = 1
        Specification<Image> spec = where(conjunction());
        if(extension != null){
            spec = spec.and(extensionEqual(extension));
        }

        if(StringUtils.hasText(query)){
            //AND (NAE LIKE 'QUERY' OR TAGS LIKE 'QUERY')
            //RIVER => %RI%
            

            spec = spec.and(anyOf(nameLike(query), tagsLike(query)));
        }

        return findAll(spec);
        // return null;
    }

}
