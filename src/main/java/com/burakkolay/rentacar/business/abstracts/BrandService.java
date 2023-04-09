package com.burakkolay.rentacar.business.abstracts;



import com.burakkolay.rentacar.business.dto.requests.create.CreateBrandRequest;
import com.burakkolay.rentacar.business.dto.requests.update.UpdateBrandRequest;
import com.burakkolay.rentacar.business.dto.responses.create.CreateBrandResponse;
import com.burakkolay.rentacar.business.dto.responses.get.GetAllBrandsResponse;
import com.burakkolay.rentacar.business.dto.responses.get.GetBrandResponse;
import com.burakkolay.rentacar.business.dto.responses.update.UpdateBrandResponse;

import java.util.List;

public interface BrandService {
    List<GetAllBrandsResponse> getAll();
    GetBrandResponse getById(int id);
    CreateBrandResponse add(CreateBrandRequest request);
    UpdateBrandResponse update(int id, UpdateBrandRequest request);
    void delete(int id);
}
