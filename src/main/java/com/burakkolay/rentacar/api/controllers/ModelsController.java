package com.burakkolay.rentacar.api.controllers;

import com.burakkolay.rentacar.business.abstracts.ModelService;
import com.burakkolay.rentacar.business.dto.requests.create.CreateModelRequest;
import com.burakkolay.rentacar.business.dto.requests.update.UpdateModelRequest;
import com.burakkolay.rentacar.business.dto.response.create.CreateModelResponse;
import com.burakkolay.rentacar.business.dto.response.get.GetAllModelsResponse;
import com.burakkolay.rentacar.business.dto.response.get.GetModelResponse;
import com.burakkolay.rentacar.business.dto.response.update.UpdateModelResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/models")
public class ModelsController {
    private final ModelService service;

    @GetMapping
    public List<GetAllModelsResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetModelResponse getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping("/{brandId}")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateModelResponse add(@RequestBody CreateModelRequest request,@PathVariable int brandId) {
        return service.add(request,brandId);
    }

    @PutMapping("/{id}")
    public UpdateModelResponse update(@PathVariable int id, @RequestBody UpdateModelRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}
