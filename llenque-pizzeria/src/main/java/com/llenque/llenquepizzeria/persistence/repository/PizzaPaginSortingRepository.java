package com.llenque.llenquepizzeria.persistence.repository;

import com.llenque.llenquepizzeria.persistence.entity.PizzaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListPagingAndSortingRepository;


public interface PizzaPaginSortingRepository extends ListPagingAndSortingRepository<PizzaEntity,Integer> {

    Page<PizzaEntity> findByAvailableTrue(Pageable pageable);

}
