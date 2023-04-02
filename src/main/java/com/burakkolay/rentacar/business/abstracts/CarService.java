package com.burakkolay.rentacar.business.abstracts;

import com.burakkolay.rentacar.business.dto.requests.create.CreateCarRequest;
import com.burakkolay.rentacar.business.dto.requests.update.UpdateCarRequest;
import com.burakkolay.rentacar.business.dto.response.create.CreateCarResponse;
import com.burakkolay.rentacar.business.dto.response.get.GetAllCarsResponse;
import com.burakkolay.rentacar.business.dto.response.get.GetCarResponse;
import com.burakkolay.rentacar.business.dto.response.update.UpdateAvailabilityResponse;
import com.burakkolay.rentacar.business.dto.response.update.UpdateCarResponse;
import com.burakkolay.rentacar.business.dto.response.update.UpdateMaintenanceResponse;

import java.util.List;

public interface CarService {
    List<GetAllCarsResponse> getAll();
    List<GetAllCarsResponse> getAllByState(String state);
    GetCarResponse getById(int id);
    CreateCarResponse add(CreateCarRequest request);
    UpdateCarResponse update(int id, UpdateCarRequest request);
    void delete(int id);
    UpdateMaintenanceResponse maintanence(int id);
    UpdateAvailabilityResponse makeAvailable(int id);
}
