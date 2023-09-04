package com.magdy.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



@Entity(name = "State")
@Table(name = "state")
@Data
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;


    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name ="country_id" )
    private Country country ;


}
