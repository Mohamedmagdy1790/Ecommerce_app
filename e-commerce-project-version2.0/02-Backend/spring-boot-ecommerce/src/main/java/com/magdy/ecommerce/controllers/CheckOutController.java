package com.magdy.ecommerce.controllers;


import com.magdy.ecommerce.dto.Purchase;
import com.magdy.ecommerce.dto.PurchaseResponse;
import com.magdy.ecommerce.services.CheckoutService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/checkout")
public class CheckOutController {
CheckoutService checkoutService;
CheckOutController(CheckoutService checkoutService){
    this.checkoutService=checkoutService;
}


    @PostMapping("/purchase")
    public PurchaseResponse add(@RequestBody Purchase purchase){

        PurchaseResponse purchaseResponse = checkoutService.placeOrder(purchase);

        return purchaseResponse;
    }
}
