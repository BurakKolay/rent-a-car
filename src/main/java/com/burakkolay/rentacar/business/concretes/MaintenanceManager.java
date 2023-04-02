package com.burakkolay.rentacar.business.concretes;

import com.burakkolay.rentacar.business.abstracts.CarService;
import com.burakkolay.rentacar.business.abstracts.MaintenanceService;
import com.burakkolay.rentacar.business.dto.requests.create.CreateMaintenanceRequest;
import com.burakkolay.rentacar.business.dto.requests.update.UpdateMaintenanceRequest;
import com.burakkolay.rentacar.business.dto.response.create.CreateMaintenanceResponse;
import com.burakkolay.rentacar.business.dto.response.get.GetAllMaintenanceResponse;
import com.burakkolay.rentacar.business.dto.response.get.GetMaintenanceResponse;
import com.burakkolay.rentacar.business.dto.response.update.UpdateMaintenanceResponse;
import com.burakkolay.rentacar.entities.concretes.Maintenance;
import com.burakkolay.rentacar.entities.enums.State;
import com.burakkolay.rentacar.repository.CarRepository;
import com.burakkolay.rentacar.repository.MaintenanceRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class MaintenanceManager implements MaintenanceService {
    private final MaintenanceRepository maintenanceRepository;
    private final ModelMapper mapper;
    private final CarService carService;

    @Override
    public List<GetAllMaintenanceResponse> getAll() {
        List<Maintenance> maintenances = maintenanceRepository.findAll();
        return maintenances
                .stream()
                .map(maintenance -> mapper.map(maintenance, GetAllMaintenanceResponse.class))
                .toList();
    }

    @Override
    public GetMaintenanceResponse getById(int id) {
        return mapper.map(maintenanceRepository.findById(id).orElseThrow(), GetMaintenanceResponse.class);
    }

    @Override
    public CreateMaintenanceResponse add(CreateMaintenanceRequest request) {
        Maintenance maintenance = mapper.map(request, Maintenance.class);
        maintenance.setId(0);
        int carId = request.getCarId();
        carService.maintanence(carId);
        maintenance.setState(true);
        maintenanceRepository.save(maintenance);
        return mapper.map(maintenance,CreateMaintenanceResponse.class);
    }

    @Override
    public UpdateMaintenanceResponse update(int id, UpdateMaintenanceRequest request) {
        Maintenance tempMaintenance = mapper.map(request, Maintenance.class);
        tempMaintenance.setId(id);
        Maintenance maintenance = maintenanceRepository.findById(tempMaintenance.getId()).orElseThrow();
        checkIfMaintenanceExist(id);
        int carId = request.getCarId();
        carService.makeAvailable(carId);
        maintenance.setState(false);
        maintenanceRepository.save(maintenance);
        return mapper.map(maintenance, UpdateMaintenanceResponse.class);
    }

    @Override
    public void delete(int id) {
        checkIfMaintenanceExist(id);
        maintenanceRepository.deleteById(id);
    }

    private void checkIfMaintenanceExist(int id) {
        if (!maintenanceRepository.existsById(id)) throw new RuntimeException("Maintance Id does not exist!");
    }
}
