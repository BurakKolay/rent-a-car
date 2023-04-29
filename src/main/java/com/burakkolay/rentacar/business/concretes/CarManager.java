package com.burakkolay.rentacar.business.concretes;


import com.burakkolay.rentacar.business.abstracts.CarService;
import com.burakkolay.rentacar.business.dto.requests.create.CreateCarRequest;
import com.burakkolay.rentacar.business.dto.requests.update.UpdateCarRequest;
import com.burakkolay.rentacar.business.dto.responses.create.CreateCarResponse;
import com.burakkolay.rentacar.business.dto.responses.get.GetAllCarsResponse;
import com.burakkolay.rentacar.business.dto.responses.get.GetCarResponse;
import com.burakkolay.rentacar.business.dto.responses.update.UpdateCarResponse;
import com.burakkolay.rentacar.business.rules.CarBusinessRules;
import com.burakkolay.rentacar.entities.concretes.Car;
import com.burakkolay.rentacar.entities.enums.State;
import com.burakkolay.rentacar.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CarManager implements CarService {
    private final CarRepository repository;
    private final ModelMapper mapper;
    private final CarBusinessRules rules;

    @Override
    public List<GetAllCarsResponse> getAll(boolean includeMaintenance) {
        List<Car> cars = filterCarsByMaintenanceState(includeMaintenance);
        List<GetAllCarsResponse> response = cars
                .stream()
                .map(car -> mapper.map(car, GetAllCarsResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetCarResponse getById(int id) {
        rules.checkIfExistsById(id);
        Car car = repository.findById(id).orElseThrow();
        GetCarResponse response = mapper.map(car, GetCarResponse.class);

        return response;
    }

    @Override
    public CreateCarResponse add(CreateCarRequest request) {
        Car car = mapper.map(request, Car.class);
        car.setId(0);
        car.setState(State.AVAILABLE);
        repository.save(car);
        CreateCarResponse response = mapper.map(car, CreateCarResponse.class);

        return response;
    }

    @Override
    public UpdateCarResponse update(int id, UpdateCarRequest request) {
        rules.checkIfExistsById(id);
        Car car = mapper.map(request, Car.class);
        car.setId(id);
        repository.save(car);
        UpdateCarResponse response = mapper.map(car, UpdateCarResponse.class);

        return response;
    }

    @Override
    public void delete(int id) {
        rules.checkIfExistsById(id);
        repository.deleteById(id);
    }

    @Override
    public void changeState(int carId, State state) {
        Car car = repository.findById(carId).orElseThrow();
        car.setState(state);
        repository.save(car);
    }


    private List<Car> filterCarsByMaintenanceState(boolean includeMaintenance) {
        if (includeMaintenance) {
            return repository.findAll();
        }

        return repository.findAllByStateIsNot(State.MAINTENANCE);
    }
}
