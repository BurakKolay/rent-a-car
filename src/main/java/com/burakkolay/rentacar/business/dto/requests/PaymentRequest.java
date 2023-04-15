package com.burakkolay.rentacar.business.dto.requests;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {
    @NotBlank(message = "Kart numarası boş bırakılamaz.")
    @Length(min = 16, max = 16,message = "Kart numarası 16 haneden oluşmak zorunda.")
    private String cardNumber;

    @NotBlank
    @Length(min=5)
    private String cardHolder;

    @Min(value = 2023)
    @NotNull
    private int cardExpirationYear;

    @Min(value =1)
    @Max(value = 12)
    @NotNull
    private int cardExpirationMonth;

    @Length(min = 3,max = 3)
    @NotBlank
    private String cardCvv;
}
