package com.board_of_ads.model.posting.autoTransport;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@Entity
public class NewCarPosting extends AutoTransportPosting {

    private String color;

    private String VIN;

    private String brand;

    private String model;

    private Short year;

    private String carBody;

    private Byte numberOfDoors;

    private String generation;

    private String typeOfEngine;

    private String wheelDrive;

    private String transmission;

    private String modification;

    private String configuration;

    private String wheelSide;

    private boolean hasServiceBook;

    private boolean hasDealerService;

    private boolean UnderWarranty;

    @OneToMany(mappedBy = "newCarPosting",
            fetch = FetchType.EAGER,
            cascade = CascadeType.MERGE)
    private Set<Option> additionalOptions;
}
