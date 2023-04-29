package com.burakkolay.rentacar.business.rules;

import com.burakkolay.rentacar.common.constants.Messages;
import com.burakkolay.rentacar.core.exceptions.BusinessException;
import com.burakkolay.rentacar.entities.enums.State;
import com.burakkolay.rentacar.repository.RentalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RentalBusinessRules {

    private final RentalRepository repository;

    public void checkIfRentalExists(int id) {
        if (!repository.existsById(id)) {
            throw new BusinessException(Messages.Rental.NotExists);
        }
    }

    public void checkIfCarAvailable(State state) {
        if (!state.equals(State.AVAILABLE)) {
            throw new BusinessException(Messages.Car.NotExists);
        }
    }
}
