package com.burakkolay.rentacar.business.abstracts;

import com.burakkolay.rentacar.entities.Brand;

import java.util.List;

public interface BrandService {
    List<Brand> getAll();
    Brand getById(int id);
    Brand add(Brand brand);
    Brand update(int id, Brand brand);
    void delete(int id);

}
