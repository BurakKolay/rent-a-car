package com.burakkolay.rentacar.business.abstracts;


import com.burakkolay.rentacar.business.dto.requests.create.CreateModelRequest;
import com.burakkolay.rentacar.business.dto.requests.update.UpdateModelRequest;
import com.burakkolay.rentacar.business.dto.responses.create.CreateModelResponse;
import com.burakkolay.rentacar.business.dto.responses.get.GetAllModelsResponse;
import com.burakkolay.rentacar.business.dto.responses.get.GetModelResponse;
import com.burakkolay.rentacar.business.dto.responses.update.UpdateModelResponse;

import java.util.List;

public interface ModelService {
    List<GetAllModelsResponse> getAll();

    GetModelResponse getById(int id);

    CreateModelResponse add(CreateModelRequest request);

    UpdateModelResponse update(int id, UpdateModelRequest request);

    void delete(int id);
}

