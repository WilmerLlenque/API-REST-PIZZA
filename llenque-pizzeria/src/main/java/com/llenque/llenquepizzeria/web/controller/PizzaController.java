package com.llenque.llenquepizzeria.web.controller;

import com.llenque.llenquepizzeria.persistence.entity.PizzaEntity;
import com.llenque.llenquepizzeria.service.DTO.UpdatePizzaPriceDTO;
import com.llenque.llenquepizzeria.service.PizzaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pizza")
public class PizzaController {


    public final PizzaServices pizzaServices;

    @Autowired
    public PizzaController(PizzaServices pizzaServices) {
        this.pizzaServices = pizzaServices;
    }

    @GetMapping("/all")
    public List<PizzaEntity> getAll(){
        return pizzaServices.getAll();
    }

    @GetMapping("/getId/{id}")
    public PizzaEntity getById(@PathVariable int id){
        return pizzaServices.getById(id);
    }

    @PostMapping("/save")
    public ResponseEntity<PizzaEntity> save(@RequestBody PizzaEntity pizza){
        if (!pizzaServices.exist(pizza.getIdPizza()) || pizza.getIdPizza() == null){
            return ResponseEntity.ok(this.pizzaServices.save(pizza));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/update")
    public ResponseEntity<PizzaEntity> update(@RequestBody PizzaEntity pizza){
        if (pizzaServices.exist(pizza.getIdPizza()) && pizza.getIdPizza() != null){
            return ResponseEntity.ok(this.pizzaServices.save(pizza));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable int id){
        if (pizzaServices.exist(id)){
            pizzaServices.delete(id);
            return true;
        }
        return false;
    }

    @GetMapping("/available")
    public List<PizzaEntity> getAvailable(){
        return pizzaServices.getAvailable();
    }

    @GetMapping("/getByName/{name}")
    public PizzaEntity getByName(@PathVariable String name){
        return pizzaServices.getByName(name);
    }

    @GetMapping("/with/{ingredient}")
    public List<PizzaEntity> getWith(@PathVariable String ingredient){
        return pizzaServices.getWith(ingredient);
    }
    @GetMapping("/without/{ingredient}")
    public List<PizzaEntity> getWithout(@PathVariable String ingredient){
        return pizzaServices.getWithout(ingredient);
    }
    @GetMapping("/cheapest/{price}")
    public List<PizzaEntity> getCheapestPizzas(@PathVariable double price){
        return pizzaServices.getCheapestPizzas(price);
    }
    @GetMapping("/getAllPag")
    public Page<PizzaEntity> getAllPag(@RequestParam(defaultValue = "0") int numPag,
                                       @RequestParam(defaultValue = "4") int tamanioPag){
        return pizzaServices.getAllPag(numPag,tamanioPag);
    }

    @GetMapping("/availablePag")
    public Page<PizzaEntity> getAvailablePag(@RequestParam(defaultValue = "0") int numPag,
                                             @RequestParam(defaultValue = "8") int tamanioPag,
                                             @RequestParam(defaultValue = "price") String sorBy,
                                             @RequestParam(defaultValue = "ASC") String sortDirection){
        return pizzaServices.getAvailablePag(numPag,tamanioPag,sorBy,sortDirection);

    }
    @PutMapping("/price")
    public void updatePrice(@RequestBody UpdatePizzaPriceDTO dto){
        if (pizzaServices.exist(dto.getIdPizza())) {
            pizzaServices.updatePrice(dto);
        }
    }
}
