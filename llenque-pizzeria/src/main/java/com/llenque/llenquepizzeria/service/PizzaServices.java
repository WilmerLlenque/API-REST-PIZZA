package com.llenque.llenquepizzeria.service;

import com.llenque.llenquepizzeria.persistence.entity.PizzaEntity;
import com.llenque.llenquepizzeria.persistence.repository.PizzaPaginSortingRepository;
import com.llenque.llenquepizzeria.persistence.repository.PizzaRepository;
import com.llenque.llenquepizzeria.service.DTO.UpdatePizzaPriceDTO;
import com.llenque.llenquepizzeria.service.exeption.EmailApiExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PizzaServices {

    @Autowired
    private PizzaRepository pizzaRepository;
    @Autowired
    private PizzaPaginSortingRepository pizzaPaginSortingRepository;

     public List<PizzaEntity> getAll(){
        return pizzaRepository.findAll();
    }

     public Page<PizzaEntity> getAllPag(int numPagina,int tamanioPag){
         Pageable pageable= PageRequest.of(numPagina,tamanioPag);
         return pizzaPaginSortingRepository.findAll(pageable);
     }
     public PizzaEntity getById(int id){
        return pizzaRepository.findById(id).orElse(null);
     }

     public PizzaEntity save(PizzaEntity pizza){
        return pizzaRepository.save(pizza);
     }
     public boolean exist(int id){
        return pizzaRepository.existsById(id);
     }

     public void delete(int id){
            pizzaRepository.deleteById(id);
     }
     public List<PizzaEntity> getAvailable(){
        return pizzaRepository.findByAvailableTrue();
     }

     public Page<PizzaEntity> getAvailablePag(int numPag,int tamanioPag,String sortBy,String sortDirection){
         Sort sort=Sort.by(Sort.Direction.fromString(sortDirection),sortBy);
         Pageable pageable=PageRequest.of(numPag,tamanioPag,sort);
         return pizzaPaginSortingRepository.findByAvailableTrue(pageable);
     }

     public PizzaEntity getByName(String name){
        return pizzaRepository.findFirstByAvailableTrueAndNameIgnoreCase(name);
     }

     public List<PizzaEntity> getWith(String ingredient){
        return pizzaRepository.findAllByAvailableTrueAndDescriptionContainingIgnoreCase(ingredient);
     }
    public List<PizzaEntity> getWithout(String ingredient){
        return pizzaRepository.findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(ingredient);
    }

    public List<PizzaEntity> getCheapestPizzas(double price){
        return pizzaRepository.findByPriceLessThan(price);
    }

    @Transactional(noRollbackFor = EmailApiExeption.class)
    public void updatePrice(UpdatePizzaPriceDTO dto){
         pizzaRepository.updatePrice(dto);
         this.sendEmail();
    }

    private void sendEmail(){
         throw new EmailApiExeption();
    }


}
