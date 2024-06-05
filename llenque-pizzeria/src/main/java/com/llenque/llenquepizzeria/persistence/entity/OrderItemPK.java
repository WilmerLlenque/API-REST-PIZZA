package com.llenque.llenquepizzeria.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemPK implements Serializable {

    private Integer idOrder;
    private Integer idPizza;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItemPK that)) return false;
        return Objects.equals(idOrder, that.idOrder) && Objects.equals(idPizza, that.idPizza);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrder, idPizza);
    }
}
