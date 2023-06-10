package com.burakkolay.rentacar.business.concretes;

import com.burakkolay.rentacar.business.abstracts.CarService;
import com.burakkolay.rentacar.business.abstracts.InvoiceService;
import com.burakkolay.rentacar.business.abstracts.PaymentService;
import com.burakkolay.rentacar.business.abstracts.RentalService;
import com.burakkolay.rentacar.business.dto.requests.create.CreateInvoiceRequest;
import com.burakkolay.rentacar.business.dto.requests.create.CreateRentalRequest;
import com.burakkolay.rentacar.business.dto.requests.update.UpdateRentalRequest;
import com.burakkolay.rentacar.business.dto.responses.create.CreateRentalResponse;
import com.burakkolay.rentacar.business.dto.responses.get.GetAllRentalsResponse;
import com.burakkolay.rentacar.business.dto.responses.get.GetCarResponse;
import com.burakkolay.rentacar.business.dto.responses.get.GetRentalResponse;
import com.burakkolay.rentacar.business.dto.responses.update.UpdateRentalResponse;
import com.burakkolay.rentacar.business.rules.RentalBusinessRules;
import com.burakkolay.rentacar.common.dto.CreateRentalPaymentRequest;
import com.burakkolay.rentacar.entities.Rental;
import com.burakkolay.rentacar.entities.enums.State;
import com.burakkolay.rentacar.repository.RentalRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class RentalManager implements RentalService {
    private final RentalRepository repository;
    private final ModelMapper mapper;
    private final CarService carService;
    private final PaymentService paymentService;
    private final InvoiceService invoiceService;
    private final RentalBusinessRules rules;

    @Override
    public List<GetAllRentalsResponse> getAll() {
        List<Rental> rentals = repository.findAll();
        List<GetAllRentalsResponse> response = rentals
                .stream()
                .map(rental -> mapper.map(rental, GetAllRentalsResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetRentalResponse getById(int id) {
        rules.checkIfRentalExists(id);
        Rental rental = repository.findById(id).orElseThrow();
        GetRentalResponse response = mapper.map(rental, GetRentalResponse.class);

        return response;
    }

    @Override
    public CreateRentalResponse add(CreateRentalRequest request) {
        rules.checkIfCarAvailable(carService.getById(request.getCarId()).getState());
        Rental rental = mapper.map(request, Rental.class);
        rental.setId(0);
        rental.setTotalPrice(getTotalPrice(rental));
        rental.setStartDate(LocalDateTime.now());

        // Payment
        CreateRentalPaymentRequest paymentRequest = new CreateRentalPaymentRequest();
        mapper.map(request.getPaymentRequest(), paymentRequest);
        paymentRequest.setPrice(getTotalPrice(rental));
        paymentService.processRentalPayment(paymentRequest);

        repository.save(rental);
        carService.changeState(rental.getCar().getId(), State.RENTED);
        CreateRentalResponse response = mapper.map(rental, CreateRentalResponse.class);

        // Invoice
        CreateInvoiceRequest invoiceRequest = new CreateInvoiceRequest();
        createInvoiceRequest(request, invoiceRequest, rental);
        invoiceService.add(invoiceRequest);

        return response;
    }

    @Override
    public UpdateRentalResponse update(int id, UpdateRentalRequest request) {
        rules.checkIfRentalExists(id);
        Rental rental = mapper.map(request, Rental.class);
        rental.setId(id);
        rental.setTotalPrice(getTotalPrice(rental));
        repository.save(rental);
        UpdateRentalResponse response = mapper.map(rental, UpdateRentalResponse.class);

        return response;
    }

    @Override
    public void delete(int id) {
        rules.checkIfRentalExists(id);
        int carId = repository.findById(id).get().getCar().getId();
        carService.changeState(carId, State.AVAILABLE);
        repository.deleteById(id);
    }

    private double getTotalPrice(Rental rental) {
        return rental.getDailyPrice() * rental.getRentedForDays();
    }

    private void createInvoiceRequest(CreateRentalRequest request, CreateInvoiceRequest invoiceRequest, Rental rental) {
        GetCarResponse car = carService.getById(request.getCarId());

        invoiceRequest.setRentedAt(rental.getStartDate());
        invoiceRequest.setModelName(car.getModelName());
        invoiceRequest.setBrandName(car.getModelBrandName());
        invoiceRequest.setDailyPrice(request.getDailyPrice());
        invoiceRequest.setRentedForDays(request.getRentedForDays());
        invoiceRequest.setCardHolder(request.getPaymentRequest().getCardHolder());
        invoiceRequest.setPlate(car.getPlate());
        invoiceRequest.setModelYear(car.getModelYear());
    }

}
