package com.board_of_ads.model.posting.autoTransport.cars;

import com.board_of_ads.model.posting.autoTransport.AutoTransport;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(name = "cars")
public class Car extends AutoTransport {

    @Column
    private String color;

    @Column
    private String VIN;

    @Column
    private String brand;

    @Column
    private String model;

    @Column
    private Short year;

    @Column
    private String carBody;

    @Column
    private Byte numberOfDoors;

    @Column
    private String generation;

    @Column
    private String typeOfEngine;

    @Column
    private String wheelDrive;

    @Column
    private String transmission;

    @Column
    private String modification;

    @Column
    private String configuration;

    @Column
    private String wheelSide;

    @Column
    private boolean hasServiceBook;

    @Column
    private boolean hasDealerService;

    @Column
    private boolean UnderWarranty;

    @OneToMany(mappedBy = "car",
            fetch = FetchType.EAGER,
            cascade = CascadeType.MERGE)
    private Set<Option> additionalOptions;
}
