package com.burakkolay.rentacar.business.concretes;


import com.burakkolay.rentacar.business.abstracts.CarService;
import com.burakkolay.rentacar.business.abstracts.MaintenanceService;
import com.burakkolay.rentacar.business.dto.requests.create.CreateMaintenanceRequest;
import com.burakkolay.rentacar.business.dto.requests.update.UpdateMaintenanceRequest;
import com.burakkolay.rentacar.business.dto.responses.create.CreateMaintenanceResponse;
import com.burakkolay.rentacar.business.dto.responses.get.GetAllMaintenancesResponse;
import com.burakkolay.rentacar.business.dto.responses.get.GetMaintenanceResponse;
import com.burakkolay.rentacar.business.dto.responses.update.UpdateMaintenanceResponse;
import com.burakkolay.rentacar.entities.concretes.Maintenance;
import com.burakkolay.rentacar.entities.enums.State;
import com.burakkolay.rentacar.repository.MaintenanceRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class MaintenanceManager implements MaintenanceService {
    private final MaintenanceRepository repository;
    private final ModelMapper mapper;
    private final CarService carService;

    @Override
    public List<GetAllMaintenancesResponse> getAll() {
        List<Maintenance> maintenances = repository.findAll();
        List<GetAllMaintenancesResponse> response = maintenances
                .stream()
                .map(maintenance -> mapper.map(maintenance, GetAllMaintenancesResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetMaintenanceResponse getById(int id) {
        Maintenance maintenance = repository.findById(id).orElseThrow();
        GetMaintenanceResponse response = mapper.map(maintenance, GetMaintenanceResponse.class);

        return response;
    }

    @Override
    public GetMaintenanceResponse returnCarFromMaintenance(int carId) {
        checkIfCarIsNotUnderMaintenance(carId);
        Maintenance maintenance = repository.findMaintenanceByCarIdAndIsCompletedFalse(carId);
        maintenance.setCompleted(true);
        maintenance.setEndDate(LocalDateTime.now());
        repository.save(maintenance);
        carService.changeState(carId, State.AVAILABLE);
        GetMaintenanceResponse response = mapper.map(maintenance, GetMaintenanceResponse.class);

        return response;
    }

    @Override
    public CreateMaintenanceResponse add(CreateMaintenanceRequest request) {
        checkCarAvailabilityForMaintenance(request.getCarId());
        checkIfCarUnderMaintenance(request.getCarId());
        Maintenance maintenance = mapper.map(request, Maintenance.class);
        maintenance.setId(0);
        maintenance.setCompleted(false);
        maintenance.setStartDate(LocalDateTime.now());
        maintenance.setEndDate(null);
        carService.changeState(request.getCarId(), State.MAINTENANCE);
        repository.save(maintenance);

        CreateMaintenanceResponse response = mapper.map(maintenance, CreateMaintenanceResponse.class);

        return response;
    }

    @Override
    public UpdateMaintenanceResponse update(int id, UpdateMaintenanceRequest request) {
        Maintenance maintenance = mapper.map(request, Maintenance.class);
        maintenance.setId(id);
        repository.save(maintenance);
        UpdateMaintenanceResponse response = mapper.map(maintenance, UpdateMaintenanceResponse.class);

        return response;
    }

    @Override
    public void delete(int id) {
        checkIfMaintenanceExists(id);
        makeCarAvailanleIfIsCompletedFalse(id);
        repository.deleteById(id);
    }
    private void checkIfMaintenanceExists(int id){
        if(!repository.existsById(id)){
            throw new RuntimeException("Böyle bir bakım bilgisine ulaşılamadı");
        }
    }
    private void checkIfCarUnderMaintenance(int carId) {
        if (repository.existsByCarIdAndIsCompletedFalse(carId)) {
            throw new RuntimeException("Araç şuanda bakımda!");
        }
    }

    private void checkIfCarIsNotUnderMaintenance(int carId) {
        if (!repository.existsByCarIdAndIsCompletedFalse(carId)) {
            throw new RuntimeException("Bakımda böyle bir araç bulunamadı!");
        }
    }

    private void checkCarAvailabilityForMaintenance(int carId) {
        if (carService.getById(carId).equals(State.RENTED)) {
            throw new RuntimeException("Araç kirada olduğu için bakıma alınamaz!");
        }
    }

    private void makeCarAvailanleIfIsCompletedFalse(int id){
        int carId = repository.findById(id).get().getId();
        if(repository.existsByCarIdAndIsCompletedFalse(id)){
            carService.changeState(carId,State.AVAILABLE);
        }
    }
}
