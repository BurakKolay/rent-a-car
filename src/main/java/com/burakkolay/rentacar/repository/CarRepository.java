package com.burakkolay.rentacar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.burakkolay.rentacar.entities.concretes.Car;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car,Integer> {

}
