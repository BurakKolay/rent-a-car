package com.burakkolay.rentacar.business.concretes;

import com.burakkolay.rentacar.business.abstracts.PaymentService;
import com.burakkolay.rentacar.business.abstracts.PosService;
import com.burakkolay.rentacar.business.dto.requests.create.CreatePaymentRequest;
import com.burakkolay.rentacar.business.dto.requests.update.UpdatePaymentRequest;
import com.burakkolay.rentacar.business.dto.responses.create.CreatePaymentResponse;
import com.burakkolay.rentacar.business.dto.responses.get.GetAllPaymentsResponse;
import com.burakkolay.rentacar.business.dto.responses.get.GetPaymentResponse;
import com.burakkolay.rentacar.business.dto.responses.update.UpdatePaymentResponse;
import com.burakkolay.rentacar.business.rules.PaymentBusinessRules;
import com.burakkolay.rentacar.common.dto.CreateRentalPaymentRequest;
import com.burakkolay.rentacar.entities.Payment;
import com.burakkolay.rentacar.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PaymentManager implements PaymentService {
    private final PaymentRepository repository;
    private final ModelMapper mapper;
    private final PosService posService;
    private final PaymentBusinessRules rules;

    @Override
    public List<GetAllPaymentsResponse> getAll() {
        List<Payment> payments = repository.findAll();
        List<GetAllPaymentsResponse> response = payments
                .stream()
                .map(payment -> mapper.map(payment, GetAllPaymentsResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetPaymentResponse getById(int id) {
        rules.checkIfPaymentExists(id);
        Payment payment = repository.findById(id).orElseThrow();
        GetPaymentResponse response = mapper.map(payment, GetPaymentResponse.class);

        return response;
    }

    @Override
    public CreatePaymentResponse add(CreatePaymentRequest request) {
        rules.checkIfCardExists(request.getCardNumber());
        Payment payment = mapper.map(request, Payment.class);
        payment.setId(0);
        repository.save(payment);
        CreatePaymentResponse response = mapper.map(payment, CreatePaymentResponse.class);

        return response;
    }

    @Override
    public UpdatePaymentResponse update(int id, UpdatePaymentRequest request) {
        rules.checkIfPaymentExists(id);
        Payment payment = mapper.map(request, Payment.class);
        payment.setId(id);
        repository.save(payment);
        UpdatePaymentResponse response = mapper.map(payment, UpdatePaymentResponse.class);

        return response;
    }

    @Override
    public void delete(int id) {
        rules.checkIfPaymentExists(id);
        repository.deleteById(id);
    }

    @Override
    public void processRentalPayment(CreateRentalPaymentRequest request) {
        rules.checkIfPaymentIsValid(request);
        Payment payment = repository.findByCardNumber(request.getCardNumber());
        rules.checkIfBalanceIdEnough(payment.getBalance(), request.getPrice());
        posService.pay(); // fake pos service
        payment.setBalance(payment.getBalance() - request.getPrice());
        repository.save(payment);
    }

}
