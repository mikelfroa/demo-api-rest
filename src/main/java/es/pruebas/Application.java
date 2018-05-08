package es.pruebas;

import es.pruebas.models.Community;
import es.pruebas.repositories.CommunityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application  {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(CommunityRepository repository) {
        return (args) -> {
            // save communities
            repository.save(new Community("Java", "Descripcion Java"));
            repository.save(new Community("eCommerce", "Descripcion eCommerce"));
            repository.save(new Community("Agile", "Descripcion Agile"));
            repository.save(new Community("QA & Testing", "Descripcion QA & Testing"));
            repository.save(new Community("DevOps", "Descripcion DevOps"));
            
        };
    }


}
