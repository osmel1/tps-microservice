package com.elhachimi.billing.web;

import com.elhachimi.billing.entities.Bill;
import com.elhachimi.billing.feign.CustomerRestClient;
import com.elhachimi.billing.feign.ProductRestClient;
import com.elhachimi.billing.repos.BillRepo;
import com.elhachimi.billing.repos.ProductItemRepo;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class BillingRestController {
    CustomerRestClient customerRestClient;
    ProductRestClient productRestClient;
    BillRepo billRepo;
    ProductItemRepo productItemRepo;

    @GetMapping("/fullBill/{id}")
    public Bill getBill(@PathVariable(name = "id") Long id) {
        Bill bill = billRepo.findById(id).get();
        bill.setCustomer(customerRestClient.getCustomerById(bill.getCustomerId()));
        bill.getProductItems().forEach(pi -> {
            pi.setProduct(productRestClient.getProductById(pi.getProductId()));
        });
        return bill;
    }
}
