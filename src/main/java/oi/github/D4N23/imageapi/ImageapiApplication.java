package oi.github.D4N23.imageapi;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// import oi.github.D4N23.imageapi.domain.entity.Image;
// import oi.github.D4N23.imageapi.domain.enums.ImageExtension;
// import oi.github.D4N23.imageapi.infra.repositoy.ImageRepository;

@SpringBootApplication
@EnableJpaAuditing
public class ImageapiApplication {

	// @Bean
	// public CommandLineRunner commandLineRunner(@Autowired ImageRepository repository){
	// 	return args ->{
	// 		Image image = Image
	// 		.builder()
	// 		.extension(ImageExtension.JPG)
	// 		.name("myimage")
	// 		.tags("test")
	// 		.size(2000L)
	// 		.build();

	// 	repository.save(image);
	// 	};
	// }

	public static void main(String[] args) {
		SpringApplication.run(ImageapiApplication.class, args);
	}

}
