package com.burakkolay.rentacar.business.dto.requests.create;

import com.burakkolay.rentacar.common.constants.Regex;
import com.burakkolay.rentacar.common.utils.annotations.NotFutureYear;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCarRequest {
    private int modelId;
    @Min(1)
    private double dailyPrice;
    @Min(1998)
    @NotFutureYear
    private int modelYear;
    @Pattern(regexp = Regex.Plate)
    private String plate;
}
