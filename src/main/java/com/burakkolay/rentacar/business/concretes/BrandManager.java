package com.burakkolay.rentacar.business.concretes;

import com.burakkolay.rentacar.business.abstracts.BrandService;
import com.burakkolay.rentacar.entities.Brand;
import com.burakkolay.rentacar.repository.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandManager implements BrandService {
    private final BrandRepository brandRepository;

    public BrandManager(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public List<Brand> getAll() {
        return brandRepository.findAll();
    }

    @Override
    public Brand getById(int id) {
        checkIfBrandExists(id);
        return brandRepository.findById(id).orElseThrow();
    }

    @Override
    public Brand add(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public Brand update(int id, Brand brand) {
        brand.setId(id);
        return brandRepository.save(brand);
    }

    @Override
    public void delete(int id) {
        brandRepository.deleteById(id);
    }

    private void checkIfBrandExists(int id){
        if(!brandRepository.existsById(id)) throw new RuntimeException("Marka Bulunamadı");
    }
}
