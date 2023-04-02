package com.burakkolay.rentacar.api.controllers;

import com.burakkolay.rentacar.business.abstracts.MaintenanceService;
import com.burakkolay.rentacar.business.dto.requests.create.CreateMaintenanceRequest;
import com.burakkolay.rentacar.business.dto.requests.update.UpdateMaintenanceRequest;
import com.burakkolay.rentacar.business.dto.response.create.CreateMaintenanceResponse;
import com.burakkolay.rentacar.business.dto.response.get.GetAllMaintenanceResponse;
import com.burakkolay.rentacar.business.dto.response.get.GetMaintenanceResponse;
import com.burakkolay.rentacar.business.dto.response.update.UpdateMaintenanceResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/maintenances")
public class MaintenanceController {
    private final MaintenanceService maintenanceService;

    @GetMapping
    public List<GetAllMaintenanceResponse> getAll(){
        return maintenanceService.getAll();
    }

    @GetMapping("/{id}")
    public GetMaintenanceResponse getById(@PathVariable int id){
        return maintenanceService.getById(id);
    }

    @PostMapping()
    public CreateMaintenanceResponse add(@RequestBody CreateMaintenanceRequest request){
        return maintenanceService.add(request);
    }

    @PutMapping("/{id}")
    public UpdateMaintenanceResponse update(int id, UpdateMaintenanceRequest request){
        return maintenanceService.update(id,request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        maintenanceService.delete(id);
    }
}
