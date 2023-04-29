package com.burakkolay.rentacar.business.abstracts;

import com.burakkolay.rentacar.business.dto.requests.create.CreateRentalRequest;
import com.burakkolay.rentacar.business.dto.requests.update.UpdateRentalRequest;
import com.burakkolay.rentacar.business.dto.responses.create.CreateRentalResponse;
import com.burakkolay.rentacar.business.dto.responses.get.GetAllRentalsResponse;
import com.burakkolay.rentacar.business.dto.responses.get.GetRentalResponse;
import com.burakkolay.rentacar.business.dto.responses.update.UpdateRentalResponse;

import java.util.List;

public interface RentalService {

    List<GetAllRentalsResponse> getAll();

    GetRentalResponse getById(int id);

    CreateRentalResponse add(CreateRentalRequest request);

    UpdateRentalResponse update(int id, UpdateRentalRequest request);

    void delete(int id);
}
