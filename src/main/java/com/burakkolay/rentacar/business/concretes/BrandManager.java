package com.burakkolay.rentacar.business.concretes;


import com.burakkolay.rentacar.business.abstracts.BrandService;
import com.burakkolay.rentacar.business.dto.requests.create.CreateBrandRequest;
import com.burakkolay.rentacar.business.dto.requests.update.UpdateBrandRequest;
import com.burakkolay.rentacar.business.dto.responses.create.CreateBrandResponse;
import com.burakkolay.rentacar.business.dto.responses.get.GetAllBrandsResponse;
import com.burakkolay.rentacar.business.dto.responses.get.GetBrandResponse;
import com.burakkolay.rentacar.business.dto.responses.update.UpdateBrandResponse;
import com.burakkolay.rentacar.entities.concretes.Brand;
import com.burakkolay.rentacar.repository.BrandRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BrandManager implements BrandService {
    private final BrandRepository repository;
    private final ModelMapper mapper;

    @Override
    public List<GetAllBrandsResponse> getAll() {
        List<Brand> brands = repository.findAll();
        List<GetAllBrandsResponse> response = brands
                .stream()
                .map(brand -> mapper.map(brand, GetAllBrandsResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetBrandResponse getById(int id) {
        checkIfBrandExists(id);
        Brand brand = repository.findById(id).orElseThrow();
        GetBrandResponse response = mapper.map(brand, GetBrandResponse.class);

        return response;
    }

    @Override
    public CreateBrandResponse add(CreateBrandRequest request) {
//        Brand brand = new Brand();
//        brand.setName(request.getName());
//        repository.save(brand);
//
//        CreateBrandResponse response = new CreateBrandResponse();
//        response.setId(brand.getId());
//        response.setName(brand.getName());
//
//        return response;
        Brand brand = mapper.map(request, Brand.class);
        brand.setId(0);
        repository.save(brand);
        CreateBrandResponse response = mapper.map(brand, CreateBrandResponse.class);

        return response;
    }

    @Override
    public UpdateBrandResponse update(int id, UpdateBrandRequest request) {
        checkIfBrandExists(id);
        Brand brand = mapper.map(request, Brand.class);
        brand.setId(id);
        repository.save(brand);
        UpdateBrandResponse response = mapper.map(brand, UpdateBrandResponse.class);

        return response;
    }

    @Override
    public void delete(int id) {
        checkIfBrandExists(id);
        repository.deleteById(id);
    }

    // Business rules
    private void checkIfBrandExists(int id) {
        if (!repository.existsById(id)) throw new RuntimeException("Marka bulunamadı!");
    }
}
