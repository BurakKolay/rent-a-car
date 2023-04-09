package com.burakkolay.rentacar.business.concretes;


import com.burakkolay.rentacar.business.abstracts.ModelService;
import com.burakkolay.rentacar.business.dto.requests.create.CreateModelRequest;
import com.burakkolay.rentacar.business.dto.requests.update.UpdateModelRequest;
import com.burakkolay.rentacar.business.dto.responses.create.CreateModelResponse;
import com.burakkolay.rentacar.business.dto.responses.get.GetAllModelsResponse;
import com.burakkolay.rentacar.business.dto.responses.get.GetModelResponse;
import com.burakkolay.rentacar.business.dto.responses.update.UpdateModelResponse;
import com.burakkolay.rentacar.entities.concretes.Model;
import com.burakkolay.rentacar.repository.ModelRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ModelManager implements ModelService {
    private final ModelRepository repository;
    private final ModelMapper mapper;

    @Override
    public List<GetAllModelsResponse> getAll() {
        List<Model> models = repository.findAll();
        List<GetAllModelsResponse> response = models
                .stream()
                .map(model -> mapper.map(model, GetAllModelsResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetModelResponse getById(int id) {
        checkIfModelExists(id);
        Model model = repository.findById(id).orElseThrow();
        GetModelResponse response = mapper.map(model, GetModelResponse.class);

        return response;
    }

    @Override
    public CreateModelResponse add(CreateModelRequest request) {
        Model model = mapper.map(request, Model.class);
        model.setId(0);
        repository.save(model);
        CreateModelResponse response = mapper.map(model, CreateModelResponse.class);

        return response;
    }

    @Override
    public UpdateModelResponse update(int id, UpdateModelRequest request) {
        checkIfModelExists(id);
        Model model = mapper.map(request, Model.class);
        model.setId(id);
        repository.save(model);
        UpdateModelResponse response = mapper.map(model, UpdateModelResponse.class);

        return response;
    }

    @Override
    public void delete(int id) {
        checkIfModelExists(id);
        repository.deleteById(id);
    }

    private void checkIfModelExists(int id) {
        if(!repository.existsById(id)){
            throw new RuntimeException("Böyle bir model bulunamadı!");
        }
    }
}
