package com.burakkolay.rentacar.business.abstracts;

import com.burakkolay.rentacar.business.dto.requests.create.CreateMaintenanceRequest;
import com.burakkolay.rentacar.business.dto.requests.update.UpdateMaintenanceRequest;
import com.burakkolay.rentacar.business.dto.response.create.CreateMaintenanceResponse;
import com.burakkolay.rentacar.business.dto.response.get.GetAllMaintenanceResponse;
import com.burakkolay.rentacar.business.dto.response.get.GetMaintenanceResponse;
import com.burakkolay.rentacar.business.dto.response.update.UpdateMaintenanceResponse;

import java.util.List;

public interface MaintenanceService {
    List<GetAllMaintenanceResponse> getAll();
    GetMaintenanceResponse getById(int id);
    CreateMaintenanceResponse add(CreateMaintenanceRequest request);
    UpdateMaintenanceResponse update(int id, UpdateMaintenanceRequest request);
    void delete(int id);
}
