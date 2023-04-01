package com.burakkolay.rentacar.business.dto.response.update;

import com.burakkolay.rentacar.entities.enums.State;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateCarResponse {
    private int id;
    private int modelYear;
    private String plate;
    @Enumerated(EnumType.STRING)
    private State state; //  Available, Rented,  Maintenance
    private double dailyPrice;
}
