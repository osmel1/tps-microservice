package com.elhachimi.billing.repos;

import com.elhachimi.billing.entities.Bill;
import com.elhachimi.billing.entities.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;
@RepositoryRestResource
public interface ProductItemRepo extends JpaRepository<ProductItem, Long> {
Collection<ProductItem> findByBillId(Long id);
}
