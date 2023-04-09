package com.burakkolay.rentacar.repository;

import com.burakkolay.rentacar.entities.concretes.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceRepository extends JpaRepository<Maintenance,Integer> {
    Maintenance findMaintenanceByCarIdAndIsCompletedFalse(int carId);
    boolean existsByCarIdAndIsCompletedFalse(int carId);
}
