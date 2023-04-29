package com.burakkolay.rentacar.adapters;

import com.burakkolay.rentacar.business.abstracts.PosService;
import com.burakkolay.rentacar.common.constants.Messages;
import com.burakkolay.rentacar.core.exceptions.BusinessException;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class FakePosServiceAdapter implements PosService {
    @Override
    public void pay() {
        boolean isPaymentSuccessful = new Random().nextBoolean();
        if (!isPaymentSuccessful) throw new BusinessException(Messages.Payment.Failed);
    }
}
