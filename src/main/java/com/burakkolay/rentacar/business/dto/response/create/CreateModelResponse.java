package com.burakkolay.rentacar.business.dto.response.create;

import com.burakkolay.rentacar.entities.enums.State;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateModelResponse {
    private int id;
    private String name;
}
