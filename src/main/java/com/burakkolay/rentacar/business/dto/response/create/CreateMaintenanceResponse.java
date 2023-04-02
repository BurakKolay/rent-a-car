package com.burakkolay.rentacar.business.dto.response.create;

import com.burakkolay.rentacar.entities.concretes.Car;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateMaintenanceResponse {
    private int id;
    private Timestamp createdDate;
    private Timestamp lasModifiedDate;
    private Car car;
    private boolean state;
}
