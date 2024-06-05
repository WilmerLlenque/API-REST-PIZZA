package com.llenque.llenquepizzeria.persistence.repository;

import com.llenque.llenquepizzeria.persistence.entity.PizzaEntity;
import com.llenque.llenquepizzeria.service.DTO.UpdatePizzaPriceDTO;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PizzaRepository extends ListCrudRepository<PizzaEntity,Integer> {

    List<PizzaEntity> findByAvailableTrue();
    PizzaEntity findFirstByAvailableTrueAndNameIgnoreCase(String name);
    List<PizzaEntity> findAllByAvailableTrueAndDescriptionContainingIgnoreCase(String ingredient);
    List<PizzaEntity> findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(String ingredient);

    List<PizzaEntity> findByPriceLessThan(double price);

    @Query(value = "update pizza set price= :#{#newPizzaPrice.newprice} " +
            "where id_pizza= :#{#newPizzaPrice.idPizza}",nativeQuery = true)
    @Modifying
    void updatePrice(@Param("newPizzaPrice") UpdatePizzaPriceDTO updatePizzaPriceDTO);


}
