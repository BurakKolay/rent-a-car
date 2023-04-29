package com.burakkolay.rentacar.business.rules;

import com.burakkolay.rentacar.common.constants.Messages;
import com.burakkolay.rentacar.core.exceptions.BusinessException;
import com.burakkolay.rentacar.repository.ModelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ModelBusinessRules {
    private final ModelRepository repository;

    public void checkIfModelExists(int id) {
        if (!repository.existsById(id)) {
            throw new BusinessException(Messages.Model.NotExists);
        }
    }
}
