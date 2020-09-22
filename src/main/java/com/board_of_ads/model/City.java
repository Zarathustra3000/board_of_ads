package com.board_of_ads.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "city_form_subject")
    String regionFormSubject;

    @ManyToOne
    Region region;

    public City(String name, Region region, String regionFormSubject) {
        this.regionFormSubject = regionFormSubject;
        this.name = name;
        this.region = region;
    }
}
