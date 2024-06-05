package com.llenque.llenquepizzeria.persistence.repository;

import com.llenque.llenquepizzeria.persistence.Projection.OrderSummary;
import com.llenque.llenquepizzeria.persistence.entity.OrderEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends ListCrudRepository<OrderEntity,Integer> {

    List<OrderEntity> findByDateAfter(LocalDateTime date);

    List<OrderEntity> findByMethodIn(List<String> method);

    @Query(value = "SELECT * FROM PIZZA_ORDER WHERE ID_CUSTOMER=:Id",nativeQuery = true)
    List<OrderEntity> findCustomerOrders(@Param("Id") String idCustomer);

    @Query(value = "SELECT " +
            "    po.id_order AS idOrder, " +
            "    cu.name AS customerName, " +
            "    po.date AS orderDate, " +
            "    po.total AS orderTotal, " +
            "    GROUP_CONCAT(pi.name) AS pizzaNames " +
            "FROM " +
            "    pizza_order po " +
            "        INNER JOIN customer cu ON po.id_customer = cu.id_customer " +
            "        INNER JOIN order_item oi ON po.id_order = oi.id_order " +
            "        INNER JOIN pizza pi ON oi.id_pizza = pi.id_pizza " +
            "WHERE " +
            "        po.id_order = :orderId " +
            "GROUP BY " +
            "    po.id_order, " +
            "    cu.name, " +
            "    po.date",nativeQuery = true)
    OrderSummary findSummary(@Param("orderId") int idOrder);
}
