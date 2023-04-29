package com.burakkolay.rentacar.business.abstracts;


import com.burakkolay.rentacar.business.dto.requests.create.CreateMaintenanceRequest;
import com.burakkolay.rentacar.business.dto.requests.update.UpdateMaintenanceRequest;
import com.burakkolay.rentacar.business.dto.responses.create.CreateMaintenanceResponse;
import com.burakkolay.rentacar.business.dto.responses.get.GetAllMaintenancesResponse;
import com.burakkolay.rentacar.business.dto.responses.get.GetMaintenanceResponse;
import com.burakkolay.rentacar.business.dto.responses.update.UpdateMaintenanceResponse;

import java.util.List;

public interface MaintenanceService {
    List<GetAllMaintenancesResponse> getAll();

    GetMaintenanceResponse getById(int id);

    GetMaintenanceResponse returnCarFromMaintenance(int carId);

    CreateMaintenanceResponse add(CreateMaintenanceRequest request);

    UpdateMaintenanceResponse update(int id, UpdateMaintenanceRequest request);

    void delete(int id);
}
