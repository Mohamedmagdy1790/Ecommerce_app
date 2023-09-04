package com.magdy.ecommerce.dto;

import com.magdy.ecommerce.entity.Address;
import com.magdy.ecommerce.entity.Customer;
import com.magdy.ecommerce.entity.Order;
import com.magdy.ecommerce.entity.OrderItem;
import lombok.Data;

import java.util.Set;


@Data
public class Purchase {

    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;

}
