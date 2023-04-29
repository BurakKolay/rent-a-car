package com.burakkolay.rentacar.business.rules;

import com.burakkolay.rentacar.common.constants.Messages;
import com.burakkolay.rentacar.core.exceptions.BusinessException;
import com.burakkolay.rentacar.repository.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BrandBusinessRules {
    private final BrandRepository repository;

    // Business rules
    public void checkIfBrandExists(int id) {
        if (!repository.existsById(id)) throw new BusinessException(Messages.Brand.NotExists);
    }

    public void checkIfBrandExistsByName(String name){
        if(repository.existsByNameIgnoreCase(name)){
            throw new BusinessException(Messages.Brand.Exists);
        }
    }
}
