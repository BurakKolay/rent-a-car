package com.burakkolay.rentacar.business.dto.requests.create;

import com.burakkolay.rentacar.entities.concretes.Car;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateMaintenanceRequest {
    private int carId;
}
