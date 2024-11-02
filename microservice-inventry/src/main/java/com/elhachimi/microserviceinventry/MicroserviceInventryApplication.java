package com.elhachimi.microserviceinventry;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.List;

@SpringBootApplication
public class MicroserviceInventryApplication {
	public static void main(String[] args) {
		SpringApplication.run(MicroserviceInventryApplication.class, args);
	}
	@Bean
	CommandLineRunner commandLineRunner(ProductRepo productRepo, RepositoryRestConfiguration repositoryRestConfiguration){
		// pour afficher les id dans le json retourner par le service rest repository
		repositoryRestConfiguration.exposeIdsFor(Product.class);
		return args -> {
			List.of("Ordinateur","PC" ,"Imprimante","Telephone").forEach(name->{
				Product product = Product.builder().name(name).price(Math.random()*1000.0).quantity(Math.random()*50).build();
				productRepo.save(product);
			});
		};
	}

}
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
class Product{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private double price;
	private double quantity;
}

@RepositoryRestResource
interface ProductRepo extends JpaRepository<Product, Long> {
}


