package com.elhachimi.microservicespringcloud;

import com.elhachimi.microservicespringcloud.entities.Customer;
import com.elhachimi.microservicespringcloud.repositories.CustomerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.List;

@SpringBootApplication
public class MicroserviceSpringcloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceSpringcloudApplication.class, args);
	}
	@Bean
	CommandLineRunner commandLineRunner(CustomerRepo customerRepo, RepositoryRestConfiguration repositoryRestConfiguration){
		// pour afficher les id dans le json retourner par le service rest repository
		repositoryRestConfiguration.exposeIdsFor(Customer.class);
		return args -> {
			List.of("Oussama","Hatim","Toni","Yassine").forEach(name->{
				Customer customer = Customer.builder().name(name).email(name+"mail.com").build();
				customerRepo.save(customer);
			});
		};
	}
}
