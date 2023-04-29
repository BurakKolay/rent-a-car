package com.burakkolay.rentacar.business.abstracts;

import com.burakkolay.rentacar.business.dto.requests.create.CreatePaymentRequest;
import com.burakkolay.rentacar.business.dto.requests.update.UpdatePaymentRequest;
import com.burakkolay.rentacar.business.dto.responses.create.CreatePaymentResponse;
import com.burakkolay.rentacar.business.dto.responses.get.GetAllPaymentsResponse;
import com.burakkolay.rentacar.business.dto.responses.get.GetPaymentResponse;
import com.burakkolay.rentacar.business.dto.responses.update.UpdatePaymentResponse;
import com.burakkolay.rentacar.common.dto.CreateRentalPaymentRequest;

import java.util.List;

public interface PaymentService {
    List<GetAllPaymentsResponse> getAll();

    GetPaymentResponse getById(int id);

    CreatePaymentResponse add(CreatePaymentRequest request);

    UpdatePaymentResponse update(int id, UpdatePaymentRequest request);

    void delete(int id);

    void processRentalPayment(CreateRentalPaymentRequest request);
}
