package com.burakkolay.rentacar.business.concretes;

import com.burakkolay.rentacar.business.abstracts.CarService;
import com.burakkolay.rentacar.business.dto.requests.create.CreateCarRequest;
import com.burakkolay.rentacar.business.dto.requests.update.UpdateCarRequest;
import com.burakkolay.rentacar.business.dto.response.create.CreateCarResponse;
import com.burakkolay.rentacar.business.dto.response.get.GetAllCarsResponse;
import com.burakkolay.rentacar.business.dto.response.get.GetCarResponse;
import com.burakkolay.rentacar.business.dto.response.update.UpdateCarResponse;
import com.burakkolay.rentacar.repository.CarRepository;
import com.burakkolay.rentacar.entities.concretes.Car;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@AllArgsConstructor
public class CarManager implements CarService {
    private final CarRepository repository;
    private final ModelMapper mapper;



    @Override
    public List<GetAllCarsResponse> getAll() {
        List<Car> cars = repository.findAll();

        return cars
                .stream()
                .map(car -> mapper.map(car, GetAllCarsResponse.class))
                .toList();
    }

    @Override
    public GetCarResponse getById(int id) {
        checkIfBrandExists(id);
        Car car = repository.findById(id).orElseThrow();
        return mapper.map(car, GetCarResponse.class);
    }

    @Override
    public CreateCarResponse add(CreateCarRequest request) {
        Car car = mapper.map(request, Car.class);
        repository.save(car);
        return mapper.map(car, CreateCarResponse.class);
    }

    @Override
    public UpdateCarResponse update(int id, UpdateCarRequest request) {
        checkIfBrandExists(id);

        Car car = repository.findById(id).orElseThrow();
        car.setDailyPrice(request.getDailyPrice());
        car.setState(request.getState());
        car.setPlate(request.getPlate());
        car.setModelYear(request.getModelYear());
        repository.save(car);
        return mapper.map(car, UpdateCarResponse.class);
    }


    @Override
    public void delete(int id) {
        checkIfBrandExists(id);
        repository.deleteById(id);
    }

    // Business rules

    private void checkIfBrandExists(int id) {
        if (!repository.existsById(id)) throw new RuntimeException("No such a brand!");
    }
}