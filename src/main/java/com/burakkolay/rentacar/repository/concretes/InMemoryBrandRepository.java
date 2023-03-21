package com.burakkolay.rentacar.repository.concretes;

import com.burakkolay.rentacar.entities.concretes.Brand;
import com.burakkolay.rentacar.repository.abstracts.BrandRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryBrandRepository implements BrandRepository {


    // Database         //! Veriler saklanıyor.     oldu
    // Service          //! Kendimiz işliyoruz.
    // Controller       //! cila atıp kullanıcıya gönderiyoruz.

    private final List<Brand> brands;

    public InMemoryBrandRepository(){
        this.brands = new ArrayList<>();
        brands.add(new Brand(1,"Mercedes"));        //0
        brands.add(new Brand(2,"Bmw"));             //1
        brands.add(new Brand(3,"AUDI"));            //2
        brands.add(new Brand(4,"Volvo"));
        brands.add(new Brand(5,"Renault"));

    }

    @Override
    public List<Brand> getAll() {
        return brands;
    }

    @Override
    public Brand getById(int id) {

        for (Brand brand:brands){
            if(brand.getId()==id)
                return brand;
        }
        return null;
//        return brands.get(id-1);
    }

    @Override
    public Brand add(Brand brand) {
        brands.add(brand);
        return brand;
    }

    @Override
    public Brand update(int id, Brand brand) {
        return brands.set(id-1,brand);
    }

    @Override
    public void delete(int id) {
        brands.remove(id-1);
    }
}
