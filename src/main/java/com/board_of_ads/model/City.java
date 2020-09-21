package com.board_of_ads.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter

@Entity
@Table(name = "city")
public class City {

    public City() {
    }

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
