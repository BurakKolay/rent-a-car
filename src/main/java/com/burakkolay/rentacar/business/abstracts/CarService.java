package com.burakkolay.rentacar.business.abstracts;



import com.burakkolay.rentacar.business.dto.requests.create.CreateCarRequest;
import com.burakkolay.rentacar.business.dto.requests.update.UpdateCarRequest;
import com.burakkolay.rentacar.business.dto.responses.create.CreateCarResponse;
import com.burakkolay.rentacar.business.dto.responses.get.GetAllCarsResponse;
import com.burakkolay.rentacar.business.dto.responses.get.GetCarResponse;
import com.burakkolay.rentacar.business.dto.responses.update.UpdateCarResponse;
import com.burakkolay.rentacar.entities.enums.State;

import java.util.List;

public interface CarService {
    List<GetAllCarsResponse> getAll(boolean includeMaintenance);
    GetCarResponse getById(int id);
    CreateCarResponse add(CreateCarRequest request);
    UpdateCarResponse update(int id, UpdateCarRequest request);
    void delete(int id);
    void changeState(int carId, State state);
}
