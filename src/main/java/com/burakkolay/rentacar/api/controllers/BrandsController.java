package com.burakkolay.rentacar.api.controllers;

import com.burakkolay.rentacar.business.abstracts.BrandService;
import com.burakkolay.rentacar.entities.Brand;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
public class BrandsController {

    private final BrandService brandService;

    public BrandsController(BrandService brandService) {
        this.brandService = brandService;
    }
    /*
    GET         Getirmek
    POST        Eklemek
    PUT         Güncellemek
    DELETE      Silmek
     */

    @GetMapping
    public List<Brand> getAll(){
        return brandService.getAll();
    }

    @GetMapping("/{id}")
    public Brand getById(@PathVariable int id){
        return brandService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Brand add(@RequestBody Brand brand){
        return brandService.add(brand);
    }

    @PutMapping("/{id}")
    public Brand update(@PathVariable int id,@RequestBody Brand brand){
        return brandService.update(id,brand);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        brandService.delete(id);
    }
}
