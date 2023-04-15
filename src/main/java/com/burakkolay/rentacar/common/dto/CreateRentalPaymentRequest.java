package com.burakkolay.rentacar.common.dto;

import com.burakkolay.rentacar.business.dto.requests.PaymentRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateRentalPaymentRequest extends PaymentRequest {
    private double price;
}

