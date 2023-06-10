package com.burakkolay.rentacar.repository;

import com.burakkolay.rentacar.entities.Car;
import com.burakkolay.rentacar.entities.enums.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    List<Car> findAllByStateIsNot(State state);

}
