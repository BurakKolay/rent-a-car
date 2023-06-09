package com.burakkolay.rentacar.business.dto.requests.update;


import com.burakkolay.rentacar.entities.enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCarRequest {
    private int modelId;
    private double dailyPrice;
    private int modelYear;
    private String plate;
    private State state;
}
