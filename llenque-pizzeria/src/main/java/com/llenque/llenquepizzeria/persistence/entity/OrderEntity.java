package com.llenque.llenquepizzeria.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pizza_order")
@Getter
@Setter
@NoArgsConstructor
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,name = "id_order")
    private Integer idOrder;

    @Column(nullable = false,name = "id_customer",length = 15)
    private String idCustomer;

    @Column(nullable = false,columnDefinition = "DATETIME")
    private LocalDateTime date;

    @Column(nullable = false,columnDefinition = "Decimal(6,2)")
    private Double total;

    @Column(nullable = false,columnDefinition = "Char(1)")
    private String method;

    @Column(name = "additional_notes",length = 200)
    private String additionalNotes;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_customer",referencedColumnName = "id_customer",insertable = false,updatable = false)
    @JsonIgnore
    private CustomerEntity customer;

    @OneToMany(mappedBy = "order",fetch = FetchType.EAGER)
    private List<OrderItemEntity> items;

}
