package com.magdy.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="orders")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="order_tracking_number")
    private String orderTrackingNumber;

    @Column(name="total_quantity")
    private int totalQuantity;

    @Column(name="total_price")
    private BigDecimal totalPrice;

    @Column(name="status")
    private String status;

    @Column(name="date_created")
    @CreationTimestamp
    private Date dateCreated;

    @Column(name="last_updated")
    @UpdateTimestamp
    private Date lastUpdated;

    @ManyToOne()
    @JoinColumn(name = "customer_id")
    Customer customer;

    @OneToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "shipping_address_id", referencedColumnName = "id")
    private Address Shippingaddress;

    @OneToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "Billing_address_id",referencedColumnName = "id")
    private Address Billingaddress;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderItem> orderItemSet= new HashSet<>();

    public  void  add(OrderItem item){
        if(item !=null){
            if(orderItemSet ==null){
                orderItemSet=new HashSet<>();
            }
            else {
                orderItemSet.add(item);
                // set order in this item
                item.setOrder(this);
            }
        }
    }



}