package com.burakkolay.rentacar.business.concretes;

import com.burakkolay.rentacar.business.abstracts.BrandService;
import com.burakkolay.rentacar.business.abstracts.ModelService;
import com.burakkolay.rentacar.business.dto.requests.create.CreateModelRequest;
import com.burakkolay.rentacar.business.dto.requests.update.UpdateModelRequest;
import com.burakkolay.rentacar.business.dto.response.create.CreateModelResponse;
import com.burakkolay.rentacar.business.dto.response.get.GetAllModelsResponse;
import com.burakkolay.rentacar.business.dto.response.get.GetModelResponse;
import com.burakkolay.rentacar.business.dto.response.update.UpdateModelResponse;
import com.burakkolay.rentacar.entities.concretes.Brand;
import com.burakkolay.rentacar.entities.concretes.Model;
import com.burakkolay.rentacar.repository.ModelRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ModelManager implements ModelService {
    private final ModelRepository repository;
    private final ModelMapper mapper;
    private final BrandService brandService;

    @Override
    public List<GetAllModelsResponse> getAll() {
        List<Model> models = repository.findAll();

        return models
                .stream()
                .map(model -> mapper.map(model, GetAllModelsResponse.class))
                .toList();
    }

    @Override
    public GetModelResponse getById(int id) {
        checkIfBrandExists(id);
        Model model = repository.findById(id).orElseThrow();
        return mapper.map(model, GetModelResponse.class);
    }

    @Override
    public CreateModelResponse add(CreateModelRequest request,int brandId) {
        Model model = mapper.map(request, Model.class);
        model.setId(0);
        model.setBrand(mapper.map(brandService.getById(brandId), Brand.class));
        repository.save(model);
        return mapper.map(model, CreateModelResponse.class);
    }

    @Override
    public UpdateModelResponse update(int id, UpdateModelRequest request) {
        checkIfBrandExists(id);

        Model model = repository.findById(id).orElseThrow();
        model.setName(request.getName());
        repository.save(model);
        return mapper.map(model, UpdateModelResponse.class);

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
