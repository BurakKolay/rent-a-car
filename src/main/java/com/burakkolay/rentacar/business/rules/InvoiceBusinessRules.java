package com.burakkolay.rentacar.business.rules;

import com.burakkolay.rentacar.common.constants.Messages;
import com.burakkolay.rentacar.core.exceptions.BusinessException;
import com.burakkolay.rentacar.repository.InvoiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InvoiceBusinessRules {
    private final InvoiceRepository repository;

    public void checkIfInvoiceExists(int id) {
        if (!repository.existsById(id)) {
            throw new BusinessException(Messages.Invoice.NotExists);
        }
    }
}
