package com.burakkolay.rentacar.entities;

import com.burakkolay.rentacar.entities.enums.State;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int modelYear;
    private String plate;
    private double dailyPrice;
    @Enumerated(EnumType.STRING)
    private State state;
    @ManyToOne
//    @JsonManagedReference
    private Model model;
    @OneToMany(mappedBy = "car")
    private List<Maintenance> maintenances;
    @OneToMany(mappedBy = "car")
    private List<Rental> rentals;
}
