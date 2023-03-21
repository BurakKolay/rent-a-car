package com.burakkolay.rentacar.business.concretes;

import com.burakkolay.rentacar.business.abstracts.BrandService;
import com.burakkolay.rentacar.entities.concretes.Brand;
import com.burakkolay.rentacar.repository.abstracts.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandManager implements BrandService {
    BrandRepository brandRepository;

    public BrandManager(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public List<Brand> getAll() {
        return brandRepository.getAll();
    }

    @Override
    public Brand getById(int id) {
        return brandRepository.getById(id);
    }

    @Override
    public Brand add(Brand brand) {
        return brandRepository.add(brand);
    }

    @Override
    public Brand update(int id, Brand brand) {
        return brandRepository.update(id,brand);
    }

    @Override
    public void delete(int id) {
        brandRepository.delete(id);
    }
}
