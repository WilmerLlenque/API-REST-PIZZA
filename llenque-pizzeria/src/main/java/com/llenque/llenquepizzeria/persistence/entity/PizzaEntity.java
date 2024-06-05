package com.llenque.llenquepizzeria.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Pizza")
@Getter
@Setter
@NoArgsConstructor
public class PizzaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,name = "id_pizza")
    private Integer idPizza;

    @Column(nullable = false,length = 30,unique = true)
    private String name;

    @Column(nullable = false,length = 150)
    private String description;

    @Column(nullable = false,columnDefinition = "Decimal(5,2)")
    private Double price;

    @Column(columnDefinition = "TINYINT")
    private Boolean vegetarian;

    @Column(columnDefinition = "TINYINT")
    private Boolean vegan;

    @Column(nullable = false,columnDefinition = "TINYINT")
    private Boolean available;


}
