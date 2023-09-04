package com.magdy.ecommerce.services;

import com.magdy.ecommerce.dto.Purchase;
import com.magdy.ecommerce.dto.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse  placeOrder(Purchase purchase);
}
