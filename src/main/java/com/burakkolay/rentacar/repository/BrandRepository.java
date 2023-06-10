package com.burakkolay.rentacar.repository;

import com.burakkolay.rentacar.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Integer> {
    boolean existsByNameIgnoreCase(String name);
}
