package com.magdy.ecommerce.dto;

import lombok.Data;
import lombok.NonNull;
import org.springframework.transaction.annotation.Transactional;

@Data
public class PurchaseResponse {
    @NonNull
    private String orderTrackingNumber;
}
