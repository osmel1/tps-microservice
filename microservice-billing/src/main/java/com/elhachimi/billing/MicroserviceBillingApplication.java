package com.elhachimi.billing;

import com.elhachimi.billing.entities.Bill;
import com.elhachimi.billing.entities.ProductItem;
import com.elhachimi.billing.feign.CustomerRestClient;
import com.elhachimi.billing.feign.ProductRestClient;
import com.elhachimi.billing.models.Customer;
import com.elhachimi.billing.models.Product;
import com.elhachimi.billing.repos.BillRepo;
import com.elhachimi.billing.repos.ProductItemRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.PagedModel;

import java.time.LocalDate;

@SpringBootApplication
@EnableFeignClients
public class MicroserviceBillingApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceBillingApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(
            CustomerRestClient customerRestClient,
            BillRepo billRepository,
            ProductItemRepo productItemRepository,
            ProductRestClient productRestClient
    ){
        return args -> {
            Customer customer = customerRestClient.getCustomerById(1L);
            System.out.println("------------------------");
            System.out.println("Customer id : "+customer.getId());
            System.out.println("Customer name : "+customer.getName());
            System.out.println("Customer email : "+customer.getEmail());
            System.out.println("------------------------");
            // save the bill
            Bill bill = billRepository.save(new Bill(null, LocalDate.now(),null,customer.getId(),null));
            // save the products items
            PagedModel<Product> products = productRestClient.allProducts(0, 20);
            products.forEach(product -> {
                ProductItem productItem = ProductItem.builder()
                        .productId(product.getId())
                        .product(null)
                        .price(product.getPrice())
                        .bill(bill)
                        .quantity(1+Math.random()*100)
                        .build();
                productItemRepository.save(productItem);
            });

        };
    }
}
