package com.elhachimi.billing.entities;

import jakarta.persistence.OneToMany;
import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDate;
import java.util.Collection;

@Projection(name = "fullBill", types = {Bill.class})
public interface BillProjection {
    Long getId();
    LocalDate getBillingDate();
    Long getCustomerId();
}
