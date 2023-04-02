package com.burakkolay.rentacar.business.dto.response.get;

import com.burakkolay.rentacar.entities.concretes.Car;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GetAllMaintenanceResponse {
    private int id;
    private Timestamp createdDate;
    private Timestamp lasModifiedDate;
    private Car car;
    private boolean state;
}
